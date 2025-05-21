package br.com.fiap.softtek.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import br.com.fiap.softtek.api.MotivacionalApiService
import br.com.fiap.softtek.data.model.CheckIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DiarioDeHumorScreen(navController: NavController) {

    var showDialog by remember { mutableStateOf(false) }
    var mensagemResposta by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFFB6E3B5)
    var selectedEmoji by remember { mutableStateOf("") }
    var selectedFeeling by remember { mutableStateOf("") }

    val emojis = listOf("Triste 🙁", "Alegre 😀", "Cansado 😩", "Ansioso 😫", "Medo 😱", "Raiva 😤")
    val sentimentos = listOf("Motivado", "Cansado", "Preocupado", "Estressado", "Animado", "Satisfeito")

    val scrollState = rememberScrollState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(scrollState)
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
            OptionItem2(
                label = item,
                selected = selectedEmoji == item,
                onSelect = { selectedEmoji = item }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Como você se sente hoje?", fontSize = 16.sp)

        sentimentos.forEach { item ->
            OptionItem2(
                label = item,
                selected = selectedFeeling == item,
                onSelect = { selectedFeeling = item }
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                scope.launch {
                    if (selectedEmoji.isNotBlank() && selectedFeeling.isNotBlank()) {
                        val db = AppDatabase.getDatabase(context)
                        val dao = db.checkInDao()
                        val dataAtual = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                        dao.inserir(
                            CheckIn(
                                emoji = selectedEmoji,
                                sentimento = selectedFeeling,
                                data = dataAtual
                            )
                        )

                        val api = MotivacionalApiService.getInstance()
                        val resposta = api.getMensagem(selectedEmoji)

                        showDialog = true
                        mensagemResposta = resposta.mensagem


                        Toast.makeText(context, "Check-in registrado!", Toast.LENGTH_SHORT).show()

                        selectedEmoji = ""
                        selectedFeeling = ""

                    } else {
                        Toast.makeText(context, "Por favor, preencha ambos os campos!", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Registrar Check-in", fontSize = 16.sp, color = Color.White)
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    navController.navigate("home")
                }) {
                    Text("OK")
                }
            },
            title = { Text("💬") },
            text = { Text(mensagemResposta, fontSize = 16.sp) }
        )
    }


}

@Composable
fun OptionItem2(label: String, selected: Boolean, onSelect: () -> Unit) {
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
