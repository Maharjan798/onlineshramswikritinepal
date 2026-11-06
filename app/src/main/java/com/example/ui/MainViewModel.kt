package com.example.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.example.data.datasource.PredefinedData
import com.example.data.db.AppDatabase
import com.example.data.db.SavedEstimateEntity
import com.example.data.db.ShramRepository
import com.example.data.model.AppLanguage
import com.example.data.model.CostBreakdown
import com.example.data.model.DestinationCountry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "shram_swikriti_db"
    ).build()

    private val repository = ShramRepository(db.shramDao())

    // 1. App Language Toggle State
    private val _language = MutableStateFlow(AppLanguage.NEPALI)
    val language: StateFlow<AppLanguage> = _language.asStateFlow()

    fun toggleLanguage() {
        _language.value = if (_language.value == AppLanguage.NEPALI) AppLanguage.ENGLISH else AppLanguage.NEPALI
    }

    fun setLanguage(lang: AppLanguage) {
        _language.value = lang
    }

    // 2. Navigation State
    private val _selectedTab = MutableStateFlow(0)
    val selectedTab: StateFlow<Int> = _selectedTab.asStateFlow()

    fun setSelectedTab(tabIndex: Int) {
        _selectedTab.value = tabIndex
    }

    // 3. Calculator State (Default Age: 28, Contract: 2 yrs, Country: Qatar)
    private val _age = MutableStateFlow(28)
    val age: StateFlow<Int> = _age.asStateFlow()

    private val _contractYears = MutableStateFlow(2)
    val contractYears: StateFlow<Int> = _contractYears.asStateFlow()

    private val _ssfMonths = MutableStateFlow(1)
    val ssfMonths: StateFlow<Int> = _ssfMonths.asStateFlow()

    private val _includeServiceFee = MutableStateFlow(false)
    val includeServiceFee: StateFlow<Boolean> = _includeServiceFee.asStateFlow()

    private val _selectedCountry = MutableStateFlow(PredefinedData.COUNTRIES.first())
    val selectedCountry: StateFlow<DestinationCountry> = _selectedCountry.asStateFlow()

    private val _includeSsf = MutableStateFlow(true)
    val includeSsf: StateFlow<Boolean> = _includeSsf.asStateFlow()

    private val _isSsfSubsidized = MutableStateFlow(false)
    val isSsfSubsidized: StateFlow<Boolean> = _isSsfSubsidized.asStateFlow()

    private data class CalcInputs(val age: Int, val years: Int, val ssfMonths: Int, val serviceFee: Boolean)
    private data class SsfCountryInputs(val includeSsf: Boolean, val isSubsidized: Boolean, val country: DestinationCountry)

    val costBreakdown: StateFlow<CostBreakdown> = kotlinx.coroutines.flow.combine(
        combine(_age, _contractYears, _ssfMonths, _includeServiceFee) { age, years, ssfM, fee ->
            CalcInputs(age, years, ssfM, fee)
        },
        combine(_includeSsf, _isSsfSubsidized, _selectedCountry) { ssf, sub, country ->
            SsfCountryInputs(ssf, sub, country)
        }
    ) { c, s ->
        PredefinedData.calculateCost(
            age = c.age,
            contractYears = c.years,
            ssfMonths = c.ssfMonths,
            includeSsf = s.includeSsf,
            includeServiceFee = c.serviceFee,
            isSsfSubsidized = s.isSubsidized,
            country = s.country
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = PredefinedData.calculateCost(28, 2, 1, true, false, false, PredefinedData.COUNTRIES.first())
    )

    fun setAge(newAge: Int) {
        _age.value = newAge.coerceIn(18, 64)
    }

    fun setAgeGroup(groupIndex: Int) {
        when (groupIndex) {
            0 -> _age.value = 28 // 18-35
            1 -> _age.value = 42 // 36-50
            else -> _age.value = 58 // 51-64
        }
    }

    fun setSsfMonths(months: Int) {
        _ssfMonths.value = months.coerceIn(1, 12)
    }

    fun setIncludeServiceFee(include: Boolean) {
        _includeServiceFee.value = include
    }

    fun setContractYears(years: Int) {
        _contractYears.value = years.coerceIn(1, 5)
    }

    fun setSelectedCountry(country: DestinationCountry) {
        _selectedCountry.value = country
    }

    fun setIncludeSsf(include: Boolean) {
        _includeSsf.value = include
    }

    fun setIsSsfSubsidized(subsidized: Boolean) {
        _isSsfSubsidized.value = subsidized
    }

    // Save current calculation to Room DB
    fun saveCurrentCalculation() {
        viewModelScope.launch {
            val breakdown = costBreakdown.value
            val country = selectedCountry.value
            val entity = SavedEstimateEntity(
                countryId = country.id,
                countryNameEn = country.nameEn,
                countryNameNe = country.nameNe,
                flagEmoji = country.flagEmoji,
                age = age.value,
                contractYears = contractYears.value,
                welfareFundNpr = breakdown.welfareFundNpr,
                insurancePremiumNpr = breakdown.insurancePremiumNpr,
                ssfContributionNpr = breakdown.ssfContributionNpr,
                gatewayFeeNpr = breakdown.gatewayFeeNpr,
                totalNpr = breakdown.totalNpr,
                currencyCode = country.currencyCode,
                totalLocalCurrency = breakdown.totalLocalCurrency
            )
            repository.saveEstimate(entity)
        }
    }

    // 4. Saved History Stream
    val savedEstimates = repository.savedEstimates.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun deleteEstimate(id: Long) {
        viewModelScope.launch {
            repository.deleteEstimate(id)
        }
    }

    // 5. Checklist State
    val checklistStates = repository.checklistStates.map { list ->
        list.associate { it.documentId to it.isCompleted }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyMap()
    )

    fun toggleChecklistItem(docId: String, currentStatus: Boolean) {
        viewModelScope.launch {
            repository.updateChecklistState(docId, !currentStatus)
        }
    }

    fun resetChecklist() {
        viewModelScope.launch {
            repository.resetChecklist()
        }
    }

    // 6. Step Wizard Progress State
    private val _completedSteps = MutableStateFlow(setOf<Int>())
    val completedSteps: StateFlow<Set<Int>> = _completedSteps.asStateFlow()

    fun toggleStepCompleted(stepNumber: Int) {
        val current = _completedSteps.value.toMutableSet()
        if (current.contains(stepNumber)) {
            current.remove(stepNumber)
        } else {
            current.add(stepNumber)
        }
        _completedSteps.value = current
    }

    // Document Scanner Preview Dialog State
    private val _scannedDocTitle = MutableStateFlow<String?>(null)
    val scannedDocTitle: StateFlow<String?> = _scannedDocTitle.asStateFlow()

    fun openDocScannerPreview(title: String) {
        _scannedDocTitle.value = title
    }

    fun closeDocScannerPreview() {
        _scannedDocTitle.value = null
    }
}
