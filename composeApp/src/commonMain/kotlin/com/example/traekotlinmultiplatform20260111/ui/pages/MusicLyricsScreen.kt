package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppPrimary
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard
import com.example.traekotlinmultiplatform20260111.ui.components.AudioPlayer
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import traekotlinmultiplatform20260111.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

data class SongItem(val id: String, val title: String, val audioFile: String, val lyricsFile: String)

val songs = listOf(
    // Wedding Reasons
    SongItem("wed_zh", "史上最瞎結婚理由 (中文)", "song_wedding_zh.mp3", "lyrics_wedding_zh.txt"),
    SongItem("wed_jp", "史上最瞎結婚理由 (日語)", "song_wedding_jp.mp3", "lyrics_wedding_jp.txt"),
    SongItem("wed_yue", "史上最瞎結婚理由 (粵語)", "song_wedding_yue.mp3", "lyrics_wedding_yue.txt"),
    SongItem("wed_en", "史上最瞎結婚理由 (英語)", "song_wedding_en.mp3", "lyrics_wedding_en.txt"),
    SongItem("wed_kr", "史上最瞎結婚理由 (韓語)", "song_wedding_kr.mp3", "lyrics_wedding_kr.txt"),
    
    // Plumber Prince
    SongItem("plumb_zh", "塗哥水電王子爆紅 (中文)", "song_plumber_zh.mp3", "lyrics_plumber_zh.txt"),
    SongItem("plumb_jp", "塗哥水電王子爆紅 (日語)", "song_plumber_jp.mp3", "lyrics_plumber_jp.txt"),
    SongItem("plumb_yue", "塗哥水電王子爆紅 (粵語)", "song_plumber_yue.mp3", "lyrics_plumber_yue.txt"),
    SongItem("plumb_en", "塗哥水電王子爆紅 (英語)", "song_plumber_en.mp3", "lyrics_plumber_en.txt"),
    SongItem("plumb_kr", "塗哥水電王子爆紅 (韓語)", "song_plumber_kr.mp3", "lyrics_plumber_kr.txt"),

    // Evolution Show
    SongItem("evo_zh", "鋒兄進化Show (中文)", "song_evolution_zh.mp3", "lyrics_evolution_zh.txt"),
    SongItem("evo_jp", "鋒兄進化Show (日語)", "song_evolution_jp.mp3", "lyrics_evolution_jp.txt"),
    SongItem("evo_yue", "鋒兄進化Show (粵語)", "song_evolution_yue.mp3", "lyrics_evolution_yue.txt"),
    SongItem("evo_en", "鋒兄進化Show (英語)", "song_evolution_en.mp3", "lyrics_evolution_en.txt"),
    SongItem("evo_kr", "鋒兄進化Show (韓語)", "song_evolution_kr.mp3", "lyrics_evolution_kr.txt")
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun MusicLyricsScreen() {
    var selectedSong by remember { mutableStateOf<SongItem?>(null) }
    var isPlaying by remember { mutableStateOf(false) }
    var lyricsContent by remember { mutableStateOf("") }
    
    // Reset state when song changes
    LaunchedEffect(selectedSong) {
        isPlaying = false
        lyricsContent = "Loading lyrics..."
        selectedSong?.let { song ->
            try {
                val bytes = Res.readBytes("files/${song.lyricsFile}")
                lyricsContent = bytes.decodeToString()
                isPlaying = true // Auto play when selected
            } catch (e: Exception) {
                lyricsContent = "Error loading lyrics: ${e.message}"
            }
        } ?: run {
            lyricsContent = ""
        }
    }
    
    // Audio Player (Hidden/Non-UI logic handled inside)
    if (selectedSong != null) {
        AudioPlayer(
            fileName = selectedSong!!.audioFile,
            isPlaying = isPlaying
        )
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
                text = "鋒兄音樂歌詞",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text("已載入 ${songs.size} 首歌曲", color = TextSecondary, fontSize = 14.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(modifier = Modifier.weight(1f)) {
            // Song List
            AppCard(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("歌曲列表", color = TextPrimary, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp))
                    LazyColumn {
                        items(songs) { song ->
                            Text(
                                text = song.title,
                                color = if (selectedSong == song) AppSecondary else TextPrimary,
                                fontWeight = if (selectedSong == song) FontWeight.Bold else FontWeight.Normal,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { selectedSong = song }
                                    .padding(8.dp)
                            )
                        }
                    }
                }
            }
            
            Spacer(modifier = Modifier.padding(8.dp))
            
            // Lyrics View
            AppCard(modifier = Modifier.weight(2f).fillMaxHeight()) {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                    if (selectedSong != null) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.fillMaxSize()
                        ) {
                            Text(
                                text = selectedSong!!.title,
                                color = TextPrimary,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(Modifier.height(8.dp))
                            
                            // Scrollable Lyrics
                            Box(modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth()
                                .verticalScroll(rememberScrollState())
                            ) {
                                Text(
                                    text = lyricsContent,
                                    color = TextSecondary,
                                    fontSize = 16.sp,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier.fillMaxWidth()
                                )
                            }
                        }
                    } else {
                        Text("請選擇一首歌曲播放", color = TextSecondary)
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Player Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = { isPlaying = !isPlaying },
                enabled = selectedSong != null,
                colors = ButtonDefaults.buttonColors(containerColor = AppSecondary)
            ) {
                Text(if (isPlaying) "暫停" else "播放")
            }
            Spacer(Modifier.padding(8.dp))
            Button(
                onClick = { isPlaying = false },
                enabled = selectedSong != null,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red.copy(alpha = 0.7f))
            ) {
                Text("停止")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Instruction Card
        AppCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💡 開發小提示 (Dev Tip)", fontWeight = FontWeight.Bold, color = AppSecondary, modifier = Modifier.padding(bottom = 8.dp))
                Text("資源位置: composeApp/src/commonMain/composeResources/files", color = TextSecondary, fontSize = 13.sp)
                Text("存取方式: Res.readBytes(\"files/song_xx.mp3\") 或使用 Audio Player 庫", color = TextSecondary, fontSize = 13.sp)
                Text("已將檔案重新命名為 song_xx.mp3 及 lyrics_xx.txt 以符合資源規範", color = AppSecondary, fontSize = 13.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
