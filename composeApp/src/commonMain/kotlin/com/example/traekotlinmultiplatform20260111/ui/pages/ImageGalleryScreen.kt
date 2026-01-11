package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary

@Composable
fun ImageGalleryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("圖片庫 (開發中)", color = TextPrimary)
    }
}
