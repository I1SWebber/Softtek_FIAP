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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.softtek.util.dispararNotificacaoAgora

@Composable
fun HomeScreen(onNavigate: (String) -> Unit) {
    val backgroundColor = Color(0xFFB6E3B5)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .padding(top = 24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bem-vindo ao MenteLeve 🌿",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C1C1C)
        )

        MenuButton("Avaliação Psicossocial") { onNavigate("avaliacao") }
        MenuButton("Diário de Humor") { onNavigate("diario") }
        MenuButton("Histórico de Check-ins") { onNavigate("historico") }
        MenuButton("Dashboards") { onNavigate("graficos") }
    }
}

@Composable
fun MenuButton(label: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .background(color = Color.White, shape = RoundedCornerShape(16.dp))
            .padding(horizontal = 16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(text = label, fontSize = 18.sp, color = Color(0xFF2E7D32), fontWeight = FontWeight.Medium)
    }
}
