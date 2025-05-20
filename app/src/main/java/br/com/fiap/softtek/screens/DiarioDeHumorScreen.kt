package br.com.fiap.menteleve.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DiarioDeHumorScreen() {
    val backgroundColor = Color(0xFFB6E3B5)
    var selectedEmoji by remember { mutableStateOf("") }
    var selectedFeeling by remember { mutableStateOf("") }

    val emojis = listOf("Triste 🙁", "Alegre 😀", "Cansado 😩", "Ansioso 😫", "Medo 😱", "Raiva 😤")
    val sentimentos = listOf("Motivado", "Cansado", "Preocupado", "Estressado", "Animado", "Satisfeito")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Check-in",
            fontSize = 20.sp,
            color = Color.DarkGray
        )

        Text(
            text = "Mapeamento de Riscos - Ansiedade / Depressão / Burnout",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C1C1C)
        )

        Spacer(modifier = Modifier.height(8.dp))
        Text("Escolha o seu emoji de hoje!", fontSize = 16.sp)

        emojis.forEach { item ->
            OptionItem(
                label = item,
                selected = selectedEmoji == item,
                onSelect = { selectedEmoji = item }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Como você se sente hoje?", fontSize = 16.sp)

        sentimentos.forEach { item ->
            OptionItem(
                label = item,
                selected = selectedFeeling == item,
                onSelect = { selectedFeeling = item }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                // Aqui pode salvar no banco, exibir mensagem ou navegar
                println("Emoji: $selectedEmoji | Sentimento: $selectedFeeling")
            },
            modifier = Modifier
                .height(55.dp)
                .width(300.dp)
                .align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(Color(0xFF2E7D32))

        ) {
            Text("Registrar Check-in", fontSize = 16.sp, color = Color.White)
        }
    }
}

@Composable
fun OptionItem(label: String, selected: Boolean, onSelect: () -> Unit) {
    val backgroundColor = if (selected) Color(0xFF2E7D32) else Color.White
    val textColor = if (selected) Color.White else Color(0xFF2E7D32)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clickable(onClick = onSelect)
            .background(backgroundColor, RoundedCornerShape(12.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(label, fontSize = 16.sp, color = textColor)
    }
}
