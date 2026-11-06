package com.example.ui.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.datasource.PredefinedData
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.components.WizardStepCard
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun WizardScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.language.collectAsState()
    val completedSteps by viewModel.completedSteps.collectAsState()
    val context = LocalContext.current

    val steps = PredefinedData.GUIDANCE_STEPS
    val totalSteps = steps.size
    val completedCount = completedSteps.size
    val progress = if (totalSteps > 0) completedCount.toFloat() / totalSteps else 0f

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Hero Banner Illustration
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.shram_banner_1784992326660),
                contentDescription = "Shram Swikriti Guidance Banner",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        // Guidance Title Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.AltRoute,
                contentDescription = "Guidance Walkthrough",
                tint = PrimaryCobalt,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "FEIMS अनलाइन नवीकरण प्रक्रिया" else "FEIMS Online Renewal Walkthrough",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryNavy
                )
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "चरणबद्ध ५-दिग्दर्शन निर्देशिका" else "Step-by-step 5-stage interactive guide",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // Progress Overview
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
                        text = if (currentLanguage == AppLanguage.NEPALI) "प्रक्रिया प्रगति:" else "Process Progress:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$completedCount / $totalSteps ${if (currentLanguage == AppLanguage.NEPALI) "चरण पूरा" else "Steps Done"}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = if (completedCount == totalSteps) EmeraldSuccess else PrimaryCobalt
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    color = if (completedCount == totalSteps) EmeraldSuccess else PrimaryCobalt,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // 5 Step Cards
        steps.forEach { step ->
            val isStepDone = completedSteps.contains(step.stepNumber)
            WizardStepCard(
                step = step,
                isCompleted = isStepDone,
                onToggleCompleted = { viewModel.toggleStepCompleted(step.stepNumber) },
                currentLanguage = currentLanguage
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
