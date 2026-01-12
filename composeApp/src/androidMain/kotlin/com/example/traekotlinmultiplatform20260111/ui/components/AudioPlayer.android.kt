package com.example.traekotlinmultiplatform20260111.ui.components

import android.media.MediaPlayer
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import org.jetbrains.compose.resources.ExperimentalResourceApi
import traekotlinmultiplatform20260111.composeapp.generated.resources.Res
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@OptIn(ExperimentalResourceApi::class)
@Composable
actual fun AudioPlayer(
    fileName: String,
    isPlaying: Boolean,
    modifier: Modifier
) {
    val context = LocalContext.current
    var mediaPlayer by remember { mutableStateOf<MediaPlayer?>(null) }
    var currentFile by remember { mutableStateOf<String?>(null) }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    LaunchedEffect(fileName) {
        if (currentFile != fileName) {
            mediaPlayer?.release()
            mediaPlayer = null
            
            try {
                val bytes = Res.readBytes("files/$fileName")
                val file = File(context.cacheDir, fileName)
                withContext(Dispatchers.IO) {
                    FileOutputStream(file).use { output ->
                        output.write(bytes)
                    }
                }
                
                val mp = MediaPlayer()
                mp.setDataSource(file.absolutePath)
                mp.prepare()
                mp.isLooping = false
                mediaPlayer = mp
                currentFile = fileName
                
                if (isPlaying) {
                    mp.start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    LaunchedEffect(isPlaying) {
        mediaPlayer?.let { mp ->
            if (isPlaying && !mp.isPlaying) {
                mp.start()
            } else if (!isPlaying && mp.isPlaying) {
                mp.pause()
            }
        }
    }
}
