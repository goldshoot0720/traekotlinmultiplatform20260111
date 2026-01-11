package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "早安，鋒兄",
            color = TextPrimary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "今天想處理什麼？",
            color = TextSecondary,
            fontSize = 18.sp
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        AppCard(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("本月訂閱支出", color = TextSecondary, fontSize = 14.sp)
                Text("NT$ 3,450", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
        }
        
        AppCard(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("即將過期食品", color = TextSecondary, fontSize = 14.sp)
                Text("3 項", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
                Text("牛奶, 雞蛋, 吐司", color = TextSecondary, fontSize = 14.sp)
            }
        }
        
        AppCard(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("系統狀態", color = TextSecondary, fontSize = 14.sp)
                Text("正常運作中", color = TextPrimary, fontSize = 28.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
