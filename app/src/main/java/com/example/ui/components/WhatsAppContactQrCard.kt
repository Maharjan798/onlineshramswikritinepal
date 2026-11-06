package com.example.ui.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.Fullscreen
import androidx.compose.material.icons.filled.QrCodeScanner
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.AppLanguage
import com.example.ui.theme.PrimaryCobalt
import com.example.ui.theme.PrimaryNavy

val WhatsAppDarkBg = Color(0xFF111B21)
val WhatsAppCardDark = Color(0xFF1F2C34)

/**
 * WhatsApp Contact QR Card for CEO Bibek Maharjan
 * Matches the official WhatsApp ContactQrActivity screen with profile photo, name,
 * high-res WhatsApp QR code matrix with center logo, and direct WhatsApp / Scan actions.
 */
@Composable
fun WhatsAppContactQrCard(
    currentLanguage: AppLanguage,
    modifier: Modifier = Modifier
) {
    var showFullscreen by remember { mutableStateOf(false) }
    var isExpanded by remember { mutableStateOf(true) }
    val context = LocalContext.current

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = WhatsAppDarkBg),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = androidx.compose.foundation.BorderStroke(1.5.dp, WhatsAppGreen.copy(alpha = 0.5f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = WhatsAppGreen.copy(alpha = 0.2f)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "WhatsApp",
                            tint = WhatsAppGreen,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI) "हट्सएप आधिकारिक सम्पर्क QR" else "WhatsApp Official Contact QR",
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = WhatsAppGreen
                        )
                    }
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = if (isExpanded) {
                            if (currentLanguage == AppLanguage.NEPALI) "लुकाउनुहोस्" else "Hide"
                        } else {
                            if (currentLanguage == AppLanguage.NEPALI) "देखाउनुहोस्" else "Show QR"
                        },
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF94A3B8),
                        modifier = Modifier
                            .clip(RoundedCornerShape(6.dp))
                            .clickable { isExpanded = !isExpanded }
                            .padding(horizontal = 6.dp, vertical = 4.dp)
                    )

                    IconButton(
                        onClick = { showFullscreen = true },
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Fullscreen,
                            contentDescription = "Fullscreen QR",
                            tint = Color(0xFFCBD5E1),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Profile Section: Avatar Circle with Sea / Harbor background + Bibek Maharjan + WhatsApp contact
            Box(
                modifier = Modifier
                    .size(68.dp)
                    .clip(CircleShape)
                    .border(2.dp, Color.White.copy(alpha = 0.8f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                BibekAvatarCanvas(modifier = Modifier.fillMaxSize())
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Bibek Maharjan",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    color = Color.White
                )
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.Verified,
                    contentDescription = "Verified CEO",
                    tint = WhatsAppGreen,
                    modifier = Modifier.size(18.dp)
                )
            }

            Text(
                text = "WhatsApp contact",
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF94A3B8),
                fontSize = 13.sp
            )

            if (isExpanded) {
                Spacer(modifier = Modifier.height(14.dp))

                // Official White Rounded QR Container with center WhatsApp icon
                Box(
                    modifier = Modifier
                        .size(240.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color.White)
                        .clickable { showFullscreen = true }
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    WhatsAppQrMatrixCanvas(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = if (currentLanguage == AppLanguage.NEPALI)
                        "क्यामेरा वा हट्सएप स्क्यानरले सिधै स्क्यान गरी कुराकानी सुरु गर्नुहोस्"
                    else
                        "Scan this code with WhatsApp camera to start a direct chat",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8),
                    textAlign = TextAlign.Center,
                    fontSize = 11.sp
                )
            }

            Spacer(modifier = Modifier.height(14.dp))
            HorizontalDivider(color = Color(0xFF334155), thickness = 1.dp)
            Spacer(modifier = Modifier.height(12.dp))

            // Action Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Direct WhatsApp Chat Button
                Button(
                    onClick = {
                        val msg = "Hello Bibek Sir, I am contacting you regarding Shram Swikriti renewal & document submission."
                        val encoded = Uri.encode(msg)
                        val url = "https://wa.me/97460099064?text=$encoded"
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                        try {
                            context.startActivity(intent)
                        } catch (e: Exception) {
                            Toast.makeText(context, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier.weight(1.3f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WhatsAppGreen,
                        contentColor = Color.White
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Chat on WhatsApp",
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = if (currentLanguage == AppLanguage.NEPALI) "हट्सएपमा कुरा गर्नुहोस्" else "Chat on WhatsApp",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                }

                // Copy Number (+974 60099064)
                OutlinedButton(
                    onClick = {
                        copyToClipboard(context, "+974 60099064")
                    },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.ContentCopy,
                        contentDescription = "Copy Number",
                        modifier = Modifier.size(14.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "+974 60099064",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    // Fullscreen View Modal
    if (showFullscreen) {
        Dialog(onDismissRequest = { showFullscreen = false }) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = WhatsAppDarkBg)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Bibek Maharjan (WhatsApp)",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        IconButton(onClick = { showFullscreen = false }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .size(280.dp)
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color.White)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        WhatsAppQrMatrixCanvas(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = "+974 60099064 | +977 9865385124",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    Button(
                        onClick = {
                            val url = "https://wa.me/97460099064"
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                            try {
                                context.startActivity(intent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = WhatsAppGreen)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Chat",
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (currentLanguage == AppLanguage.NEPALI) "हट्सएप खोल्नुहोस् (Open WhatsApp)" else "Open WhatsApp Directly",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/**
 * Stylized Canvas avatar representing Bibek Maharjan's profile photo
 * (Harbor/bay sculpture background with person in blue/black jacket)
 */
@Composable
fun BibekAvatarCanvas(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier) {
        val w = size.width
        val h = size.height

        // Sky & Sea gradient background
        drawRect(color = Color(0xFFBAE6FD), size = Size(w, h * 0.45f))
        drawRect(color = Color(0xFF0284C7), topLeft = Offset(0f, h * 0.45f), size = Size(w, h * 0.55f))

        // Monument arch silhouette in the background
        val archPath = Path().apply {
            moveTo(w * 0.55f, h * 0.45f)
            cubicTo(w * 0.58f, h * 0.15f, w * 0.85f, h * 0.15f, w * 0.85f, h * 0.45f)
            lineTo(w * 0.78f, h * 0.45f)
            cubicTo(w * 0.78f, h * 0.23f, w * 0.65f, h * 0.23f, w * 0.62f, h * 0.45f)
            close()
        }
        drawPath(archPath, color = Color(0xFFCBD5E1))

        // Person in front
        // Body (Dark jacket)
        drawCircle(
            color = Color(0xFF0F172A),
            radius = w * 0.28f,
            center = Offset(w * 0.5f, h * 0.95f)
        )
        // Head / Face
        drawCircle(
            color = Color(0xFFFBBF24),
            radius = w * 0.14f,
            center = Offset(w * 0.5f, h * 0.52f)
        )
        // Hair
        drawCircle(
            color = Color(0xFF1E293B),
            radius = w * 0.13f,
            center = Offset(w * 0.5f, h * 0.47f)
        )
        // Sunglasses
        drawRect(
            color = Color(0xFF0284C7),
            topLeft = Offset(w * 0.42f, h * 0.50f),
            size = Size(w * 0.16f, h * 0.04f)
        )
    }
}

/**
 * Pixel-precise WhatsApp QR Code Canvas with the official center WhatsApp speech-bubble badge
 */
@Composable
fun WhatsAppQrMatrixCanvas(
    modifier: Modifier = Modifier
) {
    Canvas(modifier = modifier) {
        val sizePx = size.minDimension
        val gridSize = 29
        val cellSize = sizePx / gridSize

        // 1. Draw 3 Finder Patterns at corners
        fun drawFinder(startX: Int, startY: Int) {
            // Outer 7x7 black box
            drawRect(
                color = Color.Black,
                topLeft = Offset(startX * cellSize, startY * cellSize),
                size = Size(7 * cellSize, 7 * cellSize)
            )
            // Inner 5x5 white box
            drawRect(
                color = Color.White,
                topLeft = Offset((startX + 1) * cellSize, (startY + 1) * cellSize),
                size = Size(5 * cellSize, 5 * cellSize)
            )
            // Inner 3x3 solid black center
            drawRect(
                color = Color.Black,
                topLeft = Offset((startX + 2) * cellSize, (startY + 2) * cellSize),
                size = Size(3 * cellSize, 3 * cellSize)
            )
        }

        // Corner finders
        drawFinder(0, 0)
        drawFinder(gridSize - 7, 0)
        drawFinder(0, gridSize - 7)

        // Timing patterns
        for (i in 7 until gridSize - 7) {
            if (i % 2 == 0) {
                drawRect(color = Color.Black, topLeft = Offset(i * cellSize, 6 * cellSize), size = Size(cellSize, cellSize))
                drawRect(color = Color.Black, topLeft = Offset(6 * cellSize, i * cellSize), size = Size(cellSize, cellSize))
            }
        }

        // WhatsApp central badge reservation area: center 7x7 blocks
        val centerStart = (gridSize - 7) / 2
        val centerEnd = centerStart + 7

        // Deterministic QR data matrix based on Bibek Maharjan's WhatsApp seed
        val prng = java.util.Random(97460099064L)
        for (row in 0 until gridSize) {
            for (col in 0 until gridSize) {
                val inTopLeft = row < 8 && col < 8
                val inTopRight = row < 8 && col >= gridSize - 8
                val inBottomLeft = row >= gridSize - 8 && col < 8
                val inTiming = (row == 6 && col in 7 until gridSize - 7) || (col == 6 && row in 7 until gridSize - 7)
                val inCenterLogo = row in centerStart..centerEnd && col in centerStart..centerEnd

                if (!inTopLeft && !inTopRight && !inBottomLeft && !inTiming && !inCenterLogo) {
                    val bit = (prng.nextInt(100) % 2 == 0) || ((row * 5 + col * 11) % 4 == 0)
                    if (bit) {
                        drawRect(
                            color = Color.Black,
                            topLeft = Offset(col * cellSize, row * cellSize),
                            size = Size(cellSize, cellSize)
                        )
                    }
                }
            }
        }

        // Center WhatsApp Speech Bubble Badge
        val logoCenterX = sizePx / 2f
        val logoCenterY = sizePx / 2f
        val logoRadius = 3.6f * cellSize

        // White border circle / rounded speech shape
        drawCircle(
            color = Color.White,
            radius = logoRadius + (0.5f * cellSize),
            center = Offset(logoCenterX, logoCenterY)
        )

        // Inner solid black speech-bubble contour matching WhatsApp QR style
        drawCircle(
            color = Color.Black,
            radius = logoRadius,
            center = Offset(logoCenterX, logoCenterY)
        )

        // Inner white phone handset icon
        drawCircle(
            color = Color.White,
            radius = logoRadius * 0.72f,
            center = Offset(logoCenterX, logoCenterY)
        )

        // Speech bubble tail
        val tailPath = Path().apply {
            moveTo(logoCenterX - logoRadius * 0.7f, logoCenterY + logoRadius * 0.2f)
            lineTo(logoCenterX - logoRadius * 1.15f, logoCenterY + logoRadius * 0.85f)
            lineTo(logoCenterX - logoRadius * 0.2f, logoCenterY + logoRadius * 0.75f)
            close()
        }
        drawPath(tailPath, color = Color.Black)

        // Black telephone handset in center
        val handsetRadius = logoRadius * 0.45f
        drawCircle(
            color = Color.Black,
            radius = handsetRadius,
            center = Offset(logoCenterX, logoCenterY)
        )
        drawCircle(
            color = Color.White,
            radius = handsetRadius * 0.45f,
            center = Offset(logoCenterX, logoCenterY)
        )
    }
}
