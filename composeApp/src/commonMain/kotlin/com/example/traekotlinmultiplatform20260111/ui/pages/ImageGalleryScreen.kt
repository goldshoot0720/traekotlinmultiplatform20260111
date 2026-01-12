package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppPrimary
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import traekotlinmultiplatform20260111.composeapp.generated.resources.Res
import traekotlinmultiplatform20260111.composeapp.generated.resources.*

data class ImageItem(val id: String, val name: String, val resource: DrawableResource? = null)


// dummyImages removed, using allRegistryImages from ImageRegistry.kt

@Composable
fun ImageGalleryScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "圖片庫",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                Button(
                    onClick = { /* Reload action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = AppSecondary)
                ) {
                    Icon(Icons.Default.Refresh, contentDescription = "Reload", modifier = Modifier.padding(end = 4.dp))
                    Text("重新整理")
                }
                Spacer(Modifier.padding(8.dp))
                Text("已載入 ${allRegistryImages.size} 張圖片", color = TextSecondary, fontSize = 14.sp)
            }
        }

        // Instruction Card
        AppCard(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💡 圖片庫狀態 (Gallery Status)", fontWeight = FontWeight.Bold, color = AppSecondary, modifier = Modifier.padding(bottom = 8.dp))
                Text("已自動掃描並載入 composeResources/drawable 中的圖片資源", color = TextSecondary, fontSize = 13.sp)
                Text("包含: Gallery, MindVideo, Screenshots 等系列", color = TextSecondary, fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 150.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(allRegistryImages) { image ->
                AppCard {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Image Display
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            if (image.resource != null) {
                                Image(
                                    painter = painterResource(image.resource),
                                    contentDescription = image.name,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.Face,
                                    contentDescription = null,
                                    tint = AppSecondary,
                                    modifier = Modifier.fillMaxSize(0.5f)
                                )
                            }
                        }
                        
                        Text(
                            text = image.name,
                            color = TextPrimary,
                            fontSize = 12.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            maxLines = 2
                        )
                    }
                }
            }
        }
    }
}
