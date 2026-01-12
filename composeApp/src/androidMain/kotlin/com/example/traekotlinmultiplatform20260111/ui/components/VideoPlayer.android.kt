package com.example.traekotlinmultiplatform20260111.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import android.widget.VideoView
import android.widget.MediaController
import android.net.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import traekotlinmultiplatform20260111.composeapp.generated.resources.Res
import java.io.File
import java.io.FileOutputStream

@Composable
actual fun VideoPlayer(fileName: String, modifier: Modifier) {
    val context = LocalContext.current
    var videoUri by remember { mutableStateOf<Uri?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(fileName) {
        isLoading = true
        error = null
        try {
            // Copy resource to cache file
            val bytes = Res.readBytes("files/$fileName")
            val file = File(context.cacheDir, fileName)
            withContext(Dispatchers.IO) {
                FileOutputStream(file).use { output ->
                    output.write(bytes)
                }
            }
            videoUri = Uri.fromFile(file)
        } catch (e: Exception) {
            e.printStackTrace()
            error = "Error loading video: ${e.message}"
        } finally {
            isLoading = false
        }
    }

    Box(modifier = modifier.background(Color.Black)) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center),
                color = Color.White
            )
        } else if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                modifier = Modifier.align(Alignment.Center)
            )
        } else if (videoUri != null) {
            AndroidView(
                modifier = Modifier.fillMaxSize(),
                factory = { ctx ->
                    VideoView(ctx).apply {
                        setVideoURI(videoUri)
                        val mediaController = MediaController(ctx)
                        mediaController.setAnchorView(this)
                        setMediaController(mediaController)
                        setOnPreparedListener { mp ->
                            mp.start()
                            mp.isLooping = true
                        }
                    }
                }
            )
        }
    }
}
