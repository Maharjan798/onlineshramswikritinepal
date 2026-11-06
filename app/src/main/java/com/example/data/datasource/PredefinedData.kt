package com.example.data.datasource

import com.example.data.model.CountryRegion
import com.example.data.model.CostBreakdown
import com.example.data.model.DestinationCountry
import com.example.data.model.DocumentItem
import com.example.data.model.GuidanceStep

object PredefinedData {

    val COUNTRIES = listOf(
        DestinationCountry(
            id = "qatar",
            nameEn = "Qatar",
            nameNe = "कतार",
            flagEmoji = "🇶🇦",
            region = CountryRegion.GCC,
            currencyCode = "QAR",
            nprExchangeRate = 36.85,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "FEIMS biometrics from previous visa valid. In-person visit NOT needed if biometric scanned after 2019.",
            biometricsNotesNe = "२०१९ पछि औंठाछाप/बायोमेट्रिक गराउनुभएको छ भने घरबाटै अनलाइन श्रम सम्भव छ।",
            specialAlertsEn = "Direct online re-entry (Aafno Swikriti) permitted if company name matches your previous passport sticker.",
            specialAlertsNe = "पुरानै कम्पनीमा निरन्तरता भएमा सीधा अनलाइन री-एन्ट्री स्वीकृति मिल्छ।"
        ),
        DestinationCountry(
            id = "uae",
            nameEn = "United Arab Emirates (UAE)",
            nameNe = "संयुक्त अरब इमिरेट्स (युएई)",
            flagEmoji = "🇦🇪",
            region = CountryRegion.GCC,
            currencyCode = "AED",
            nprExchangeRate = 36.50,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "Existing FEIMS ID valid. Ensure active UAE Resident Visa copy is uploaded.",
            biometricsNotesNe = "हालको युएई रेजिडेन्ट भिसा/इमिरेट्स आईडी स्क्यान गरी अपलोड गर्नुहोस्।",
            specialAlertsEn = "Provide official translated contract if visa document is in Arabic only.",
            specialAlertsNe = "अरबी भाषाको भिसा भएमा अंग्रेजी अनुवाद प्रति संलग्न गर्नुहोस्।"
        ),
        DestinationCountry(
            id = "saudi",
            nameEn = "Saudi Arabia",
            nameNe = "साउदी अरब",
            flagEmoji = "🇸🇦",
            region = CountryRegion.GCC,
            currencyCode = "SAR",
            nprExchangeRate = 35.75,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "Iqama status check performed against Saudi Ministry portal.",
            biometricsNotesNe = "इकामा (Iqama) को म्याद बाँकी रहेको यकिन गरी अपलोड गर्नुहोस्।",
            specialAlertsEn = "Valid Iqama copy and re-entry visa (Khuruj Auda) required for online processing.",
            specialAlertsNe = "वैध एकामा र पुन: प्रवेश भिसा (खुरुज औदा) अनिवार्य छ।"
        ),
        DestinationCountry(
            id = "kuwait",
            nameEn = "Kuwait",
            nameNe = "कुवेत",
            flagEmoji = "🇰🇼",
            region = CountryRegion.GCC,
            currencyCode = "KWD",
            nprExchangeRate = 435.20,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "Kuwait Civil ID and valid Work Visa copy mandatory.",
            biometricsNotesNe = "कुवेत सिभिल आईडी र वर्क भिसा स्क्यान अनिवार्य छ।",
            specialAlertsEn = "Direct renewal allowed for valid returning workers with updated Civil ID.",
            specialAlertsNe = "फर्किएका कामदारको लागि आईडी नवीकरणपछि सीधा श्रम स्वीकृत हुन्छ।"
        ),
        DestinationCountry(
            id = "malaysia",
            nameEn = "Malaysia",
            nameNe = "मलेसिया",
            flagEmoji = "🇲🇾",
            region = CountryRegion.MALAYSIA,
            currencyCode = "MYR",
            nprExchangeRate = 28.60,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "PLKS Passport Sticker biometric registration carried forward.",
            biometricsNotesNe = "PLKS पास र बायोमेट्रिक रेकर्ड प्रणालीमा अद्यावधिक हुनुपर्छ।",
            specialAlertsEn = "Direct online permit renewal available if PLKS pass is renewed by Malaysian employer.",
            specialAlertsNe = "रोजगारदाताले PLKS पास थप गरेपछि अनलाइन श्रम प्रक्रिया पूरा गर्न सकिन्छ।"
        ),
        DestinationCountry(
            id = "japan",
            nameEn = "Japan (SSW / TITP)",
            nameNe = "जापान (एसएसडब्ल्यू / टीआईटीपी)",
            flagEmoji = "🇯🇵",
            region = CountryRegion.EAST_ASIA,
            currencyCode = "JPY",
            nprExchangeRate = 0.88,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "Certificate of Eligibility (COE) or Residence Card required.",
            biometricsNotesNe = "रेजिडेन्स कार्ड वा COE कागजात स्पष्ट हुनुपर्छ।",
            specialAlertsEn = "Specified Skilled Worker (SSW) holders must upload signed employment agreement.",
            specialAlertsNe = "SSW कामदारले रोजगार सम्झौता पत्र अनिवार्य स्क्यान गर्नुपर्छ।"
        ),
        DestinationCountry(
            id = "croatia",
            nameEn = "Croatia",
            nameNe = "क्रोएशिया",
            flagEmoji = "🇭🇷",
            region = CountryRegion.EUROPE,
            currencyCode = "EUR",
            nprExchangeRate = 145.50,
            isReentryAllowedDirect = false,
            embassyVerificationRequired = true,
            biometricsNotesEn = "New European rules: Embassy demand attestation required if switching company.",
            biometricsNotesNe = "कम्पनी परिवर्तन गरेमा नेपाली दूतावासको मागपत्र प्रमाणीकरण अनिवार्य छ।",
            specialAlertsEn = "ATTENTION: Changing employer in Europe requires Nepali Embassy attestation & fresh verification.",
            specialAlertsNe = "सचेत गराउंदछौं: युरोपमा रोजगारदाता फेरेमा दूतावासको प्रमाणीकरण पत्र अपलोड गर्नुपर्छ।"
        ),
        DestinationCountry(
            id = "romania",
            nameEn = "Romania",
            nameNe = "रोमानिया",
            flagEmoji = "🇷🇴",
            region = CountryRegion.EUROPE,
            currencyCode = "RON",
            nprExchangeRate = 29.20,
            isReentryAllowedDirect = false,
            embassyVerificationRequired = true,
            biometricsNotesEn = "Biometrics verified via FEIMS online portal.",
            biometricsNotesNe = "बायोमेट्रिक अनलाइन FEIMS प्रणालीबाट रुजु हुन्छ।",
            specialAlertsEn = "Requires Work Permit (Aviz de Munca) copy along with Residence Permit.",
            specialAlertsNe = "वर्क परमिट (Aviz de Munca) र रेजिडेन्स कार्ड प्रति अनिवार्य।"
        ),
        DestinationCountry(
            id = "poland",
            nameEn = "Poland",
            nameNe = "पोल्याण्ड",
            flagEmoji = "🇵🇱",
            region = CountryRegion.EUROPE,
            currencyCode = "PLN",
            nprExchangeRate = 33.80,
            isReentryAllowedDirect = false,
            embassyVerificationRequired = true,
            biometricsNotesEn = "Requires official Polish Voivodeship decision letter scan.",
            biometricsNotesNe = "पोलिश भोइभोडेशिप निर्णय पत्रको स्क्यान अनिवार्य छ।",
            specialAlertsEn = "Must submit Polish Work Permit & Residence Card.",
            specialAlertsNe = "वर्क परमिट र रेजिडेन्स कार्ड अनिवार्य।"
        ),
        DestinationCountry(
            id = "usa",
            nameEn = "United States of America",
            nameNe = "संयुक्त राज्य अमेरिका",
            flagEmoji = "🇺🇸",
            region = CountryRegion.AMERICAS,
            currencyCode = "USD",
            nprExchangeRate = 134.20,
            isReentryAllowedDirect = true,
            embassyVerificationRequired = false,
            biometricsNotesEn = "US Work Visa (H2B / H1B / L1) biometric linked.",
            biometricsNotesNe = "अमेरिकी वर्क भिसा र I-94 फारम अद्यावधिक हुनुपर्छ।",
            specialAlertsEn = "Provide valid Form I-94 and US Work Authorization/Visa.",
            specialAlertsNe = "वैध I-94 र भिसा कागजात अपलोड गर्नुहोस्।"
        )
    )

