package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.components.DocumentCard
import com.example.ui.components.openWhatsApp
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

private val WhatsAppGreen = Color(0xFF25D366)

@Composable
fun ChecklistScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.language.collectAsState()
    val selectedCountry by viewModel.selectedCountry.collectAsState()
    val checklistStates by viewModel.checklistStates.collectAsState()
    val context = LocalContext.current

    val allDocs = PredefinedData.CHECKLIST_ITEMS
    val completedCount = allDocs.count { doc -> checklistStates[doc.id] == true }
    val totalCount = allDocs.size
    val progress = if (totalCount > 0) completedCount.toFloat() / totalCount else 0f

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
                imageVector = Icons.Default.AssignmentTurnedIn,
                contentDescription = "Checklist",
                tint = PrimaryCobalt,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "आवश्यक कागजात चेकलिस्ट" else "Required Documents Checklist",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryNavy
                )
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "FEIMS अनलाइन आवेदनको लागि पूर्व-तयारी" else "Pre-requisite documents for FEIMS online application",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // WhatsApp Documents Submission Notice Banner (App Submission Policy)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = WhatsAppGreen.copy(alpha = 0.08f)),
            border = BorderStroke(1.5.dp, WhatsAppGreen.copy(alpha = 0.6f))
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        shape = CircleShape,
                        color = WhatsAppGreen,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WhatsApp",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI)
                                "कागजातहरू हट्सएप (WhatsApp) मार्फत मात्र लिइनेछ"
                            else
                                "Documents will be taken through WhatsApp only",
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryNavy
                        )
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI)
                                "एपमा फाइल अपलोड हुँदैन। कृपया आवश्यक कागजातहरू हाम्रो आधिकारिक हट्सएपमा सिधै पठाउनुहोस्।"
                            else
                                "File uploading in-app is disabled. All documents must be sent directly via WhatsApp.",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = WhatsAppGreen.copy(alpha = 0.3f))
                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = {
                        openWhatsApp(context, "+97460099064")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Send via WhatsApp",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "कागजात पठाउन हट्सएप खोल्नुहोस् (+974 60099064)"
                        else
                            "Send Documents via WhatsApp (+974 60099064)",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Country Selector Context
        CountryDropdown(
            selectedCountry = selectedCountry,
            onCountrySelected = { viewModel.setSelectedCountry(it) },
            currentLanguage = currentLanguage,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Overview Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "कागजात तयारी अवस्था:" else "Document Readiness:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$completedCount / $totalCount ${if (currentLanguage == AppLanguage.NEPALI) "तयार भयो" else "Ready"} (${(progress * 100).toInt()}%)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (completedCount == totalCount) EmeraldSuccess else PrimaryCobalt
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = if (completedCount == totalCount) EmeraldSuccess else PrimaryCobalt,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Document List Items
        Text(
            text = if (currentLanguage == AppLanguage.NEPALI) "कागजातहरुको सूची (अन्तरक्रियात्मक):" else "Checklist Items (Interactive):",
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(8.dp))

        allDocs.forEach { doc ->
            val isChecked = checklistStates[doc.id] ?: false
            DocumentCard(
                document = doc,
                isCompleted = isChecked,
                onToggleCompleted = { viewModel.toggleChecklistItem(doc.id, isChecked) },
                onOpenScannerPreview = { title -> viewModel.openDocScannerPreview(title) },
                currentLanguage = currentLanguage
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Reset Checklist Button
        OutlinedButton(
            onClick = { viewModel.resetChecklist() },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(
                imageVector = Icons.Default.RestartAlt,
                contentDescription = "Reset Checklist",
                tint = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.padding(horizontal = 4.dp))
            Text(
                text = if (currentLanguage == AppLanguage.NEPALI) "चेकलिस्ट पुनः सुरु गर्नुहोस् (Reset)" else "Reset Checklist Progress",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
