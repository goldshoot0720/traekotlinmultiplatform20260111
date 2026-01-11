package com.example.traekotlinmultiplatform20260111.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.traekotlinmultiplatform20260111.ui.AppPrimaryVariant
import com.example.traekotlinmultiplatform20260111.ui.TextSecondary

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppPrimaryVariant.copy(alpha = 0.5f)
        ),
        border = BorderStroke(1.dp, TextSecondary.copy(alpha = 0.3f)),
        content = {
            content()
        }
    )
}