    fun calculateCost(
        age: Int,
        contractYears: Int = 2,
        ssfMonths: Int = 1,
        includeSsf: Boolean = true,
        includeServiceFee: Boolean = false,
        isSsfSubsidized: Boolean = false,
        country: DestinationCountry = COUNTRIES.first()
    ): CostBreakdown {
        // 1. Foreign Employment Welfare Fund
        val welfareFund = if (contractYears <= 3) 1500.0 else 2500.0

        // 2. Term Life Insurance Premium based on age band & contract years
        val insurancePremium = when {
            age in 18..35 -> when (contractYears) {
                1 -> 2400.0
                2 -> 3708.0
                3 -> 4511.0
                else -> 5800.0
            }
            age in 36..50 -> when (contractYears) {
                1 -> 3200.0
                2 -> 4931.0
                3 -> 6152.0
                else -> 7800.0
            }
            else -> when (contractYears) { // Age 51-64
                1 -> 6100.0
                2 -> 9463.0
                3 -> 11565.0
                else -> 14200.0
            }
        }

        // 3. SSF Contribution (Rs. 2,596 / month)
        val monthlySsfRate = 2596.0
        val ssfContribution = if (includeSsf) {
            val months = ssfMonths.coerceIn(1, 12)
            if (isSsfSubsidized) (monthlySsfRate * 0.5 * months) else (monthlySsfRate * months)
        } else 0.0

        // 4. Agency Service Fee (Rs 500 optional)
        val serviceFee = if (includeServiceFee) 500.0 else 0.0

        val totalNpr = welfareFund + insurancePremium + ssfContribution + serviceFee
        val totalLocal = totalNpr / country.nprExchangeRate

        val ageBandEn = when {
            age in 18..35 -> "18-35 yrs"
            age in 36..50 -> "36-50 yrs"
            else -> "51-64 yrs"
        }

        val ageBandNe = when {
            age in 18..35 -> "18-35 yrs"
            age in 36..50 -> "36-50 yrs"
            else -> "51-64 yrs"
        }

        val yearsEn = "$contractYears Years"
        val yearsNe = "$contractYears Years"

        return CostBreakdown(
            welfareFundNpr = welfareFund,
            insurancePremiumNpr = insurancePremium,
            ssfContributionNpr = ssfContribution,
            ssfMonths = if (includeSsf) ssfMonths.coerceIn(1, 12) else 0,
            ssfMonthlyRateNpr = monthlySsfRate,
            serviceFeeNpr = serviceFee,
            gatewayFeeNpr = 0.0,
            totalNpr = totalNpr,
            localCurrencyCode = country.currencyCode,
            totalLocalCurrency = totalLocal,
            ageBandTextEn = ageBandEn,
            ageBandTextNe = ageBandNe,
            contractYearsTextEn = yearsEn,
            contractYearsTextNe = yearsNe,
            isSsfSubsidized = isSsfSubsidized
        )
    }

