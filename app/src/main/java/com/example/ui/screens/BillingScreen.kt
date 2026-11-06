package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AssignmentTurnedIn
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Payment
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.MainViewModel
import com.example.ui.components.QrPaymentSection
import com.example.ui.components.WhatsAppGreen
import com.example.ui.components.copyToClipboard
import com.example.ui.components.openWhatsApp
import com.example.ui.theme.EmeraldSuccess
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BillingScreen(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentLanguage by viewModel.language.collectAsState()
    val costBreakdown by viewModel.costBreakdown.collectAsState()
    val selectedCountry by viewModel.selectedCountry.collectAsState()
    val age by viewModel.age.collectAsState()
    val contractYears by viewModel.contractYears.collectAsState()
    val ssfMonths by viewModel.ssfMonths.collectAsState()

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Customer Editable details
    var customerName by remember { mutableStateOf("Nepali Worker") }
    var passportNumber by remember { mutableStateOf("PA0987654") }
    var dateOfBirth by remember { mutableStateOf("1996-05-15") }
    var isEditingCustomer by remember { mutableStateOf(false) }

    // Constant Nepal Tax Invoice Parameters
    val invoiceNo = "INV-2081/82-0482"
    val panNumber = "111438110"
    val currentDateBs = "२०८१ असोज १९"
    val currentDateAd = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

    // Calculated amounts
    val welfareFund = costBreakdown.welfareFundNpr
    val insuranceFee = costBreakdown.insurancePremiumNpr
    val ssfFee = costBreakdown.ssfContributionNpr
    val totalGovtCharges = welfareFund + insuranceFee + ssfFee
    val serviceCharge = 700.0 // Service charge: Rs. 700
    val grandTotalPayable = totalGovtCharges + serviceCharge

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        // Section Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ReceiptLong,
                contentDescription = "Tax Invoice",
                tint = PrimaryCobalt,
                modifier = Modifier.padding(end = 8.dp)
            )
            Column {
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "कर बिजक (TAX INVOICE)" else "TAX INVOICE & BILLING",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryNavy
                )
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI)
                        "नेपाल सरकारको कानुन तथा आन्तरिक राजस्व नियमावली बमोजिम"
                    else
                        "As per Inland Revenue Department (IRD) Nepal Regulations",
                    style = MaterialTheme.typography.bodySmall,
                    color = PrimaryCobalt,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        // Editable Customer / Worker Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "सेवाग्राही / कामदारको विवरण:" else "Applicant / Worker Details:",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                    IconButton(onClick = { isEditingCustomer = !isEditingCustomer }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Customer Details",
                            tint = PrimaryCobalt,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }

                if (isEditingCustomer) {
                    OutlinedTextField(
                        value = customerName,
                        onValueChange = { customerName = it },
                        label = { Text("Worker / Applicant Name") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = passportNumber,
                        onValueChange = { passportNumber = it },
                        label = { Text("Passport Number") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = dateOfBirth,
                        onValueChange = { dateOfBirth = it },
                        label = { Text("Date of Birth / जन्म मिति (YYYY-MM-DD)") },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.CalendarToday,
                                contentDescription = "Date of Birth",
                                tint = PrimaryCobalt,
                                modifier = Modifier.size(18.dp)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        onClick = { isEditingCustomer = false },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryNavy)
                    ) {
                        Text("Save Details")
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = customerName,
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = "Passport No: $passportNumber",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "DOB / जन्म मिति: $dateOfBirth",
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold,
                                color = PrimaryCobalt
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = "${selectedCountry.flagEmoji} ${if (currentLanguage == AppLanguage.NEPALI) selectedCountry.nameNe else selectedCountry.nameEn}",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = PrimaryCobalt
                            )
                            Text(
                                text = "$contractYears Years | Age: $age",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // THE OFFICIAL NEPAL TAX INVOICE CARD
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFCBD5E1))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            ) {
                // 1. Tax Invoice Registered Header
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "कर बिजक (TAX INVOICE)",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "मूल्य अभिवृद्धि कर ऐन, २०५२ को अनुसूची-५ बमोजिम",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF64748B),
                        fontWeight = FontWeight.Medium
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Shram Swikriti & Foreign Employment Solutions",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                    Text(
                        text = "Kathmandu, Nepal",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = "PAN / स्थायी लेखा नं.: $panNumber",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF047857)
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.5.dp)
                Spacer(modifier = Modifier.height(10.dp))

                // Invoice Meta Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "बिजक नं. (Invoice No): $invoiceNo",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = "सेवाग्राही (Client): $customerName",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF334155)
                        )
                        Text(
                            text = "राहदानी (Passport): $passportNumber",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF334155)
                        )
                        Text(
                            text = "जन्म मिति (DOB): $dateOfBirth",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF334155)
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "मिति (BS): $currentDateBs",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = "Date (AD): $currentDateAd",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF64748B)
                        )
                        Text(
                            text = "गन्तव्य: ${selectedCountry.nameEn}",
                            style = MaterialTheme.typography.bodySmall,
                            fontWeight = FontWeight.SemiBold,
                            color = PrimaryCobalt
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
                HorizontalDivider(color = Color(0xFFE2E8F0))
                Spacer(modifier = Modifier.height(10.dp))

                // TABLE HEADER
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF1F5F9), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "विवरण (PARTICULARS)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        modifier = Modifier.weight(2f)
                    )
                    Text(
                        text = "प्रकृति (TYPE)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF475569),
                        modifier = Modifier.weight(1.3f)
                    )
                    Text(
                        text = "रकम (NPR)",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        textAlign = TextAlign.End,
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // LINE ITEMS:
                // Section 1: Government Statutory Charges (गैर-कर सरकारी दस्तुर)
                InvoiceLineItem(
                    index = "१.",
                    title = "वैदेशिक रोजगार कल्याणकारी कोष\n(Foreign Employment Welfare Fund)",
                    category = "सरकारी गैर-कर",
                    amountNpr = welfareFund
                )

                InvoiceLineItem(
                    index = "२.",
                    title = "म्यादी जीवन बीमा प्रिमियम\n(Term Life Insurance - $contractYears Yrs)",
                    category = "सरकारी गैर-कर",
                    amountNpr = insuranceFee
                )

                InvoiceLineItem(
                    index = "३.",
                    title = "सामाजिक सुरक्षा कोष योगदान\n(Social Security Fund - $ssfMonths Mo)",
                    category = "सरकारी गैर-कर",
                    amountNpr = ssfFee
                )

                // Subtotal Government Charges
                HorizontalDivider(color = Color(0xFFE2E8F0), thickness = 1.dp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "जम्मा सरकारी शुल्क (Total Govt. Charges):",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F172A)
                    )
                    Text(
                        text = "रु. ${String.format("%,.2f", totalGovtCharges)}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                }
                HorizontalDivider(color = Color(0xFFCBD5E1), thickness = 1.5.dp)

                Spacer(modifier = Modifier.height(8.dp))

                // Section 2: Service Charge (हाम्रो सेवा शुल्क - added separately as explicitly instructed!)
                InvoiceLineItem(
                    index = "४.",
                    title = "अनलाइन फारम तथा परामर्श सेवा शुल्क\n(Agency Service Charge - Bibek Maharjan)",
                    category = "हाम्रो सेवा शुल्क",
                    amountNpr = serviceCharge,
                    isHighlight = true
                )

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color(0xFF94A3B8), thickness = 1.5.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // TAX & SUMMARY BREAKDOWN AS PER NEPAL IRD RULES
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "अकरयोग्य सरकारी रकम (Non-Taxable Govt. Fees):",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = "रु. ${String.format("%,.2f", totalGovtCharges)}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E293B)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "करयोग्य सेवा शुल्क (Taxable Service Charge):",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = "रु. ${String.format("%,.2f", serviceCharge)}",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E293B)
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "मूल्य अभिवृद्धि कर (VAT 13% on Service Fee):",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF475569)
                    )
                    Text(
                        text = "रु. ०.०० (PAN Bill Exempt)",
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold,
                        color = Color(0xFF1E293B)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))
                HorizontalDivider(color = Color(0xFF0F172A), thickness = 2.dp)
                Spacer(modifier = Modifier.height(8.dp))

                // GRAND TOTAL ROW
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFEF3C7), RoundedCornerShape(8.dp))
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "कुल जम्मा रकम (GRAND TOTAL)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF78350F)
                        )
                        Text(
                            text = "Govt. Fees + Rs. 700 Service Fee",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF92400E)
                        )
                    }

                    Text(
                        text = "रु. ${String.format("%,.0f", grandTotalPayable)}",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFFB45309)
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Words breakdown
                Text(
                    text = "अक्षरेपी: रु. ${numberToNepaliWords(grandTotalPayable.toInt())} मात्र।",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF334155),
                    modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Stamp & Authorized Signature Box
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    // Official Stamp Box
                    Surface(
                        modifier = Modifier
                            .border(1.dp, Color(0xFF15803D), RoundedCornerShape(8.dp))
                            .padding(8.dp),
                        color = Color(0xFFDCFCE7).copy(alpha = 0.5f)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = "Verified Tax Invoice",
                                tint = Color(0xFF15803D),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = "VERIFIED INVOICE",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF15803D)
                            )
                            Text(
                                text = "PAN: $panNumber",
                                style = MaterialTheme.typography.labelSmall,
                                fontSize = 9.sp,
                                color = Color(0xFF15803D)
                            )
                        }
                    }

                    // Authorized Signatory
                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = "Bibek Maharjan",
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryNavy
                        )
                        Text(
                            text = "CEO / Authorized Signatory",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color(0xFF475569)
                        )
                        Text(
                            text = "कम्प्युटर बिजक (हस्ताक्षर आवश्यक नपर्ने)",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 8.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // WhatsApp Documents Policy Banner
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            color = WhatsAppGreen.copy(alpha = 0.1f),
            border = androidx.compose.foundation.BorderStroke(1.dp, WhatsAppGreen.copy(alpha = 0.5f))
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "WhatsApp Notice",
                    tint = WhatsAppGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "कागजातहरू हट्सएप (WhatsApp) मार्फत मात्र लिइनेछ"
                        else
                            "Documents will be taken through WhatsApp only",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        color = PrimaryNavy
                    )
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI)
                            "एपमा फाइल अपलोड हुँदैन। भुक्तानी रसिद र कागजातहरू हट्सएपमा सिधै पठाउनुहोस्।"
                        else
                            "In-app file upload is not supported. Please send payment proof and documents via WhatsApp.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(14.dp))

        // INVOICE ACTION BUTTONS
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Share Tax Invoice
            OutlinedButton(
                onClick = {
                    shareInvoiceText(
                        context = context,
                        invoiceNo = invoiceNo,
                        panNumber = panNumber,
                        customerName = customerName,
                        passportNo = passportNumber,
                        dob = dateOfBirth,
                        country = selectedCountry.nameEn,
                        welfareFund = welfareFund,
                        insurance = insuranceFee,
                        ssf = ssfFee,
                        govtTotal = totalGovtCharges,
                        serviceCharge = serviceCharge,
                        grandTotal = grandTotalPayable
                    )
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "Share",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "बिजक सेयर" else "Share Bill",
                    fontWeight = FontWeight.Bold
                )
            }

            // WhatsApp Direct with CEO
            Button(
                onClick = {
                    val invoiceMsg = """
                        *TAX INVOICE - SHRAM SWIKRITI NEPAL*
                        Invoice No: $invoiceNo
                        PAN: $panNumber
                        Applicant: $customerName (PP: $passportNumber)
                        DOB: $dateOfBirth
                        Country: ${selectedCountry.nameEn}
                        ------------------------------------
                        1. Welfare Fund: Rs. ${String.format("%,.0f", welfareFund)}
                        2. Insurance: Rs. ${String.format("%,.0f", insuranceFee)}
                        3. SSF: Rs. ${String.format("%,.0f", ssfFee)}
                        *Subtotal Govt Charges: Rs. ${String.format("%,.0f", totalGovtCharges)}*
                        *Service Charge: Rs. 700*
                        ------------------------------------
                        *GRAND TOTAL: Rs. ${String.format("%,.0f", grandTotalPayable)}*
                        
                        Hello Bibek Sir, I want to proceed with this renewal.
                    """.trimIndent()
                    val encoded = Uri.encode(invoiceMsg)
                    val url = "https://wa.me/97460099064?text=$encoded"
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    try {
                        context.startActivity(intent)
                    } catch (e: Exception) {
                        Toast.makeText(context, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.weight(1.3f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = WhatsAppGreen,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Call,
                    contentDescription = "WhatsApp",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI) "हट्सएपमा पठाउनुहोस्" else "WhatsApp CEO",
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // NEPAL QR CODE PAYMENT SECTION (Laxmi Sunrise Bank Fonepay & eSewa)
        QrPaymentSection(
            totalAmountNpr = grandTotalPayable,
            govtFeesNpr = totalGovtCharges,
            serviceChargeNpr = serviceCharge,
            customerName = customerName,
            passportNumber = passportNumber,
            countryName = selectedCountry.nameEn,
            currentLanguage = currentLanguage
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
private fun InvoiceLineItem(
    index: String,
    title: String,
    category: String,
    amountNpr: Double,
    isHighlight: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(2f),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "$index ",
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Bold,
                color = if (isHighlight) PrimaryNavy else Color(0xFF1E293B)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal,
                color = if (isHighlight) PrimaryNavy else Color(0xFF1E293B)
            )
        }

        Text(
            text = category,
            style = MaterialTheme.typography.labelSmall,
            color = if (isHighlight) PrimaryCobalt else Color(0xFF64748B),
            fontWeight = if (isHighlight) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.weight(1.3f)
        )

        Text(
            text = "रु. ${String.format("%,.2f", amountNpr)}",
            style = MaterialTheme.typography.bodySmall,
            fontWeight = if (isHighlight) FontWeight.ExtraBold else FontWeight.SemiBold,
            color = if (isHighlight) Color(0xFF047857) else Color(0xFF0F172A),
            textAlign = TextAlign.End,
            modifier = Modifier.weight(1f)
        )
    }
}

private fun shareInvoiceText(
    context: Context,
    invoiceNo: String,
    panNumber: String,
    customerName: String,
    passportNo: String,
    dob: String,
    country: String,
    welfareFund: Double,
    insurance: Double,
    ssf: Double,
    govtTotal: Double,
    serviceCharge: Double,
    grandTotal: Double
) {
    val invoiceText = """
        ========================================
        कर बिजक (TAX INVOICE) - SHRAM SWIKRITI
        PAN NO: $panNumber
        INVOICE NO: $invoiceNo
        ========================================
        Applicant: $customerName
        Passport No: $passportNo
        DOB: $dob
        Destination: $country
        ----------------------------------------
        1. Welfare Fund (2 Yrs): Rs. ${String.format("%,.2f", welfareFund)}
        2. Term Life Insurance: Rs. ${String.format("%,.2f", insurance)}
        3. SSF Contribution: Rs. ${String.format("%,.2f", ssf)}
        ----------------------------------------
        SUBTOTAL GOVT CHARGES: Rs. ${String.format("%,.2f", govtTotal)}
        SERVICE CHARGE: Rs. ${String.format("%,.2f", serviceCharge)}
        ----------------------------------------
        GRAND TOTAL PAYABLE: Rs. ${String.format("%,.2f", grandTotal)}
        ========================================
        CEO: Bibek Maharjan (+974 60099064 / +977 9865385124)
        Manager: Jenita Maharjan (+977 9809364333)
    """.trimIndent()

    val intent = Intent(Intent.ACTION_SEND).apply {
        type = "text/plain"
        putExtra(Intent.EXTRA_SUBJECT, "Tax Invoice $invoiceNo - Shram Swikriti")
        putExtra(Intent.EXTRA_TEXT, invoiceText)
    }
    context.startActivity(Intent.createChooser(intent, "Share Tax Invoice"))
}

private fun numberToNepaliWords(number: Int): String {
    // Simple helper for thousands representation in words
    val thousands = number / 1000
    val remainder = number % 1000
    val hundreds = remainder / 100
    val rest = remainder % 100

    val thWord = when (thousands) {
        1 -> "एक हजार"
        2 -> "दुई हजार"
        3 -> "तीन हजार"
        4 -> "चार हजार"
        5 -> "पाँच हजार"
        6 -> "छ हजार"
        7 -> "सात हजार"
        8 -> "आठ हजार"
        9 -> "नौ हजार"
        10 -> "दश हजार"
        11 -> "एघार हजार"
        12 -> "बाह्र हजार"
        13 -> "तेह्र हजार"
        14 -> "चौध हजार"
        15 -> "पन्ध्र हजार"
        else -> "$thousands हजार"
    }

    val hWord = if (hundreds > 0) when (hundreds) {
        1 -> "एक सय"
        2 -> "दुई सय"
        3 -> "तीन सय"
        4 -> "चार सय"
        5 -> "पाँच सय"
        6 -> "छ सय"
        7 -> "सात सय"
        8 -> "आठ सय"
        9 -> "नौ सय"
        else -> "$hundreds सय"
    } else ""

    val rWord = if (rest > 0) "$rest" else ""

    return listOf(thWord, hWord, rWord).filter { it.isNotBlank() }.joinToString(" ")
}
