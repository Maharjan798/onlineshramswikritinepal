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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DocumentScanner
import androidx.compose.material.icons.filled.PhotoCamera
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.AppLanguage
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

@Composable
fun DocumentScanModal(
    documentTitle: String,
    onDismiss: () -> Unit,
    currentLanguage: AppLanguage
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.DocumentScanner,
                            contentDescription = "Scan Helper",
                            tint = PrimaryCobalt,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI) "कागजात निर्देशिका" else "Document Guide",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = PrimaryNavy.copy(alpha = 0.08f)
                ) {
                    Text(
                        text = documentTitle,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy,
                        modifier = Modifier.padding(12.dp)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Important Policy Note: WhatsApp only
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = WhatsAppGreen.copy(alpha = 0.12f)
                ) {
                    Row(
                        modifier = Modifier.padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WhatsApp Notice",
                            tint = WhatsAppGreen,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI)
                                "सूचना: कागजातहरू हट्सएप (WhatsApp) मार्फत मात्र लिइनेछ।"
                            else
                                "Notice: Documents will be taken through WhatsApp only.",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryNavy
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Scan Guidelines Bullet Points
                ScanRequirementRow(
                    title = if (currentLanguage == AppLanguage.NEPALI) "फाइल साइज सीमा:" else "Max File Size:",
                    desc = if (currentLanguage == AppLanguage.NEPALI) "५०० KB भन्दा सानो हुनुपर्छ (Photo: ३०० KB)" else "Strictly under 500 KB (Photo < 300 KB)"
                )
                ScanRequirementRow(
                    title = if (currentLanguage == AppLanguage.NEPALI) "स्वीकृत ढाँचा:" else "Allowed Formats:",
                    desc = "JPG, PNG, PDF"
                )
                ScanRequirementRow(
                    title = if (currentLanguage == AppLanguage.NEPALI) "फोटो ब्याकग्राउन्ड:" else "Photo Background:",
                    desc = if (currentLanguage == AppLanguage.NEPALI) "सेतो पृष्ठभूमि (White Background), टोपी/चश्मा नभएको" else "Solid White background, clear face, no dark glasses"
                )
                ScanRequirementRow(
                    title = if (currentLanguage == AppLanguage.NEPALI) "अक्षरको स्पष्टता:" else "Legibility:",
                    desc = if (currentLanguage == AppLanguage.NEPALI) "राहदानी नम्बर र नाम प्रष्ट पढिनुपर्छ" else "Passport number, dates & signatures fully readable"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Send this document via WhatsApp
                Button(
                    onClick = {
                        val msg = "Hello Bibek Sir, I am sending my document: $documentTitle for Shram Swikriti renewal."
                        val encoded = android.net.Uri.encode(msg)
                        val url = "https://wa.me/97460099064?text=$encoded"
                        val intent = android.content.Intent(android.content.Intent.ACTION_VIEW, android.net.Uri.parse(url))
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            android.widget.Toast.makeText(context, "Could not open WhatsApp", android.widget.Toast.LENGTH_SHORT).show()
                        }
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
                        text = if (currentLanguage == AppLanguage.NEPALI) "हट्सएपमा यो कागजात पठाउनुहोस्" else "Send Document via WhatsApp",
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp)
                ) {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "बन्द गर्नुहोस् (Close)" else "Close Guide",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun ScanRequirementRow(
    title: String,
    desc: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = "Check",
            tint = EmeraldSuccess,
            modifier = Modifier.size(18.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = desc,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
