package com.example.ui.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import com.example.data.model.AppLanguage
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

/**
 * Prominent Government Entity Disclaimer & Verified Sources Component
 *
 * Explicitly states that this application:
 * 1. Is NOT affiliated with, authorized by, or representing any government entity,
 *    including the Department of Foreign Employment (DOFE), Ministry of Labour, Employment and Social Security,
 *    or the Government of Nepal.
 * 2. Is an independent facilitation tool and calculator provided by Digital Solution.
 * 3. Attributes official government information sources to DOFE and FEIMS.
 */
@Composable
fun GovernmentDisclaimerCard(
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(1.5.dp, Color(0xFFDC2626).copy(alpha = 0.45f)),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header with Alert Badge
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFFDC2626).copy(alpha = 0.15f),
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Shield,
                        contentDescription = "Government Entity Disclaimer",
                        tint = Color(0xFFDC2626),
                        modifier = Modifier
                            .padding(8.dp)
                            .size(20.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "सरकारी निकाय नभएको सूचना (GOVERNMENT DISCLAIMER)"
                        else
                            "GOVERNMENT ENTITY DISCLAIMER",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFDC2626),
                        fontSize = 12.sp
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "वैदेशिक रोजगार विभाग (DOFE) वा सरकारी सम्बद्धता नभएको घोषणा"
                        else
                            "Not Affiliated with or Authorized by Government / DOFE",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Main Disclaimer Text
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                color = Color(0xFFFEF2F2),
                border = BorderStroke(1.dp, Color(0xFFFCA5A5))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "घोषणा (DISCLAIMER): यो एप नेपाल सरकार, श्रम, रोजगार तथा सामाजिक सुरक्षा मन्त्रालय, वा वैदेशिक रोजगार विभाग (DOFE) को आधिकारिक एप होइन र कुनै सरकारी निकायको प्रतिनिधित्व गर्दैन। यो डिजिटल सोलुसनद्वारा श्रमिकहरूको सहजताको लागि उपलब्ध गराइएको स्वतन्त्र शुल्क क्याल्कुलेटर तथा परामर्श सहयोग एप मात्र हो।"
                        else
                            "DISCLAIMER: This application DOES NOT represent, and is NOT affiliated with, endorsed by, or authorized by the Government of Nepal, the Ministry of Labour, Employment and Social Security, or the Department of Foreign Employment (DOFE). It is an independent advisory and calculation facilitation service provided by Digital Solution.",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF991B1B),
                        lineHeight = 18.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Purpose & Independent facilitation note
            Text(
                text = if (currentLanguage == AppLanguage.NEPALI)
                    "श्रम स्वीकृति नेपाल नेपाली आप्रवासी कामदारहरूलाई वैदेशिक रोजगार नवीकरण शुल्क अनुमान गर्न, आवश्यक कागजातहरू बुझ्न र सिधै हट्सएप मार्फत परामर्श प्राप्त गर्न सहयोग गर्ने स्वतन्त्र औजार हो।"
                else
                    "Shram Swikriti Nepal is an independent facilitation tool and calculator designed to assist Nepali migrant workers in estimating foreign employment renewal fees, understanding required documents, and getting direct WhatsApp consultation.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 11.sp,
                lineHeight = 16.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            Spacer(modifier = Modifier.height(10.dp))

            // Official Government Sources Section
            Text(
                text = if (currentLanguage == AppLanguage.NEPALI)
                    "आधिकारिक सरकारी सूचना स्रोतहरू (Official Sources of Information):"
                else
                    "Official Government Sources of Information:",
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = PrimaryNavy
            )

            Text(
                text = if (currentLanguage == AppLanguage.NEPALI)
                    "यस एपमा देखाइएका सबै सरकारी शुल्क, नियम तथा प्रक्रियाहरू नेपाल सरकार, श्रम तथा रोजगार मन्त्रालय र वैदेशिक रोजगार विभाग (DOFE / FEIMS) का आधिकारिक मापदण्डहरूमा आधारित छन्।"
                else
                    "All government rates, rules, and procedures displayed in this app are based on published guidelines from the Government of Nepal, Ministry of Labour, and the Department of Foreign Employment (DOFE / FEIMS).",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(top = 2.dp, bottom = 8.dp)
            )

            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.2f))
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "१. वैदेशिक रोजगार विभाग (DOFE) - श्रम तथा रोजगार मन्त्रालय, नेपाल सरकार"
                        else
                            "1. Department of Foreign Employment (DOFE), Government of Nepal",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "२. FEIMS वैदेशिक रोजगार सूचना व्यवस्थापन प्रणाली"
                        else
                            "2. FEIMS - Foreign Employment Information Management System",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                }
            }
        }
    }
}
