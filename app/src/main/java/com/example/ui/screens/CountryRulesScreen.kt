package com.example.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Public
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.datasource.PredefinedData
import com.example.data.model.AppLanguage
import com.example.data.model.CountryRegion
import com.example.ui.MainViewModel
import com.example.ui.components.WarningCard
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun CountryRulesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.language.collectAsState()
    var selectedRegionFilter by remember { mutableStateOf<CountryRegion?>(null) }

    val countries = PredefinedData.COUNTRIES.filter { country ->
        selectedRegionFilter == null || country.region == selectedRegionFilter
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Title Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Public,
                contentDescription = "Country Rules",
                tint = PrimaryCobalt,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "देशगत नियम तथा विशेष सूचना" else "Country Rules & Smart Alerts",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryNavy
                )
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "जीसीसी, युरोप, मलेसिया लगायतका देशका नियमहरु" else "Regulations for GCC, Malaysia, Europe & International Destinations",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Smart Alert Cards (Highlights)
        WarningCard(
            title = if (currentLanguage == AppLanguage.NEPALI) "जीसीसी र मलेसिया (GCC & Malaysia) री-एन्ट्री:" else "GCC & Malaysia Direct Re-entry:",
            message = if (currentLanguage == AppLanguage.NEPALI) "पुरानै रोजगारदाता कम्पनीमा फर्किने कामदारको लागि सीधा अनलाइन री-एन्ट्री (आफ्नो स्वीकृति) अनुमति दिइन्छ।" else "Direct online re-entry (Aafno Swikriti) permitted if employer name matches previous permit."
        )

        WarningCard(
            title = if (currentLanguage == AppLanguage.NEPALI) "युरोपियन देशहरु (European Destinations):" else "European Destination Regulations:",
            message = if (currentLanguage == AppLanguage.NEPALI) "कम्पनी वा रोजगारदाता परिवर्तन गर्दा नेपाली दूतावासबाट मागपत्र प्रमाणीकरण (Demand Attestation) अनिवार्य छ।" else "Verification from Nepali Embassy in Europe mandatory if switching employer."
        )

        WarningCard(
            title = if (currentLanguage == AppLanguage.NEPALI) "बायोमेट्रिक नियम (Biometrics Requirement):" else "Biometrics Database Rule:",
            message = if (currentLanguage == AppLanguage.NEPALI) "२०१९ अघि वा FEIMS प्रणालीमा बायोमेट्रिक नभएका कामदारले एकपटक विभाग वा ताहाचल कार्यालय जानुपर्छ।" else "Must visit DOFE office in person if biometrics were taken before 2019 or not registered in FEIMS."
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Region Filters
        Text(
            text = if (currentLanguage == AppLanguage.NEPALI) "क्षेत्र अनुसार फिल्टर गर्नुहोस्:" else "Filter by Region:",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(
                selected = selectedRegionFilter == null,
                onClick = { selectedRegionFilter = null },
                label = { Text(text = if (currentLanguage == AppLanguage.NEPALI) "सबै" else "All") }
            )
            FilterChip(
                selected = selectedRegionFilter == CountryRegion.GCC,
                onClick = { selectedRegionFilter = CountryRegion.GCC },
                label = { Text(text = "GCC") }
            )
            FilterChip(
                selected = selectedRegionFilter == CountryRegion.EUROPE,
                onClick = { selectedRegionFilter = CountryRegion.EUROPE },
                label = { Text(text = "Europe") }
            )
            FilterChip(
                selected = selectedRegionFilter == CountryRegion.MALAYSIA,
                onClick = { selectedRegionFilter = CountryRegion.MALAYSIA },
                label = { Text(text = "Malaysia") }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Country Cards List
        countries.forEach { country ->
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = country.flagEmoji, fontSize = 28.sp)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = if (currentLanguage == AppLanguage.NEPALI) country.nameNe else country.nameEn,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (country.isReentryAllowedDirect) EmeraldSuccess.copy(alpha = 0.15f) else MaterialTheme.colorScheme.errorContainer
                        ) {
                            Text(
                                text = if (country.isReentryAllowedDirect) {
                                    if (currentLanguage == AppLanguage.NEPALI) "सीधा अनलाइन" else "Direct Online"
                                } else {
                                    if (currentLanguage == AppLanguage.NEPALI) "दूतावास रुजु" else "Embassy Verification"
                                },
                                style = MaterialTheme.typography.labelSmall,
                                color = if (country.isReentryAllowedDirect) EmeraldSuccess else MaterialTheme.colorScheme.error,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "• नियम: ${country.specialAlertsNe}" else "• Rule: ${country.specialAlertsEn}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "• बायोमेट्रिक: ${country.biometricsNotesNe}" else "• Biometrics: ${country.biometricsNotesEn}",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
