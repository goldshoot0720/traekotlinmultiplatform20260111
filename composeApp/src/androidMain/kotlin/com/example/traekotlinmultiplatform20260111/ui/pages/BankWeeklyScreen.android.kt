package com.example.traekotlinmultiplatform20260111.ui.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File

@Composable
actual fun BankWeeklyScreen() {
    val context = LocalContext.current
    val storageFile = remember { File(context.filesDir, "bank_quick_notes.tsv") }

    BankQuickNotesContent(
        load = {
            withContext(Dispatchers.IO) {
                if (!storageFile.exists()) return@withContext emptyMap()
                storageFile.readLines()
                    .mapNotNull { line ->
                        val parts = line.split('\t')
                        if (parts.size < 2) return@mapNotNull null
                        val key = parts[0].trim()
                        val value = parts[1].trim().toLongOrNull() ?: return@mapNotNull null
                        if (key.isBlank()) return@mapNotNull null
                        key to value
                    }
                    .toMap()
            }
        },
        save = { map ->
            withContext(Dispatchers.IO) {
                storageFile.parentFile?.mkdirs()
                val text = buildString {
                    map.forEach { (k, v) ->
                        append(k)
                        append('\t')
                        append(v)
                        append('\n')
                    }
                }
                storageFile.writeText(text)
            }
        }
    )
}

