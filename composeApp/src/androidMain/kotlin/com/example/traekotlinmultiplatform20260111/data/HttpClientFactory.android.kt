package com.example.traekotlinmultiplatform20260111.data

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp

actual fun createHttpClient(): HttpClient {
    return HttpClient(OkHttp)
}