    val CHECKLIST_ITEMS = listOf(
        DocumentItem(
            id = "passport",
            titleEn = "Valid Passport Copy",
            titleNe = "राहदानी (पासपोर्ट) को प्रतिलिपि",
            descEn = "Original passport scan. Expiry date MUST be more than 6 months from submission date.",
            descNe = "पासपोर्टको म्याद कम्तिमा ६ महिना बाँकी हुनुपर्छ। पहिलो र अन्तिम पानाको स्पष्ट स्क्यान।",
            isRequired = true,
            maxSizeKb = 500,
            allowedFormats = "JPG, PDF"
        ),
        DocumentItem(
            id = "old_permit",
            titleEn = "Previous Work Permit Sticker / QR",
            titleNe = "पुरानो श्रम स्वीकृति स्टिकर / QR कोड",
            descEn = "Scan of previous Foreign Employment Work Permit (Shram Swikriti) sticker or online QR certificate.",
            descNe = "अघिल्लो पटक लिएको श्रम स्वीकृतिको स्टिकर वा QR प्रमाणपत्र प्रति।",
            isRequired = true,
            maxSizeKb = 500,
            allowedFormats = "JPG, PDF"
        ),
        DocumentItem(
            id = "visa_qid",
            titleEn = "Valid Visa / QID / Residence Permit",
            titleNe = "वैध भिसा / रेजिडेन्स परमिट / एकामा",
            descEn = "Current active residence permit or entry visa copy. Official English translation needed if in foreign script.",
            descNe = "गन्तव्य देशको हालको भिसा वा रेजिडेन्स कार्ड। विदेशी भाषामा भएमा अंग्रेजी अनुवाद प्रति।",
            isRequired = true,
            maxSizeKb = 500,
            allowedFormats = "JPG, PDF"
        ),
        DocumentItem(
            id = "employment_contract",
            titleEn = "Employment Contract / Offer Letter",
            titleNe = "रोजगार सम्झौता पत्र (कन्ट्र्याक्ट लेटर)",
            descEn = "Valid agreement signed by employer detailing monthly salary, position, and job duration.",
            descNe = "कम्पनी र कामदारबिच भएको तलब, पद र म्याद खुलेको सम्झौता पत्र।",
            isRequired = true,
            maxSizeKb = 500,
            allowedFormats = "PDF, JPG"
        ),
        DocumentItem(
            id = "photo",
            titleEn = "Recent Passport Size Photo",
            titleNe = "हालै खिचेको पासपोर्ट साइज फोटो",
            descEn = "Clear passport photo with solid white background. No caps, dark glasses, or shadows.",
            descNe = "सेतो पृष्ठभूमि (White background) मा स्पष्ट मुख देखिने ताजा फोटो।",
            isRequired = true,
            maxSizeKb = 300,
            allowedFormats = "JPG, PNG"
        ),
        DocumentItem(
            id = "contact_info",
            titleEn = "Active Nepali Mobile & Email ID",
            titleNe = "सक्रिय नेपाली मोवाइल नं. र इमेल",
            descEn = "Required for FEIMS OTP verification and receiving digital Shram Swikriti PDF certificate.",
            descNe = "FEIMS लगइन र OTP को लागि सक्रिय नेपाली मोबाइल नम्बर र इमेल आईडी।",
            isRequired = true,
            maxSizeKb = 0,
            allowedFormats = "Text input"
        ),
        DocumentItem(
            id = "embassy_attestation",
            titleEn = "Embassy Verification / Demand Approval",
            titleNe = "दूतावास प्रमाणीकरण (आवश्यक परेमा)",
            descEn = "Mandatory for European destinations or if changing employer in Europe / domestic worker category.",
            descNe = "युरोपमा रोजगारदाता परिवर्तन गर्दा वा घरेलु कामदारको लागि दूतावासको प्रमाणीकरण पत्र।",
            isRequired = false,
            maxSizeKb = 500,
            allowedFormats = "PDF"
        )
    )

