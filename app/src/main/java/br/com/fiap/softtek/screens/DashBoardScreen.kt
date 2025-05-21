package br.com.fiap.softtek.screens


import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.menteleve.data.AppDatabase
import br.com.fiap.softtek.data.model.Avaliacao
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import br.com.fiap.softtek.screens.util.OptionItem


@Composable
fun DashboardScreen() {

    var mediasMap by remember { mutableStateOf<Map<String, Double>>(emptyMap()) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val backgroundColor = Color(0xFFB6E3B5)

    var emojisContados by remember { mutableStateOf(listOf<Pair<String, Int>>()) }
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        scope.launch {
            val db = AppDatabase.getDatabase(context)
            val checkinDao = db.checkInDao()
            val avaliacaoDao = db.avaliacaoDao()

            emojisContados = checkinDao.contarPorEmoji().map { it.emoji to it.total }

            val medias = avaliacaoDao.mediasAutoavaliacao()
            mediasMap = mapOf(
                "Motivação" to medias.motivacao,
                "Estresse" to medias.estresse,
                "Apoio" to medias.apoio,
                "Equilíbrio" to medias.equilibrio,
                "Sono" to medias.sono
            )
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Dashboards 📊", fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Text("Médias da Autoavaliação", fontWeight = FontWeight.Bold, fontSize = 17.sp)

        mediasMap.forEach { (titulo, media) ->
            CardInfo(titulo, String.format("%.1f / 5", media))
        }


        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Emoções mais registradas", fontWeight = FontWeight.Bold, fontSize = 17.sp)

                Spacer(modifier = Modifier.height(12.dp))

                emojisContados.forEach { (emoji, total) ->
                    BarChartRow(emoji, total)
                }
            }
        }

    }
}

@Composable
fun CardInfo(titulo: String, valor: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(titulo, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            Text(valor, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2E7D32))
        }
    }
}

@Composable
fun BarChartRow(label: String, value: Int) {
    val barMaxWidth = 200.dp
    val barWidth = if (value > 0) (value * 20).dp else 4.dp

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {

        Text(label, modifier = Modifier.width(100.dp), fontSize = 16.sp, fontWeight = FontWeight.Medium)
        Box(
            modifier = Modifier
                .height(20.dp)
                .width(barWidth.coerceAtMost(barMaxWidth))
                .background(Color(0xFF2E7D32), shape = RoundedCornerShape(4.dp))
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text("$value", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.width(12.dp))
    }
}
