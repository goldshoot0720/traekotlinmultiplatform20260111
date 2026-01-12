package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.traekotlinmultiplatform20260111.ui.AppPrimaryVariant
import com.example.traekotlinmultiplatform20260111.ui.AppSecondary
import com.example.traekotlinmultiplatform20260111.ui.TextPrimary
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary
import com.example.traekotlinmultiplatform20260111.ui.components.AppCard
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
expect fun BankWeeklyScreen()

data class BankQuickNoteRow(
    val id: String,
    val name: String
)

@Composable
fun BankQuickNotesContent(
    initialRows: List<BankQuickNoteRow> = defaultBankRows,
    initialValues: Map<String, Long> = defaultBankValues,
    load: suspend () -> Map<String, Long>,
    save: suspend (Map<String, Long>) -> Unit
) {
    val scope = rememberCoroutineScope()
    val values = remember { mutableStateMapOf<String, String>() }
    var lastLoadedText by remember { mutableStateOf<String?>(null) }
    var statusText by remember { mutableStateOf<String?>(null) }
    var isBusy by remember { mutableStateOf(false) }

    fun subtotal(): Long {
        return initialRows.sumOf { row ->
            values[row.id]
                ?.trim()
                ?.toLongOrNull()
                ?: 0L
        }
    }

    fun setAll(map: Map<String, Long>) {
        initialRows.forEach { row ->
            val v = map[row.id] ?: 0L
            values[row.id] = v.toString()
        }
    }

    LaunchedEffect(Unit) {
        setAll(initialValues)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "銀行速記 Bank Quick Notes",
                color = TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            statusText?.let {
                Text(it, color = TextSecondary, fontSize = 12.sp)
            }
        }

        Spacer(Modifier.height(12.dp))

        Row(modifier = Modifier.weight(1f)) {
            AppCard(modifier = Modifier.weight(1f).fillMaxHeight()) {
                Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(initialRows) { row ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(AppPrimaryVariant.copy(alpha = 0.55f))
                                    .padding(horizontal = 12.dp, vertical = 10.dp)
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = row.name,
                                        color = TextPrimary,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                    OutlinedTextField(
                                        value = values[row.id] ?: "",
                                        onValueChange = { next ->
                                            val cleaned = next.filter { it.isDigit() || it == '-' }
                                            values[row.id] = cleaned
                                        },
                                        modifier = Modifier.width(140.dp),
                                        singleLine = true,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = TextSecondary.copy(alpha = 0.6f),
                                            unfocusedBorderColor = TextSecondary.copy(alpha = 0.4f),
                                            focusedContainerColor = Color.White.copy(alpha = 0.18f),
                                            unfocusedContainerColor = Color.White.copy(alpha = 0.12f),
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        ),
                                        textStyle = androidx.compose.ui.text.TextStyle(
                                            fontSize = 14.sp,
                                            textAlign = TextAlign.Center
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(12.dp))

        AppCard(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text("小計 (Subtotal)", color = TextPrimary, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(
                        lastLoadedText ?: "",
                        color = TextSecondary,
                        fontSize = 12.sp
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "NT$${formatCurrency(subtotal())}",
                        color = Color(0xFFFFD54F),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(end = 16.dp)
                    )

                    Button(
                        onClick = {
                            scope.launch {
                                isBusy = true
                                statusText = null
                                try {
                                    val loaded = load()
                                    setAll(loaded)
                                    lastLoadedText = "已讀取 ${loaded.size} 筆資料"
                                } catch (e: Exception) {
                                    statusText = "讀取失敗: ${e.message}"
                                } finally {
                                    isBusy = false
                                }
                            }
                        },
                        enabled = !isBusy,
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF8A65)),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("讀取 (Load)", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Spacer(Modifier.width(8.dp))

                    Button(
                        onClick = {
                            scope.launch {
                                isBusy = true
                                statusText = null
                                try {
                                    val map = initialRows.associate { row ->
                                        row.id to (values[row.id]?.trim()?.toLongOrNull() ?: 0L)
                                    }
                                    save(map)
                                    statusText = "已儲存"
                                } catch (e: Exception) {
                                    statusText = "儲存失敗: ${e.message}"
                                } finally {
                                    isBusy = false
                                }
                            }
                        },
                        enabled = !isBusy,
                        colors = ButtonDefaults.buttonColors(containerColor = AppSecondary),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("儲存 (Save)", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}

private fun formatCurrency(value: Long): String {
    val sign = if (value < 0) "-" else ""
    val n = abs(value).toString()
    val sb = StringBuilder()
    var count = 0
    for (i in n.length - 1 downTo 0) {
        sb.append(n[i])
        count++
        if (count == 3 && i != 0) {
            sb.append(',')
            count = 0
        }
    }
    return sign + sb.reverse().toString()
}

private val defaultBankRows = listOf(
    BankQuickNoteRow("台北富邦", "台北富邦"),
    BankQuickNoteRow("國泰世華", "國泰世華"),
    BankQuickNoteRow("兆豐銀行", "兆豐銀行"),
    BankQuickNoteRow("王道銀行", "王道銀行"),
    BankQuickNoteRow("新光銀行", "新光銀行"),
    BankQuickNoteRow("中華郵政", "中華郵政"),
    BankQuickNoteRow("玉山銀行", "玉山銀行"),
    BankQuickNoteRow("中國信託", "中國信託"),
    BankQuickNoteRow("台新銀行", "台新銀行")
)

private val defaultBankValues = mapOf(
    "台北富邦" to 423L,
    "國泰世華" to 360L,
    "兆豐銀行" to 452L,
    "王道銀行" to 500L,
    "新光銀行" to 200L,
    "中華郵政" to 601L,
    "玉山銀行" to 496L,
    "中國信託" to 1253L,
    "台新銀行" to 611L
)
