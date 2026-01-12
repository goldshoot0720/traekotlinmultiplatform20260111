package com.example.traekotlinmultiplatform20260111.data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object ContentfulConfig {
    var spaceId by mutableStateOf("")
    var accessToken by mutableStateOf("")
    var managementToken by mutableStateOf("")
}

