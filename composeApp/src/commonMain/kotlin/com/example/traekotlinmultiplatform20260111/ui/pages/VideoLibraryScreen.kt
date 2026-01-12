package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppPrimary
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard
import com.example.traekotlinmultiplatform20260111.ui.components.VideoPlayer
import traekotlinmultiplatform20260111.composeapp.generated.resources.Res
import androidx.compose.ui.window.Dialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*

data class VideoItem(val id: String, val name: String, val fileName: String)

val videoFiles = listOf(
    "video_01.mp4" to "20260109_1352",
    "video_02.mp4" to "20260109_1355",
    "video_03.mp4" to "20260109_1356",
    "video_04.mp4" to "20260109_1358",
    "video_05.mp4" to "MindVideo_01",
    "video_06.mp4" to "MindVideo_02",
    "video_07.mp4" to "鋒兄的傳奇人生",
    "video_08.mp4" to "鋒兄進化Show"
)

val dummyVideos = videoFiles.mapIndexed { index, (fileName, desc) ->
    VideoItem(
        id = "vid_$index",
        name = desc,
        fileName = fileName
    )
}

@Composable
fun VideoLibraryScreen() {
    var selectedVideo by remember { mutableStateOf<VideoItem?>(null) }

    if (selectedVideo != null) {
        Dialog(onDismissRequest = { selectedVideo = null }) {
            AppCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp) // Adjust height as needed
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = selectedVideo!!.name,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                        IconButton(onClick = { selectedVideo = null }) {
                            Icon(Icons.Default.Close, contentDescription = "Close")
                        }
                    }
                    
                    VideoPlayer(
                        fileName = selectedVideo!!.fileName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color.Black)
                    )
                }
            }
        }
    }

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
                text = "影片庫",
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
                Text("已載入 ${dummyVideos.size} 部影片", color = TextSecondary, fontSize = 14.sp)
            }
        }

        // Instruction Card
        AppCard(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💡 開發小提示 (Dev Tip)", fontWeight = FontWeight.Bold, color = AppSecondary, modifier = Modifier.padding(bottom = 8.dp))
                Text("資源位置: composeApp/src/commonMain/composeResources/files", color = TextSecondary, fontSize = 13.sp)
                Text("存取方式: Res.readBytes(\"files/video_xx.mp4\") 或使用 Video Player 庫載入 URI", color = TextSecondary, fontSize = 13.sp)
                Text("已將檔案重新命名為 video_01.mp4 ~ video_08.mp4 以符合資源規範", color = AppSecondary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 300.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(dummyVideos) { video ->
                AppCard(
                    modifier = Modifier.clickable { selectedVideo = video }
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .aspectRatio(16f / 9f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.Black.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Play",
                                tint = AppSecondary,
                                modifier = Modifier.size(48.dp)
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        Text(
                            text = video.name,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary,
                            fontSize = 16.sp
                        )
                        Text(
                            text = video.fileName,
                            color = TextSecondary,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
