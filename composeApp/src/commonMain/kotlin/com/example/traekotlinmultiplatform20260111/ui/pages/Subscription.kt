package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppPrimaryVariant
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard

data class Subscription(
    val name: String,
    val price: String,
    val nextDate: String,
    val site: String,
    val account: String,
    val note: String,
    val nextPaymentDisplay: String // For "下次扣款: 2027-02-06 16:00"
)

val dummySubscriptions = listOf(
    Subscription("天晟/處方箋/心臟內科", "0", "2027-02-06", "https://www.tcmg.com.tw/index.php/main/schedule_time?id=18", "", "", "2027-02-06 16:00"),
    Subscription("天晟/處方箋/身心科", "0", "2027-02-06", "https://www.tcmg.com.tw/index.php/main/schedule_time?id=14", "", "", "2027-02-06 16:00"),
    Subscription("Perplexity Pro/goldshoot0720", "660", "2026-11-06", "https://www.perplexity.ai/", "goldshoot0720", "", "2026-11-06 16:00"),
    Subscription("Cloudflare Domain", "350", "2026-09-15", "https://www.tpe12thmayor2038from2025.com/", "", "", "2026-09-15 16:00")
)

@Composable
fun SubscriptionScreen() {
    BoxWithConstraints {
        val isWideScreen = maxWidth > 800.dp
        
        // Form States
        var name by remember { mutableStateOf("") }
        var price by remember { mutableStateOf("NT$0") }
        var year by remember { mutableStateOf("2026") }
        var month by remember { mutableStateOf("1") }
        var day by remember { mutableStateOf("12") }
        var site by remember { mutableStateOf("") }
        var account by remember { mutableStateOf("") }
        var note by remember { mutableStateOf("") }

        if (isWideScreen) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Left Pane: List
                Column(
                    modifier = Modifier
                        .weight(1.2f)
                        .fillMaxHeight()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "訂閱清單",
                            color = TextPrimary,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = { /* Sync */ },
                            colors = ButtonDefaults.buttonColors(containerColor = AppSecondary),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Sync", modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("同步", fontSize = 12.sp)
                        }
                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(dummySubscriptions) { item ->
                            AppCard(modifier = Modifier.fillMaxWidth().padding(0.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White),
                                        contentAlignment = Alignment.Center
                                    ) {}

                                    Spacer(Modifier.width(16.dp))

                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                        Text("價格: ${item.price} | 帳號: ${item.account}", color = TextSecondary, fontSize = 14.sp)
                                        Text(
                                            text = item.site,
                                            color = Color(0xFF64B5F6),
                                            fontSize = 13.sp,
                                            modifier = Modifier.clickable { }
                                        )
                                        Text("下次扣款: ${item.nextPaymentDisplay}", color = Color(0xFFFF8A80), fontSize = 14.sp)
                                    }
                                    IconButton(onClick = { /* Delete */ }) {
                                        Icon(
                                            Icons.Default.Delete,
                                            contentDescription = "Delete",
                                            tint = TextSecondary
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Right Pane: Form
                Column(
                    modifier = Modifier
                        .weight(0.8f)
                        .fillMaxHeight()
                ) {
                     AppCard(modifier = Modifier.fillMaxSize().padding(0.dp)) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(24.dp)
                        ) {
                            Text(
                                text = "新增訂閱",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary,
                                modifier = Modifier.padding(bottom = 24.dp)
                            )

                            // Form Fields
                            Text("名稱 (Name)", color = TextPrimary, fontSize = 14.sp)
                            OutlinedTextField(
                                value = name,
                                onValueChange = { name = it },
                                modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                )
                            )
                            Spacer(Modifier.height(16.dp))

                            Text("價格 (Price)", color = TextPrimary, fontSize = 14.sp)
                            OutlinedTextField(
                                value = price,
                                onValueChange = { price = it },
                                modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = Color.Transparent,
                                    unfocusedBorderColor = Color.Transparent
                                )
                            )
                            Spacer(Modifier.height(16.dp))

                            Text("下次扣款日 (Next Date)", color = TextPrimary, fontSize = 14.sp)
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                OutlinedTextField(
                                    value = year,
                                    onValueChange = { year = it },
                                    modifier = Modifier.weight(1f).background(AppPrimaryVariant.copy(alpha=0.3f)),
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent),
                                    suffix = { Text("年") }
                                )
                                OutlinedTextField(
                                    value = month,
                                    onValueChange = { month = it },
                                    modifier = Modifier.weight(1f).background(AppPrimaryVariant.copy(alpha=0.3f)),
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent),
                                    suffix = { Text("月") }
                                )
                                OutlinedTextField(
                                    value = day,
                                    onValueChange = { day = it },
                                    modifier = Modifier.weight(1f).background(AppPrimaryVariant.copy(alpha=0.3f)),
                                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent),
                                    suffix = { Text("日") }
                                )
                            }
                            Spacer(Modifier.height(16.dp))

                            Text("網站 (Site)", color = TextPrimary, fontSize = 14.sp)
                            OutlinedTextField(
                                value = site,
                                onValueChange = { site = it },
                                modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
                            )
                            Spacer(Modifier.height(16.dp))

                            Text("帳號 (Account)", color = TextPrimary, fontSize = 14.sp)
                            OutlinedTextField(
                                value = account,
                                onValueChange = { account = it },
                                modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
                            )
                            Spacer(Modifier.height(16.dp))

                            Text("備註 (Note)", color = TextPrimary, fontSize = 14.sp)
                            OutlinedTextField(
                                value = note,
                                onValueChange = { note = it },
                                modifier = Modifier.fillMaxWidth().height(100.dp).background(AppPrimaryVariant.copy(alpha=0.3f)),
                                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
                            )
                            Spacer(Modifier.height(24.dp))

                            Button(
                                onClick = { /* Add */ },
                                modifier = Modifier.fillMaxWidth().height(48.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)) // Green
                            ) {
                                Text("加入清單", color = Color.Black, fontWeight = FontWeight.Bold)
                            }
                            
                            Spacer(Modifier.height(16.dp))
                            Text(
                                "從 Contentful 載入 24 筆訂閱",
                                color = Color(0xFFFFB74D), // Orange/Gold
                                fontSize = 12.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
            }
        } else {
            Scaffold(
                containerColor = Color.Transparent,
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {},
                        containerColor = AppSecondary,
                        contentColor = Color.White
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add")
                    }
                }
            ) { padding ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "訂閱管理",
                        color = TextPrimary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        items(dummySubscriptions) { item ->
                            AppCard(modifier = Modifier.fillMaxWidth()) {
                                Row(
                                    modifier = Modifier.padding(12.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White)
                                    ) {}
                                    Spacer(Modifier.width(16.dp))
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 1)
                                        Text("下次扣款: ${item.nextPaymentDisplay}", color = Color(0xFFFF8A80), fontSize = 12.sp)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
