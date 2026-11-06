package com.example.ui.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
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
import androidx.compose.material.icons.filled.Badge
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneInTalk
import androidx.compose.material.icons.filled.SupportAgent
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

val WhatsAppGreen = Color(0xFF25D366)
val WhatsAppDark = Color(0xFF075E54)

@Composable
fun ContactUsSection(
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxWidth()) {
        // Section Header Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = WhatsAppDark),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = CircleShape,
                    color = WhatsAppGreen,
                    modifier = Modifier.size(48.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.HeadsetMic,
                        contentDescription = "Contact Support",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(14.dp))

                Column {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "हामीलाई सम्पर्क गर्नुहोस्" else "Contact Us & Assistance",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "हट्सएप तथा कल सेवा उपलब्ध (WhatsApp Integrated)"
                        else
                            "Direct WhatsApp & Telephone Support Available",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // 1. CEO Contact Profile Card
        ContactProfileCard(
            roleEn = "CEO",
            roleNe = "प्रमुख कार्यकारी अधिकृत (CEO)",
            name = "Bibek Maharjan",
            primaryPhone = "+974 60099064",
            primaryLocation = "Qatar / कतार",
            secondaryPhone = "+977 9865385124",
            secondaryLocation = "Nepal / नेपाल",
            currentLanguage = currentLanguage,
            onWhatsAppClick = { number -> openWhatsApp(context, number) },
            onCallClick = { number -> openPhoneDialer(context, number) },
            onCopyClick = { number -> copyToClipboard(context, number) }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // 2. Manager Contact Profile Card
        ContactProfileCard(
            roleEn = "Manager",
            roleNe = "व्यवस्थापक (Manager)",
            name = "Jenita Maharjan",
            primaryPhone = "+977 9809364333",
            primaryLocation = "Nepal / नेपाल",
            secondaryPhone = null,
            secondaryLocation = null,
            currentLanguage = currentLanguage,
            onWhatsAppClick = { number -> openWhatsApp(context, number) },
            onCallClick = { number -> openPhoneDialer(context, number) },
            onCopyClick = { number -> copyToClipboard(context, number) }
        )
    }
}

@Composable
private fun ContactProfileCard(
    roleEn: String,
    roleNe: String,
    name: String,
    primaryPhone: String,
    primaryLocation: String,
    secondaryPhone: String?,
    secondaryLocation: String?,
    currentLanguage: AppLanguage,
    onWhatsAppClick: (String) -> Unit,
    onCallClick: (String) -> Unit,
    onCopyClick: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header: Name & Role Badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Surface(
                        shape = CircleShape,
                        color = PrimaryNavy.copy(alpha = 0.12f),
                        modifier = Modifier.size(40.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = name,
                            tint = PrimaryNavy,
                            modifier = Modifier
                                .padding(10.dp)
                                .size(20.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = name,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryNavy
                        )
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI) roleNe else roleEn,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryCobalt
                        )
                    }
                }

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = WhatsAppGreen.copy(alpha = 0.15f)
                ) {
                    Text(
                        text = "WhatsApp Active",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = WhatsAppDark,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.25f))
            Spacer(modifier = Modifier.height(10.dp))

            // Primary Phone Item
            PhoneActionItem(
                phone = primaryPhone,
                locationTag = primaryLocation,
                currentLanguage = currentLanguage,
                onWhatsAppClick = { onWhatsAppClick(primaryPhone) },
                onCallClick = { onCallClick(primaryPhone) },
                onCopyClick = { onCopyClick(primaryPhone) }
            )

            // Secondary Phone Item (if any)
            secondaryPhone?.let { secPhone ->
                Spacer(modifier = Modifier.height(10.dp))
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.15f))
                Spacer(modifier = Modifier.height(10.dp))

                PhoneActionItem(
                    phone = secPhone,
                    locationTag = secondaryLocation ?: "",
                    currentLanguage = currentLanguage,
                    onWhatsAppClick = { onWhatsAppClick(secPhone) },
                    onCallClick = { onCallClick(secPhone) },
                    onCopyClick = { onCopyClick(secPhone) }
                )
            }
        }
    }
}

@Composable
private fun PhoneActionItem(
    phone: String,
    locationTag: String,
    currentLanguage: AppLanguage,
    onWhatsAppClick: () -> Unit,
    onCallClick: () -> Unit,
    onCopyClick: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = phone,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                if (locationTag.isNotBlank()) {
                    Text(
                        text = locationTag,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            IconButton(onClick = onCopyClick) {
                Icon(
                    imageVector = Icons.Default.ContentCopy,
                    contentDescription = "Copy number",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(18.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // WhatsApp Link Button
            Button(
                onClick = onWhatsAppClick,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "WhatsApp",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "हट्सएप च्याट" else "WhatsApp",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }

            // Direct Call Button
            OutlinedButton(
                onClick = onCallClick,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "Phone Call",
                    modifier = Modifier.size(16.dp),
                    tint = PrimaryCobalt
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "सिधा कल" else "Call",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = PrimaryCobalt
                )
            }
        }
    }
}

fun openWhatsApp(context: Context, phoneNumber: String) {
    // Clean phone number: remove spaces and non-digits
    val cleanedNumber = phoneNumber.replace("+", "").replace(" ", "").replace("-", "")
    val url = "https://wa.me/$cleanedNumber"
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open WhatsApp link", Toast.LENGTH_SHORT).show()
    }
}

fun openPhoneDialer(context: Context, phoneNumber: String) {
    val clean = phoneNumber.replace(" ", "")
    val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$clean"))
    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open dialer", Toast.LENGTH_SHORT).show()
    }
}

fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("Copied Text", text)
    clipboard.setPrimaryClip(clip)
    Toast.makeText(context, "$text copied to clipboard", Toast.LENGTH_SHORT).show()
}

fun openWebUrl(context: Context, url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    } catch (e: Exception) {
        Toast.makeText(context, "Could not open link: $url", Toast.LENGTH_SHORT).show()
    }
}

