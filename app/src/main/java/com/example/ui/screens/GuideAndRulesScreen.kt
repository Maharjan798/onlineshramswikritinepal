package com.example.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.theme.PrimaryCobalt

@Composable
fun GuideAndRulesScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.language.collectAsState()
    var selectedSubTab by remember { mutableStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {
        TabRow(
            selectedTabIndex = selectedSubTab,
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor = PrimaryCobalt,
            indicator = { tabPositions ->
                TabRowDefaults.SecondaryIndicator(
                    modifier = Modifier.tabIndicatorOffset(tabPositions[selectedSubTab]),
                    color = PrimaryCobalt
                )
            }
        ) {
            Tab(
                selected = selectedSubTab == 0,
                onClick = { selectedSubTab = 0 },
                text = {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "५-चरण निर्देशिका (Guide)" else "5-Step Guide",
                        fontWeight = if (selectedSubTab == 0) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
            Tab(
                selected = selectedSubTab == 1,
                onClick = { selectedSubTab = 1 },
                text = {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "देशगत नियम (Rules)" else "Country Rules",
                        fontWeight = if (selectedSubTab == 1) FontWeight.Bold else FontWeight.Normal
                    )
                }
            )
        }

        when (selectedSubTab) {
            0 -> WizardScreen(viewModel = viewModel)
            1 -> CountryRulesScreen(viewModel = viewModel)
        }
    }
}
