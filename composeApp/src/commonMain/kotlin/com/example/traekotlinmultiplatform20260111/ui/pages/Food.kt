package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import com.example.traekotlinmultiplatform20260111.data.ContentfulConfig
import com.example.traekotlinmultiplatform20260111.data.ContentfulDeliveryClient
import com.example.traekotlinmultiplatform20260111.data.createHttpClient
import com.example.traekotlinmultiplatform20260111.data.toFoodItems
import kotlinx.coroutines.launch

data class FoodItem(
    val name: String,
    val expiry: String,
    val quantity: String,
    val price: String,
    val shop: String,
    val imageUrl: String? = null
)

@Composable
fun FoodScreen() {
    BoxWithConstraints {
        val isWideScreen = maxWidth > 800.dp
        val scope = rememberCoroutineScope()

        val httpClient = remember { createHttpClient() }
        DisposableEffect(Unit) {
            onDispose { httpClient.close() }
        }

        var foodItems by remember { mutableStateOf<List<FoodItem>>(emptyList()) }
        var isLoading by remember { mutableStateOf(false) }
        var loadError by remember { mutableStateOf<String?>(null) }

        fun reload() {
            scope.launch {
                isLoading = true
                loadError = null
                try {
                    val spaceId = ContentfulConfig.spaceId.trim()
                    val token = ContentfulConfig.accessToken.trim()
                    if (spaceId.isBlank() || token.isBlank()) {
                        foodItems = emptyList()
                        loadError = "尚未設定 Contentful Space ID / Access Token"
                        return@launch
                    }

                    val contentfulClient = ContentfulDeliveryClient(
                        httpClient = httpClient,
                        spaceId = spaceId,
                        accessToken = token
                    )
                    val response = contentfulClient.fetchEntries(limit = 200, include = 2)
                    val parsed = response.toFoodItems()
                    foodItems = parsed.map { item ->
                        FoodItem(
                            name = item.name,
                            expiry = item.expiry,
                            quantity = item.quantity,
                            price = item.price,
                            shop = item.shop,
                            imageUrl = item.imageUrl
                        )
                    }
                } catch (e: Exception) {
                    loadError = e.message ?: "unknown error"
                } finally {
                    isLoading = false
                }
            }
        }

        LaunchedEffect(Unit) {
            reload()
        }

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
                            text = "食品清單",
                            color = TextPrimary,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Button(
                            onClick = { reload() },
                            colors = ButtonDefaults.buttonColors(containerColor = AppSecondary),
                            contentPadding = androidx.compose.foundation.layout.PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                            modifier = Modifier.height(32.dp)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = "Sync", modifier = Modifier.size(16.dp))
                            Spacer(Modifier.width(4.dp))
                            Text("同步", fontSize = 12.sp)
                        }
                        Text(
                            text = if (isLoading) "載入中..." else "已載入 ${foodItems.size} 筆",
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                    if (loadError != null) {
                        Text(
                            text = "載入失敗: $loadError",
                            color = Color(0xFFFFB74D),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(foodItems) { item ->
                            AppCard(modifier = Modifier.fillMaxWidth().padding(0.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    // Placeholder Image
                                    Box(
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        // Image would go here
                                    }
                                    
                                    Spacer(Modifier.width(16.dp))
                                    
                                    Column(modifier = Modifier.weight(1f)) {
                                        Text(item.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                        Text("數量: ${item.quantity} | 價格: ${item.price}", color = TextSecondary, fontSize = 14.sp)
                                        Text("到期日: ${item.expiry}", color = Color(0xFFFF8A80), fontSize = 14.sp)
                                    }
                                    
                                    IconButton(onClick = { /* Delete */ }) {
                                        Icon(Icons.Default.Delete, contentDescription = "Delete", tint = TextSecondary)
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
                                text = "新增食品",
                                color = TextPrimary,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(bottom = 24.dp)
                            )
                            
                            FoodFormContent(loadedCount = foodItems.size, isLoading = isLoading, loadError = loadError)
                        }
                    }
                }
            }
        } else {
            // Mobile Layout
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
                        text = "食品管理",
                        color = TextPrimary,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(foodItems) { item ->
                            AppCard(modifier = Modifier.fillMaxWidth().height(120.dp)) {
                                Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                                     // Placeholder Image
                                    Box(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color.White)
                                    )
                                    Spacer(Modifier.width(16.dp))
                                    Column {
                                        Text(item.name, color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 16.sp, maxLines = 1)
                                        Text(item.expiry, color = Color(0xFFFF8A80), fontSize = 12.sp)
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

@Composable
fun FoodFormContent(
    loadedCount: Int,
    isLoading: Boolean,
    loadError: String?
) {
    var name by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("1") }
    var price by remember { mutableStateOf("NT$0") }
    var year by remember { mutableStateOf("2026") }
    var month by remember { mutableStateOf("1") }
    var day by remember { mutableStateOf("12") }
    var shop by remember { mutableStateOf("") }
    var hash by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        // Name
        Text("名稱 (Name)", color = TextPrimary, fontSize = 14.sp)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
        )
        
        // Quantity & Price
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text("數量 (Amount)", color = TextPrimary, fontSize = 14.sp)
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text("價格 (Price)", color = TextPrimary, fontSize = 14.sp)
                OutlinedTextField(
                    value = price,
                    onValueChange = { price = it },
                    modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
                    colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
                )
            }
        }

        // Date Row
        Text("到期日 (To Date)", color = TextPrimary, fontSize = 14.sp)
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
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

        // Shop
        Text("商店 (Shop)", color = TextPrimary, fontSize = 14.sp)
        OutlinedTextField(
            value = shop,
            onValueChange = { shop = it },
            modifier = Modifier.fillMaxWidth().background(AppPrimaryVariant.copy(alpha=0.3f)),
            colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
        )
        
        // Photo
        Text("圖片 (Photo)", color = TextPrimary, fontSize = 14.sp)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(AppPrimaryVariant.copy(alpha=0.3f), RoundedCornerShape(8.dp))
                .clickable { /* Select Photo */ }
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.Add, contentDescription = "Add Photo", tint = TextSecondary, modifier = Modifier.size(48.dp))
                Text("選擇圖片...", color = TextSecondary.copy(alpha = 0.7f))
            }
        }

        // Hash
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Hash: ", color = TextSecondary, modifier = Modifier.padding(end = 8.dp))
            OutlinedTextField(
                value = hash,
                onValueChange = { hash = it },
                modifier = Modifier.width(150.dp).background(AppPrimaryVariant.copy(alpha=0.3f)),
                colors = OutlinedTextFieldDefaults.colors(focusedBorderColor = Color.Transparent, unfocusedBorderColor = Color.Transparent)
            )
        }
        
        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { /* Add */ },
            modifier = Modifier.fillMaxWidth().height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00E676)) // Green
        ) {
            Text("加入清單", color = Color.Black, fontWeight = FontWeight.Bold)
        }
        
        Spacer(Modifier.height(16.dp))
        Text(
            text = when {
                loadError != null -> "Contentful 載入失敗"
                isLoading -> "Contentful 載入中..."
                else -> "從 Contentful 載入 $loadedCount 筆食品"
            },
            color = Color(0xFFFFB74D),
            fontSize = 12.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}
