package com.example.ui.components

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdd
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Payments
import androidx.compose.material.icons.filled.ReceiptLong
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.CostBreakdown
import com.example.ui.theme.AmberAlert
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun FeeSummaryCard(
    breakdown: CostBreakdown,
    currentLanguage: AppLanguage,
    onSaveEstimate: () -> Unit,
    onViewTaxInvoice: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Header Title + ESTIMATE ONLY Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "लागत तथा बिजक सारांश" else "Estimated Cost Summary",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Surface(
                    shape = RoundedCornerShape(4.dp),
                    color = Color.Transparent,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Text(
                        text = "ESTIMATE ONLY",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
            }

            Text(
                text = "FEE RECEIPT PREVIEW",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                letterSpacing = 1.2.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 2.dp, bottom = 12.dp)
            )

            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(10.dp))

            // 1. Welfare Fund Row
            FeeItemRow(
                label = if (currentLanguage == AppLanguage.NEPALI) "१. कल्याणकारी कोष (2 Years)" else "1. Welfare Fund (2 Years)",
                amountNpr = breakdown.welfareFundNpr
            )

            // 2. SSF Row
            if (breakdown.ssfContributionNpr > 0) {
                FeeItemRow(
                    label = if (currentLanguage == AppLanguage.NEPALI) "२. SSF (${breakdown.ssfMonths} महिना)" else "2. SSF (${breakdown.ssfMonths} month${if (breakdown.ssfMonths > 1) "s" else ""})",
                    amountNpr = breakdown.ssfContributionNpr
                )
            }

            // 3. Term Insurance Row
            FeeItemRow(
                label = if (currentLanguage == AppLanguage.NEPALI) "३. म्यादी जीवन बीमा (${breakdown.ageBandTextNe})" else "3. Term Insurance (${breakdown.ageBandTextEn})",
                amountNpr = breakdown.insurancePremiumNpr
            )

            Spacer(modifier = Modifier.height(8.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(8.dp))

            // Subtotal Government Charges
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "जम्मा सरकारी शुल्क (Govt Subtotal):" else "Subtotal Government Charges:",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryNavy
                )
                Text(
                    text = "रु. ${String.format("%,.0f", breakdown.totalGovtChargesNpr)}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryNavy
                )
            }

            // 4. Service Fee (if selected or added separately)
            if (breakdown.serviceFeeNpr > 0) {
                Spacer(modifier = Modifier.height(6.dp))
                FeeItemRow(
                    label = if (currentLanguage == AppLanguage.NEPALI) "४. हाम्रो सेवा शुल्क (Service Charge)" else "4. Our Service Charge",
                    amountNpr = breakdown.serviceFeeNpr,
                    isHighlight = true
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            Spacer(modifier = Modifier.height(10.dp))

            // Total Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "कुल जम्मा रकम (TOTAL)" else "TOTAL PAYABLE",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = if (breakdown.serviceFeeNpr > 0)
                            if (currentLanguage == AppLanguage.NEPALI) "सरकारी शुल्क + सेवा शुल्क" else "Govt Fees + Rs. 500 Service Fee"
                        else
                            if (currentLanguage == AppLanguage.NEPALI) "सरकारी शुल्क मात्र" else "Govt Fees Only",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Text(
                    text = "रु. ${String.format("%,.0f", breakdown.totalNpr)}",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFFB45309) // Amber/Red text color
                )
            }

            // Disclaimer Notice
            Text(
                text = if (currentLanguage == AppLanguage.NEPALI)
                    "Δ यो estimate हो। Final payable amount FEIMS payment screen अनुसार हुनेछ।"
                else
                    "Δ This is an estimate. Final payable amount will be as per the FEIMS payment screen.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 4.dp, bottom = 12.dp)
            )

            // Button 1: View Proper Tax Invoice (कर बिजक)
            Button(
                onClick = onViewTaxInvoice,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF047857), // IRD Nepal Green
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.ReceiptLong,
                    contentDescription = "View Tax Invoice",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "कर बिजक हेर्नुहोस् (View Tax Invoice)" else "View Official Tax Invoice",
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Button 2: Save to History
            OutlinedButton(
                onClick = onSaveEstimate,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.BookmarkAdd,
                    contentDescription = "Save Estimate",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "यो हिसाब सेभ गर्नुहोस्" else "Save Estimate to History",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun FeeItemRow(
    label: String,
    amountNpr: Double,
    isHighlight: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = if (isHighlight) PrimaryNavy else MaterialTheme.colorScheme.onSurface,
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Medium
        )

        Text(
            text = "रु. ${String.format("%,.0f", amountNpr)}",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isHighlight) FontWeight.ExtraBold else FontWeight.Bold,
            color = if (isHighlight) Color(0xFF047857) else MaterialTheme.colorScheme.onSurface
        )
    }
}
