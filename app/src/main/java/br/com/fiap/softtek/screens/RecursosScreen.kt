package br.com.fiap.softtek.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RecursosScreen() {
    val backgroundColor = Color(0xFFB6E3B5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Recursos de Apoio 🌿",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C1C1C)
        )

        RecursoCard(
            titulo = "Canal de Escuta",
            descricao = "Compartilhe anonimamente o que está sentindo e receba acolhimento emocional.",
        )

        RecursoCard(
            titulo = "Grupos de Apoio",
            descricao = "Participe de encontros entre colaboradores com experiências semelhantes.",
        )

        RecursoCard(
            titulo = "Bem-estar e Autocuidado",
            descricao = "Dicas de respiração, alongamentos e conteúdos que ajudam no equilíbrio emocional.",
        )

        RecursoCard(
            titulo = "Apoio Psicológico Profissional",
            descricao = "Caso precise, consulte a disponibilidade de sessões com psicólogos parceiros da empresa.",
        )
    }
}

@Composable
fun RecursoCard(titulo: String, descricao: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* ação futura */ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, fontSize = 18.sp, fontWeight = FontWeight.Medium, color = Color(0xFF2E7D32))
            Spacer(modifier = Modifier.height(8.dp))
            Text(descricao, fontSize = 14.sp, color = Color.DarkGray)
        }
    }
}