    val GUIDANCE_STEPS = listOf(
        GuidanceStep(
            stepNumber = 1,
            titleEn = "Document Scans Preparation",
            titleNe = "कागजातहरु स्क्यान तथा तयारी",
            summaryEn = "Prepare clear JPG/PDF files strictly under 500KB with correct orientation.",
            summaryNe = "सबै कागजातहरू ५०० KB भन्दा साना JPG वा PDF ढाँचामा स्पष्ट तयार गर्नुहोस्।",
            detailedInstructionsEn = listOf(
                "Scan original Passport, Visa/QID, Old Work Permit, and Employment Contract.",
                "Ensure passport photo is on a white background with clear lighting.",
                "Compress files using free image tools if filesize exceeds 500KB.",
                "Ensure text on scans is fully legible to avoid FEIMS officer rejection."
            ),
            detailedInstructionsNe = listOf(
                "मूल पासपोर्ट, भिसा/रेजिडेन्स कार्ड, अघिल्लो श्रम स्टिकर र सम्झौता पत्र स्क्यान गर्नुहोस्।",
                "फोटो सेतो ब्याकग्राउन्ड भएको र स्पष्ट हुनुपर्छ।",
                "फाइल साइज ५०० KB भन्दा बढी भएमा कम्प्रेस गरी मिलाउनुहोस्।",
                "कागजातका अक्षरहरू प्रष्ट पढ्न सकिने हुनुपर्छ ताकी अस्वीकृत नहोस्।"
            ),
            proTipsEn = listOf(
                "Tip: CamScanner or vFlat mobile apps produce high-quality scans directly from phone camera.",
                "Keep documents saved in a separate 'Shram' folder on your phone for sending via WhatsApp."
            ),
            proTipsNe = listOf(
                "सुझाव: मोबाइलबाटै CamScanner वा vFlat एप प्रयोग गरी प्रष्ट स्क्यान लिन सकिन्छ।",
                "सजिलै भेट्नको लागि मोबाइलमा छुट्टै 'Shram' फोल्डर बनाएर राख्नुहोस् र हट्सएपमा पठाउनुहोस्।"
            )
        ),
        GuidanceStep(
            stepNumber = 2,
            titleEn = "FEIMS Account Portal Login",
            titleNe = "FEIMS पोर्टलमा लगइन / दर्ता",
            summaryEn = "Access the Foreign Employment FEIMS system and log into your labor profile.",
            summaryNe = "वैदेशिक रोजगार FEIMS प्रणालीमा गई आफ्नो श्रम प्रोफाइल लगइन गर्नुहोस्।",
            detailedInstructionsEn = listOf(
                "Access the official FEIMS (Foreign Employment Information Management System).",
                "Enter your registered Passport Number or FEIMS ID.",
                "Click 'Send OTP' to receive 6-digit verification code on your mobile/email.",
                "If first time online, select 'Aafno Swikriti' (Individual Re-entry) option."
            ),
            detailedInstructionsNe = listOf(
                "आधिकारिक FEIMS वैदेशिक रोजगार अनलाइन प्रणालीमा जानुहोस्।",
                "आफ्नो राहदानी नम्बर वा FEIMS ID प्रविष्ट गर्नुहोस्।",
                "'Send OTP' क्लिक गरी आफ्नो मोबाइल वा इमेलमा आएको कोड हाल्नुहोस्।",
                "पहिलो पटक अनलाइन भए 'आफ्नो स्वीकृति' विकल्प छान्नुहोस्।"
            ),
            proTipsEn = listOf(
                "If OTP is delayed, try using Email OTP option or check SMS spam folder.",
                "Ensure your passport validity is at least 6 months before applying."
            ),
            proTipsNe = listOf(
                "यदि SMS मा OTP आएन भने इमेल OTP विकल्प प्रयोग गर्नुहोस्।",
                "आवेदन दिनुअघि राहदानीको म्याद कम्तिमा ६ महिना बाँकी रहेको निश्चित गर्नुहोस्।"
            ),
            actionUrl = null,
            actionTextEn = null,
            actionTextNe = null
        ),
        GuidanceStep(
            stepNumber = 3,
            titleEn = "SSF & Insurance Online Payment",
            titleNe = "कल्याणकारी कोष, बीमा र SSF भुक्तानी",
            summaryEn = "Pay Welfare Fund, Term Life Insurance, & SSF via eSewa, Khalti, or ConnectIPS.",
            summaryNe = "इसेवा, खल्ती वा कनेक्ट IPS मार्फत तोकिएको कल्याणकारी कोष र बीमा रकम तिर्नुहोस्।",
            detailedInstructionsEn = listOf(
                "In FEIMS system, click 'Calculate & Pay Fees'.",
                "Choose digital payment gateway (eSewa / Khalti / ConnectIPS).",
                "Verify automated calculation: Welfare Fund + Term Insurance + SSF.",
                "Complete authorization on payment app and download digital receipt voucher."
            ),
            detailedInstructionsNe = listOf(
                "FEIMS प्रणालीभित्र 'शुल्क भुक्तानी' (Pay Fees) मा क्लिक गर्नुहोस्।",
                "इसेवा, खल्ती वा ConnectIPS डिजिटल वालेट छान्नुहोस्।",
                "कल्याणकारी कोष, म्यादी जीवन बीमा र SSF को हिसाब जाँच गरी भुक्तानी गर्नुहोस्।",
                "भुक्तानी सम्पन्न भएपछि डिजिटल भौचर / रसिद डाउनलोड गर्नुहोस्।"
            ),
            proTipsEn = listOf(
                "Ensure your digital wallet balance is slightly higher than total fee to cover Rs 15-20 convenience fee.",
                "Payment status updates automatically within 2-5 minutes in FEIMS."
            ),
            proTipsNe = listOf(
                "डिजिटल वालेटमा आवश्यक रकम भन्दा रु २० बढी ब्यालेन्स राख्नुहोस्।",
                "भुक्तानी गरेको २ देखि ५ मिनेटभित्र FEIMS मा स्वतः स्वीकृत देखिन्छ।"
            )
        ),
        GuidanceStep(
            stepNumber = 4,
            titleEn = "Online Application Submission",
            titleNe = "अनलाइन आवेदन बुझाउने (Submit)",
            summaryEn = "Fill country & job details, submit documents via WhatsApp for FEIMS processing.",
            summaryNe = "देश र कामको विवरण भरी कागजात हट्सएपमा पठाउनुहोस् र आवेदन प्रक्रिया अघि बढाउनुहोस्।",
            detailedInstructionsEn = listOf(
                "Select Destination Country, Contract Duration, and Employer Name.",
                "Send prepared Document Scans (Passport, Visa, Contract, Photo) via WhatsApp.",
                "Review declaration and submit application for review.",
                "Save Application Tracking Number for status monitoring."
            ),
            detailedInstructionsNe = listOf(
                "गन्तव्य देश, सम्झौता अवधि र रोजगारदाता कम्पनीको नाम चयन गर्नुहोस्।",
                "तयार पारिएका कागजातहरू (पासपोर्ट, भिसा, सम्झौता, फोटो) हट्सएप मार्फत पठाउनुहोस्।",
                "जाँच गरी आवेदन प्रक्रिया सम्पन्न गर्नुहोस्।",
                "प्राप्त ट्र्याकिङ नम्बर (Application No) सुरक्षित राख्नुहोस्।"
            ),
            proTipsEn = listOf(
                "DOFE officers typically review and process re-entry applications within 24 to 48 working hours.",
                "You can monitor approval progress under 'Application Status' tab."
            ),
            proTipsNe = listOf(
                "विभागका कर्मचारीहरूले प्राय: २४ देखि ४८ कार्य घण्टाभित्र आवेदन स्वीकृत गर्छन्।",
                "आफ्नो आवेदनको स्थिति 'Application Status' मा हेर्न सक्नुहुन्छ।"
            )
        ),
        GuidanceStep(
            stepNumber = 5,
            titleEn = "Download Sticker / Approval Certificate",
            titleNe = "स्वीकृति स्टिकर / QR प्रमाण पत्र डाउनलोड",
            summaryEn = "Upon approval, download your digital Shram Swikriti sticker PDF.",
            summaryNe = "स्वीकृत भएपछि आफ्नो QR कोडसहितको डिजिटल श्रम स्वीकृति स्टिकर प्राप्त गर्नुहोस्।",
            detailedInstructionsEn = listOf(
                "Once status changes to 'Approved', click 'Download Work Permit Sticker'.",
                "Open PDF file containing valid QR Code and DOFE digital signature.",
                "Print a clean color/monochrome copy to carry along with passport during travel.",
                "Verify QR code by scanning with phone camera."
            ),
            detailedInstructionsNe = listOf(
                "आवेदन 'Approved' भएपछि 'Download Work Permit Sticker' मा थिच्नुहोस्।",
                "QR कोड र डिजिटल हस्ताक्षर भएको PDF फाइल डाउनलोड गरी सुरक्षित गर्नुहोस्।",
                "यात्रा गर्दा पासपोर्टसँगै राख्‍न यो स्टिकर प्रिन्ट गर्नुहोस्।",
                "मोवाइल क्यामेराले QR कोड स्क्यान गरी विवरण रुजु गर्न सक्नुहुन्छ।"
            ),
            proTipsEn = listOf(
                "Congratulations! Digital Shram Swikriti is legally recognized at all airports including TIA Kathmandu.",
                "Keep a digital copy backup in Google Drive or phone storage."
            ),
            proTipsNe = listOf(
                "बधाई छ! यो डिजिटल श्रम स्वीकृति त्रिभुवन विमानस्थल लगायत सबै ठाउँमा मान्य हुन्छ।",
                "यसको सफ्ट कपी गुगल ड्राइभ वा मोवाइलमा पनि सुरक्षित राख्नुहोस्।"
            )
        )
    )

