package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.model.AppLanguage
import com.example.data.model.GuidanceStep
import com.example.ui.theme.AmberAlert
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun WizardStepCard(
    step: GuidanceStep,
    isCompleted: Boolean,
    onToggleCompleted: () -> Unit,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isCompleted) EmeraldSuccess.copy(alpha = 0.08f) else MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Step Badge Circle
                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(if (isCompleted) EmeraldSuccess else PrimaryCobalt),
                    contentAlignment = Alignment.Center
                ) {
                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Step Complete",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    } else {
                        Text(
                            text = "${step.stepNumber}",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "चरण ${step.stepNumber}: ${step.titleNe}" else "Step ${step.stepNumber}: ${step.titleEn}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = if (isCompleted) EmeraldSuccess else MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) step.summaryNe else step.summaryEn,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                IconButton(onClick = { isExpanded = !isExpanded }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                        contentDescription = "Toggle Step Details"
                    )
                }
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 12.dp)
                ) {
                    // Detailed Instructions List
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "बिस्तृत प्रक्रिया निर्देशिका:" else "Detailed Step Instructions:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    val instructions = if (currentLanguage == AppLanguage.NEPALI) step.detailedInstructionsNe else step.detailedInstructionsEn
                    instructions.forEachIndexed { index, instr ->
                        Row(
                            modifier = Modifier.padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "• ",
                                fontWeight = FontWeight.Bold,
                                color = PrimaryCobalt
                            )
                            Text(
                                text = instr,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Pro-Tips Box
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = AmberAlert.copy(alpha = 0.12f)
                    ) {
                        Column(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Lightbulb,
                                    contentDescription = "Pro Tip",
                                    tint = AmberAlert,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (currentLanguage == AppLanguage.NEPALI) "महत्वपूर्ण सुझाव (Pro Tips):" else "Important Pro Tips:",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = AmberAlert
                                )
                            }
                            val tips = if (currentLanguage == AppLanguage.NEPALI) step.proTipsNe else step.proTipsEn
                            tips.forEach { tip ->
                                Text(
                                    text = tip,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    modifier = Modifier.padding(top = 4.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // External Portal URL Action Button (if present)
                    step.actionUrl?.let { url ->
                        Button(
                            onClick = {
                                openWebUrl(context, url)
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryNavy)
                        ) {
                            Icon(
                                imageVector = Icons.Default.OpenInNew,
                                contentDescription = "Open Link",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = if (currentLanguage == AppLanguage.NEPALI) step.actionTextNe ?: "पोर्टल खोल्नुहोस्" else step.actionTextEn ?: "Open Portal",
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    // Completion Toggle Button
                    Button(
                        onClick = onToggleCompleted,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isCompleted) MaterialTheme.colorScheme.secondaryContainer else EmeraldSuccess
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "Mark Step Complete",
                            modifier = Modifier.size(18.dp),
                            tint = if (isCompleted) MaterialTheme.colorScheme.onSecondaryContainer else Color.White
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isCompleted) {
                                if (currentLanguage == AppLanguage.NEPALI) "चरण पूरा भयो (सच्याउन थिच्नुहोस्)" else "Completed (Click to Undo)"
                            } else {
                                if (currentLanguage == AppLanguage.NEPALI) "यो चरण पूरा भयो भनी चिन्ह लगाउनुहोस्" else "Mark Step as Completed"
                            },
                            fontWeight = FontWeight.Bold,
                            color = if (isCompleted) MaterialTheme.colorScheme.onSecondaryContainer else Color.White
                        )
                    }
                }
            }
        }
    }
}
