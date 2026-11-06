package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AltRoute
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.components.DocumentScanModal
import com.example.ui.components.TopAppBarWithLanguage
import com.example.ui.screens.BillingScreen
import com.example.ui.screens.CalculatorScreen
import com.example.ui.screens.ChecklistScreen
import com.example.ui.screens.GuideAndRulesScreen
import com.example.ui.screens.HistoryScreen
import com.example.ui.theme.PrimaryBlueLight
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy
import com.example.ui.theme.ShramSwikritiTheme

data class NavTabItem(
    val titleEn: String,
    val titleNe: String,
    val icon: ImageVector
)

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ShramSwikritiTheme {
                val currentLanguage by viewModel.language.collectAsState()
                val selectedTab by viewModel.selectedTab.collectAsState()
                val scannedDocTitle by viewModel.scannedDocTitle.collectAsState()

                val tabs = listOf(
                    NavTabItem("Calculator", "लागत", Icons.Default.Calculate),
                    NavTabItem("Tax Invoice", "कर बिजक", Icons.Default.ReceiptLong),
                    NavTabItem("Checklist", "कागजात", Icons.Default.AssignmentTurnedIn),
                    NavTabItem("Guide", "निर्देशिका", Icons.Default.AltRoute),
                    NavTabItem("Contact Us", "सम्पर्क", Icons.Default.HeadsetMic)
                )

                Scaffold(
                    topBar = {
                        TopAppBarWithLanguage(
                            currentLanguage = currentLanguage,
                            onToggleLanguage = { viewModel.toggleLanguage() }
                        )
                    },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.surface,
                            tonalElevation = 2.dp
                        ) {
                            tabs.forEachIndexed { index, tab ->
                                NavigationBarItem(
                                    selected = selectedTab == index,
                                    onClick = { viewModel.setSelectedTab(index) },
                                    icon = {
                                        Icon(
                                            imageVector = tab.icon,
                                            contentDescription = tab.titleEn
                                        )
                                    },
                                    label = {
                                        Text(
                                            text = if (currentLanguage == AppLanguage.NEPALI) tab.titleNe else tab.titleEn,
                                            fontSize = 11.sp,
                                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                                        )
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = PrimaryCobalt,
                                        selectedTextColor = PrimaryCobalt,
                                        indicatorColor = PrimaryBlueLight,
                                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                                    )
                                )
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        when (selectedTab) {
                            0 -> CalculatorScreen(
                                viewModel = viewModel,
                                onNavigateToBilling = { viewModel.setSelectedTab(1) }
                            )
                            1 -> BillingScreen(viewModel = viewModel)
                            2 -> ChecklistScreen(viewModel = viewModel)
                            3 -> GuideAndRulesScreen(viewModel = viewModel)
                            4 -> HistoryScreen(viewModel = viewModel)
                        }

                        // Document Quality Scan Helper Dialog
                        scannedDocTitle?.let { title ->
                            DocumentScanModal(
                                documentTitle = title,
                                onDismiss = { viewModel.closeDocScannerPreview() },
                                currentLanguage = currentLanguage
                            )
                        }
                    }
                }
            }
        }
    }
}