    val RENEWAL_FAQS = listOf(
        com.example.data.model.FaqItem(
            id = "faq_1",
            questionEn = "Can I renew my labor permit (Re-entry Shram) online while staying abroad?",
            questionNe = "म वैदेशिक रोजगारमा छँदै अनलाइनबाटै श्रम स्वीकृति (Re-entry Shram) नवीकरण गर्न सकिन्छ?",
            answerEn = "Yes! If you are working in Qatar, UAE, Saudi Arabia, Kuwait, Malaysia, etc., and continuing with the same employer, you can renew your work permit completely online via the official FEIMS portal without visiting Nepal.",
            answerNe = "हो, यदि तपाईं कतार, युएई, साउदी, कुवेत, मलेसिया लगायतका देशमा हुनुहुन्छ र अघिल्लो कम्पनीमै काम जारी छ भने नेपाल नफर्कीकनै विदेशबाटै FEIMS अनलाइन पोर्टल मार्फत पुनः श्रम स्वीकृति लिन सक्नुहुन्छ।"
        ),
        com.example.data.model.FaqItem(
            id = "faq_2",
            questionEn = "What documents are strictly required for Shram Swikriti renewal?",
            questionNe = "श्रम नवीकरणका लागि के-के कागजातहरू अनिवार्य आवश्यक पर्छन्?",
            answerEn = "1. Valid Passport (Minimum 6 months validity)\n2. Active Resident Visa / QID / Iqama copy\n3. Previous Shram Swikriti Sticker / FEIMS ID\n4. Updated Employment Contract / Offer Letter\n5. Passport size photograph (clear white background)",
            answerNe = "१. राहदानी (Passport - कम्तीमा ६ महिना म्याद भएको)\n२. हालको वैध भिसा वा रेजिडेन्स कार्ड (QID/Iqama/Civil ID)\n३. अघिल्लो श्रम स्वीकृतिको निस्सा\n४. रोजगारी सम्झौता पत्र (Contract Letter)\n५. सेतो पृष्ठभूमि भएको प्रष्ट फोटो"
        ),
        com.example.data.model.FaqItem(
            id = "faq_3",
            questionEn = "How do I pay the Welfare Fund, Insurance, and SSF fees online?",
            questionNe = "कल्याणकारी कोष, जीवन बीमा र SSF को रकम कसरी बुझाउने?",
            answerEn = "You can pay directly through digital payment gateways like eSewa, Khalti, or ConnectIPS after filling out your application on FEIMS. Family members in Nepal can also pay on your behalf using your passport number.",
            answerNe = "FEIMS अनलाइन प्रणालीमा फारम भरेपछि eSewa, Khalti वा ConnectIPS मार्फत सिधै रकम बुझाउन सकिन्छ। नेपालमा रहेका परिवारका सदस्यले पनि राहदानी नम्बर प्रयोग गरी भुक्तानी गरिदिन सक्छन्।"
        ),
        com.example.data.model.FaqItem(
            id = "faq_4",
            questionEn = "Is contribution to the Social Security Fund (SSF) mandatory?",
            questionNe = "SSF (सामाजिक सुरक्षा कोष) मा रकम तिर्नु अनिवार्य छ?",
            answerEn = "Yes, as per Government of Nepal regulations, foreign employment workers must contribute a minimum of Rs. 2,596/month to SSF to receive government health, disability, and pension benefits.",
            answerNe = "हो, नेपाल सरकारको नियमानुसार वैदेशिक रोजगारमा जाने कामदारले सामाजिक सुरक्षा तथा दुर्घटना-पेन्सन सुविधा प्राप्त गर्न SSF मा न्यूनतम रु. २,५९६ प्रति महिना योगदान बुझाउनुपर्छ।"
        ),
        com.example.data.model.FaqItem(
            id = "faq_5",
            questionEn = "How long does it take for online Shram Swikriti to get approved?",
            questionNe = "अनलाइन आवेदन दिएको कति समयमा श्रम स्वीकृति स्वीकृत हुन्छ?",
            answerEn = "If all documents are clear, legible, and accurate, DOFE officers usually verify and approve applications within 24 to 48 working hours.",
            answerNe = "कागजातहरू प्रष्ट र रितपूर्वक भएमा वैदेशिक रोजगार विभाग (DOFE) का अधिकारीहरूले २४ देखि ४८ कार्य घण्टाभित्र अनलाइन स्वीकृति प्रदान गर्दछन्।"
        ),
        com.example.data.model.FaqItem(
            id = "faq_6",
            questionEn = "Do I need a physical sticker stamped on my passport?",
            questionNe = "के मलाई राहदानीमा भौतिक (Physical) स्टिकर टाँस्न आवश्यक छ?",
            answerEn = "No physical passport sticker is needed anymore! DOFE issues a digital work permit certificate with a scannable QR code in PDF format, which is accepted at all international airports.",
            answerNe = "छैन! अब राहदानीमा भौतिक स्टिकर टाँस्नु पर्दैन। विभागले QR कोडसहितको डिजिटल स्वीकृति प्रमाण पत्र (PDF) जारी गर्छ, जुन त्रिभुवन विमानस्थल लगायत सबै ठाउँमा मान्य हुन्छ।"
        ),
        com.example.data.model.FaqItem(
            id = "faq_7",
            questionEn = "What should I do if I changed my employer or visa category?",
            questionNe = "यदि मेरो भिसा वा कम्पनी (Employer) फेरिएको छ भने के गर्ने?",
            answerEn = "If you changed your employer or visa category, your application will require embassy verification or new demand approval before applying for individual re-entry.",
            answerNe = "यदि रोजगारदाता वा कम्पनी परिवर्तन भएको छ भने सम्बन्धित देशमा रहेको नेपाली दूतावासबाट नयाँ सम्झौता वा मागपत्र प्रमाणीकरण गराएर मात्र पुनः श्रम स्वीकृति आवेदन दिनुपर्छ।"
        )
    )
}

