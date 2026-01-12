package com.example.traekotlinmultiplatform20260111.ui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun VideoPlayer(fileName: String, modifier: Modifier = Modifier)
