package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.Rocket
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard

@Composable
fun HomeScreen() {
    BoxWithConstraints {
        val isWideScreen = maxWidth > 800.dp

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "歡迎使用鋒兄AI資訊系統",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Main Banner
            AppCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Icon Box
                    Box(
                        modifier = Modifier
                            .size(60.dp)
                            .background(Color(0xFFFF7043), RoundedCornerShape(12.dp)), // Orange/Red color
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "鋒",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(24.dp))
                    
                    Text(
                        text = "歡迎使用鋒兄AI資訊系統",
                        color = TextPrimary,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    Text(
                        text = "智能管理您的影片和圖片收藏，支援智能分類和快速搜尋",
                        color = TextSecondary,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    )
                    
                    Spacer(modifier = Modifier.height(40.dp))
                    
                    Text(
                        text = "鋒兄涂哥公開資訊© 版權所有 2025 ~ 2125",
                        color = TextSecondary.copy(alpha = 0.5f),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Tech Stack Section
            if (isWideScreen) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    TechCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Bolt,
                        title = "前端技術",
                        items = listOf(
                            "Kotlin Multiplatform (Compose)",
                            "Desktop / Android / iOS / Web",
                            "響應式設計 (Responsive UI)"
                        )
                    )
                    TechCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Rocket,
                        title = "後端技術",
                        items = listOf(
                            "Contentful (Headless CMS)",
                            "資料存放於 Contentful Space",
                            "Contentful Delivery API"
                        )
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    TechCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Default.Bolt,
                        title = "前端技術",
                        items = listOf(
                            "Kotlin Multiplatform (Compose)",
                            "Desktop / Android / iOS / Web",
                            "響應式設計 (Responsive UI)"
                        )
                    )
                    TechCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Default.Rocket,
                        title = "後端技術",
                        items = listOf(
                            "Contentful (Headless CMS)",
                            "資料存放於 Contentful Space",
                            "Contentful Delivery API"
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Stats Section
            if (isWideScreen) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatsCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Description,
                        title = "訂閱管理",
                        stats = listOf(
                            StatItem("項目數", "24", ""),
                            StatItem("7天提醒", "6", "最近: 2026/1/10"),
                            StatItem("30天提醒", "10", "最近: 2026/1/10")
                        )
                    )
                    StatsCard(
                        modifier = Modifier.weight(1f),
                        icon = Icons.Default.Restaurant,
                        title = "食品管理",
                        stats = listOf(
                            StatItem("項目數", "13", ""),
                            StatItem("3天提醒", "0", "最近: -"),
                            StatItem("7天提醒", "0", "最近: -")
                        )
                    )
                }
            } else {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                    StatsCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Default.Description,
                        title = "訂閱管理",
                        stats = listOf(
                            StatItem("項目數", "24", ""),
                            StatItem("7天提醒", "6", "最近: 2026/1/10"),
                            StatItem("30天提醒", "10", "最近: 2026/1/10")
                        )
                    )
                    StatsCard(
                        modifier = Modifier.fillMaxWidth(),
                        icon = Icons.Default.Restaurant,
                        title = "食品管理",
                        stats = listOf(
                            StatItem("項目數", "13", ""),
                            StatItem("3天提醒", "0", "最近: -"),
                            StatItem("7天提醒", "0", "最近: -")
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun TechCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    items: List<String>
) {
    AppCard(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(8.dp))
                Text(title, color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))
            items.forEach { item ->
                Text("• $item", color = TextSecondary, fontSize = 14.sp, modifier = Modifier.padding(bottom = 4.dp))
            }
        }
    }
}

data class StatItem(val label: String, val value: String, val subtext: String)

@Composable
fun StatsCard(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    title: String,
    stats: List<StatItem>
) {
    AppCard(modifier = modifier) {
        Column(modifier = Modifier.padding(24.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(24.dp))
                Spacer(Modifier.width(8.dp))
                Text(title, color = TextPrimary, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(24.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                stats.forEach { stat ->
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(stat.label, color = TextSecondary, fontSize = 14.sp)
                        Text(stat.value, color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                        if (stat.subtext.isNotEmpty()) {
                            Text(stat.subtext, color = TextSecondary.copy(alpha = 0.7f), fontSize = 10.sp)
                        } else {
                            Spacer(Modifier.height(15.sp.value.dp)) // Placeholder height
                        }
                    }
                }
            }
        }
    }
}
