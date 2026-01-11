package com.example.traekotlinmultiplatform20260111

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Traekotlinmultiplatform20260111",
    ) {
        App()
    }
}