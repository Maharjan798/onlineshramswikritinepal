package com.example.data.model

enum class AppLanguage(val code: String, val displayName: String, val nativeName: String) {
    ENGLISH("en", "English", "English"),
    NEPALI("ne", "Nepali", "नेपाली")
}

enum class CountryRegion(val nameEn: String, val nameNe: String) {
    GCC("GCC / Middle East", "जी.सी.सी. / मध्य पूर्व"),
    MALAYSIA("Malaysia", "मलेसिया"),
    EUROPE("Europe", "युरोप"),
    EAST_ASIA("East Asia (Japan/Korea)", "पूर्वी एशिया (जापान/कोरिया)"),
    AMERICAS("Americas & Oceania", "अमेरिका र ओशिनिया"),
    OTHER("Other Destinations", "अन्य देशहरु")
}

data class DestinationCountry(
    val id: String,
    val nameEn: String,
    val nameNe: String,
    val flagEmoji: String,
    val region: CountryRegion,
    val currencyCode: String,
    val nprExchangeRate: Double, // 1 Local Unit = X NPR
    val isReentryAllowedDirect: Boolean,
    val embassyVerificationRequired: Boolean,
    val biometricsNotesEn: String,
    val biometricsNotesNe: String,
    val specialAlertsEn: String,
    val specialAlertsNe: String
)

data class CostBreakdown(
    val welfareFundNpr: Double,
    val insurancePremiumNpr: Double,
    val ssfContributionNpr: Double,
    val ssfMonths: Int = 1,
    val ssfMonthlyRateNpr: Double = 2596.0,
    val serviceFeeNpr: Double = 0.0,
    val gatewayFeeNpr: Double = 0.0,
    val totalNpr: Double,
    val localCurrencyCode: String,
    val totalLocalCurrency: Double,
    val ageBandTextEn: String,
    val ageBandTextNe: String,
    val contractYearsTextEn: String,
    val contractYearsTextNe: String,
    val isSsfSubsidized: Boolean = false
) {
    val totalGovtChargesNpr: Double
        get() = welfareFundNpr + insurancePremiumNpr + ssfContributionNpr
}

data class DocumentItem(
    val id: String,
    val titleEn: String,
    val titleNe: String,
    val descEn: String,
    val descNe: String,
    val isRequired: Boolean,
    val maxSizeKb: Int = 500,
    val allowedFormats: String = "JPG, PNG, PDF",
    val categoryTags: List<String> = listOf("ALL")
)

data class GuidanceStep(
    val stepNumber: Int,
    val titleEn: String,
    val titleNe: String,
    val summaryEn: String,
    val summaryNe: String,
    val detailedInstructionsEn: List<String>,
    val detailedInstructionsNe: List<String>,
    val proTipsEn: List<String>,
    val proTipsNe: List<String>,
    val actionUrl: String? = null,
    val actionTextEn: String? = null,
    val actionTextNe: String? = null
)

data class FaqItem(
    val id: String,
    val questionEn: String,
    val questionNe: String,
    val answerEn: String,
    val answerNe: String,
    val category: String = "GENERAL"
)

