package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard

@Composable
fun SettingsScreen() {
    var spaceId by remember { mutableStateOf("") }
    var accessToken by remember { mutableStateOf("") }
    var managementToken by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "系統設定",
            color = TextPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Contentful API 設定",
            color = TextSecondary,
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                SettingsTextField(
                    value = spaceId,
                    onValueChange = { spaceId = it },
                    label = "Space ID"
                )
                Spacer(modifier = Modifier.height(8.dp))
                SettingsTextField(
                    value = accessToken,
                    onValueChange = { accessToken = it },
                    label = "Access Token"
                )
                Spacer(modifier = Modifier.height(8.dp))
                SettingsTextField(
                    value = managementToken,
                    onValueChange = { managementToken = it },
                    label = "Management Token"
                )
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Button(
                    onClick = { /* Save action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = AppSecondary),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("儲存設定", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun SettingsTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label, color = TextSecondary) },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = AppSecondary,
            unfocusedBorderColor = TextSecondary,
            focusedTextColor = TextPrimary,
            unfocusedTextColor = TextPrimary,
            cursorColor = AppSecondary
        ),
        modifier = Modifier.fillMaxWidth()
    )
}
