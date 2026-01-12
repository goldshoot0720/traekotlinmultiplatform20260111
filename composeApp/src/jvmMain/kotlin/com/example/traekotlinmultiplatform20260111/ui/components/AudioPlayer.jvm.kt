package com.example.traekotlinmultiplatform20260111.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun AudioPlayer(fileName: String, isPlaying: Boolean, modifier: Modifier) {
    Box(
        modifier = modifier.background(Color.Gray.copy(alpha = 0.2f)).padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Audio playback not supported on Desktop.\nFile: $fileName\nPlaying: $isPlaying",
            color = Color.Black
        )
    }
}
