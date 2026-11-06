package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.FaqItem
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun FaqSectionCard(
    faqList: List<FaqItem>,
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    // Track expanded state for each FAQ item by id
    val expandedStates = remember { mutableStateMapOf<String, Boolean>() }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header Title
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = PrimaryCobalt.copy(alpha = 0.1f),
                    modifier = Modifier.size(38.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.HelpOutline,
                        contentDescription = "FAQ Icon",
                        tint = PrimaryCobalt,
                        modifier = Modifier
                            .padding(8.dp)
                            .size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "सोधिने प्रश्नहरू (Frequently Asked Questions)" else "Frequently Asked Questions (FAQ)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "पुनः श्रम स्वीकृतिका जिज्ञासा र उत्तरहरू" else "Clear answers for online Shram renewal queries",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Accordion Items
            faqList.forEachIndexed { index, faq ->
                val isExpanded = expandedStates[faq.id] ?: (index == 0) // Default first item expanded

                FaqAccordionItem(
                    faq = faq,
                    isExpanded = isExpanded,
                    currentLanguage = currentLanguage,
                    onToggleExpand = {
                        expandedStates[faq.id] = !isExpanded
                    },
                    modifier = Modifier.testTag("faq_item_${faq.id}")
                )

                if (index < faqList.size - 1) {
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
private fun FaqAccordionItem(
    faq: FaqItem,
    isExpanded: Boolean,
    currentLanguage: AppLanguage,
    onToggleExpand: () -> Unit,
    modifier: Modifier = Modifier
) {
    val rotationDegrees by animateFloatAsState(
        targetValue = if (isExpanded) 180f else 0f,
        label = "arrow_rotation"
    )

    val questionText = if (currentLanguage == AppLanguage.NEPALI) faq.questionNe else faq.questionEn
    val answerText = if (currentLanguage == AppLanguage.NEPALI) faq.answerNe else faq.answerEn

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 1.dp,
                color = if (isExpanded) PrimaryCobalt.copy(alpha = 0.5f) else MaterialTheme.colorScheme.outline.copy(alpha = 0.25f),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onToggleExpand() },
        color = if (isExpanded) PrimaryCobalt.copy(alpha = 0.04f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp)
        ) {
            // Question Header Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = questionText,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = if (isExpanded) FontWeight.Bold else FontWeight.SemiBold,
                    color = if (isExpanded) PrimaryNavy else MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier.weight(1f)
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse FAQ" else "Expand FAQ",
                    tint = if (isExpanded) PrimaryCobalt else MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .size(24.dp)
                        .rotate(rotationDegrees)
                )
            }

            // Expanded Answer Body
            AnimatedVisibility(
                visible = isExpanded,
                enter = expandVertically() + fadeIn(),
                exit = shrinkVertically() + fadeOut()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    HorizontalDivider(
                        color = PrimaryCobalt.copy(alpha = 0.15f),
                        thickness = 1.dp
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(12.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Lightbulb,
                            contentDescription = "Tip",
                            tint = Color(0xFFD97706),
                            modifier = Modifier
                                .size(20.dp)
                                .padding(top = 2.dp)
                        )

                        Spacer(modifier = Modifier.width(10.dp))

                        Text(
                            text = answerText,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            lineHeight = 20.sp
                        )
                    }
                }
            }
        }
    }
}
