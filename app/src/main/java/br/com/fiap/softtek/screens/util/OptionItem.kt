// OptionItem.kt
package br.com.fiap.softtek.screens.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OptionItem(label: String, selected: Boolean, onSelect: () -> Unit) {
    val backgroundColor = if (selected) Color(0xFF2E7D32) else Color.White
    val textColor = if (selected) Color.White else Color(0xFF2E7D32)

    Box(
        modifier = Modifier
            .height(40.dp)
            .widthIn(min = 60.dp)
            .clickable(onClick = onSelect)
            .background(backgroundColor, shape = MaterialTheme.shapes.medium),
        contentAlignment = Alignment.Center
    ) {
        Text(label, fontSize = 16.sp, color = textColor)
    }
}
