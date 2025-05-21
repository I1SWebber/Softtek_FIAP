package br.com.fiap.softtek.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.menteleve.data.AppDatabase
import br.com.fiap.softtek.data.model.CheckIn
import kotlinx.coroutines.launch

@Composable
fun HistoricoScreen() {
    val backgroundColor = Color(0xFFB6E3B5)
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var checkins by remember { mutableStateOf<List<CheckIn>>(emptyList()) }

    LaunchedEffect(true) {
        scope.launch {
            val db = AppDatabase.getDatabase(context)
            val dao = db.checkInDao()
            checkins = dao.listarTodos()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        Text(
            text = "Histórico de Check-ins",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1C1C1C),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(checkins) { checkIn ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "🗓️ Data: ${checkIn.data}", fontWeight = FontWeight.Medium)
                        Text(text = "Emoji: ${checkIn.emoji}")
                        Text(text = "Sentimento: ${checkIn.sentimento}")
                    }
                }
            }
        }
    }
}
