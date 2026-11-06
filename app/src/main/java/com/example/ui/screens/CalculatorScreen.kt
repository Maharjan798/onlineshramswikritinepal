package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.datasource.PredefinedData
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.components.CountryDropdown
import com.example.ui.components.FaqSectionCard
import com.example.ui.components.FeeSummaryCard
import com.example.ui.components.GovernmentDisclaimerCard
import com.example.ui.components.WhatsAppContactQrCard
import com.example.ui.components.WhatsAppGreen
import com.example.ui.components.openWhatsApp
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun CalculatorScreen(
    viewModel: MainViewModel,
    onNavigateToBilling: () -> Unit = { viewModel.setSelectedTab(1) },
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.language.collectAsState()
    val age by viewModel.age.collectAsState()
    val ssfMonths by viewModel.ssfMonths.collectAsState()
    val includeServiceFee by viewModel.includeServiceFee.collectAsState()
    val selectedCountry by viewModel.selectedCountry.collectAsState()
    val costBreakdown by viewModel.costBreakdown.collectAsState()
    val context = LocalContext.current

    val scrollState = rememberScrollState()

    // Active Age Group Index: 0 for 18-35, 1 for 36-50, 2 for 51-64
    val activeAgeGroupIndex = when {
        age in 18..35 -> 0
        age in 36..50 -> 1
        else -> 2
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Section Title Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Calculate,
                contentDescription = "Calculator",
                tint = PrimaryCobalt,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "Shram Swikriti by Digital Solution" else "Shram Swikriti Fee Calculator",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryNavy
                )
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "स्वतन्त्र सेवा – वैदेशिक रोजगार विभागसँग असम्बन्धित" else "Independent Facilitation – Not Affiliated with DOFE",
                    style = MaterialTheme.typography.bodySmall,
                    color = PrimaryCobalt,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // PROMINENT NON-GOVERNMENT ENTITY DISCLAIMER BANNER (Play Console Policy Compliance)
        GovernmentDisclaimerCard(
            currentLanguage = currentLanguage,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // DIRECT WHATSAPP CONTACT QR (Bibek Maharjan - Published on 1st Page)
        WhatsAppContactQrCard(
            currentLanguage = currentLanguage,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Main Input Selection Card (Exact Digital Solution UI)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                // 1. AGE GROUP Selector Label
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "तपाईंको उमेर समूह (AGE GROUP)" else "Worker Age Group (AGE GROUP)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 3 Segmented Pill Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    val ageGroups = listOf("18-35", "36-50", "51-64")
                    ageGroups.forEachIndexed { index, groupText ->
                        val isSelected = activeAgeGroupIndex == index
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(44.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    if (isSelected) PrimaryNavy else Color.Transparent
                                )
                                .border(
                                    width = 1.dp,
                                    color = if (isSelected) PrimaryNavy else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .clickable { viewModel.setAgeGroup(index) },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = groupText,
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // 2. SSF Month Selector Slider (1-12)
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "SSF तिर्ने महिना (1-12)" else "SSF Payment Months (1-12)",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "रु. २,५९६ प्रति महिना" else "Rs. 2,596 per month",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Slider(
                        value = ssfMonths.toFloat(),
                        onValueChange = { viewModel.setSsfMonths(it.toInt()) },
                        valueRange = 1f..12f,
                        steps = 10,
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFDC2626), // Red thumb like screenshot
                            activeTrackColor = Color(0xFFDC2626)
                        ),
                        modifier = Modifier.weight(1f)
                    )

                    Spacer(modifier = Modifier.width(16.dp))

                    Text(
                        text = "$ssfMonths",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // 3. Service Fee Checkbox (Rs. 500)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { viewModel.setIncludeServiceFee(!includeServiceFee) },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = includeServiceFee,
                        onCheckedChange = { viewModel.setIncludeServiceFee(it) },
                        colors = CheckboxDefaults.colors(
                            checkedColor = PrimaryCobalt
                        )
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "हाम्रो सेवा शुल्क समावेश गर्ने (रु. ५००)"
                        else
                            "Include Agency Service Fee (Rs. 500)",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 5. Total Fee Summary Card Output (FEE RECEIPT PREVIEW)
        FeeSummaryCard(
            breakdown = costBreakdown,
            currentLanguage = currentLanguage,
            onSaveEstimate = { viewModel.saveCurrentCalculation() },
            onViewTaxInvoice = { onNavigateToBilling() }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quick WhatsApp Support Card with CEO & Manager
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.HeadsetMic,
                        contentDescription = "WhatsApp Assistance",
                        tint = WhatsAppGreen,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "हट्सएप सिधा सहयोग (WhatsApp Assistance)"
                        else
                            "Direct WhatsApp Assistance",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI)
                        "श्रम नवीकरण फारम भर्न वा बिल सम्बन्धी जानकारीका लागि सिधै सम्पर्क गर्नुहोस्:"
                    else
                        "For filling Shram Swikriti form or billing inquiries, chat directly with us:",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(
                        onClick = { openWhatsApp(context, "+97460099064") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = WhatsAppGreen,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WhatsApp CEO",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "CEO: Bibek",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Button(
                        onClick = { openWhatsApp(context, "+9779809364333") },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryNavy,
                            contentColor = Color.White
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WhatsApp Manager",
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Mgr: Jenita",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // 6. Non-Government Entity Disclaimer & Verified Sources (Play Policy Compliance)
        GovernmentDisclaimerCard(
            currentLanguage = currentLanguage,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // 7. Frequently Asked Questions Accordion (Shram Renewal FAQ)
        FaqSectionCard(
            faqList = PredefinedData.RENEWAL_FAQS,
            currentLanguage = currentLanguage
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
