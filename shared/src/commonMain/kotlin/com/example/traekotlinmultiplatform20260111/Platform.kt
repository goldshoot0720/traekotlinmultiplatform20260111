package com.example.traekotlinmultiplatform20260111

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform