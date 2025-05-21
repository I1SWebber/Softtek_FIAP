package br.com.fiap.softtek.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import br.com.fiap.softtek.screens.OptionItem


@Composable
fun AvaliacaoScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val backgroundColor = Color(0xFFB6E3B5)

    val perguntasEscala = listOf(
        "Você tem se sentido motivado no trabalho?",
        "Como tem sido seu nível de estresse nos últimos dias?",
        "Você sente que tem apoio emocional quando precisa?",
        "Você consegue equilibrar trabalho e vida pessoal?",
        "Você tem conseguido dormir bem?"
    )

    val perguntasSimNao = listOf(
        "Você sente que sua carga de trabalho está acima do razoável?",
        "Há pressão excessiva por metas ou resultados?",
        "Você sente que seu trabalho é reconhecido adequadamente?",
        "Há conflitos frequentes no ambiente de trabalho?",
        "Você sente que pode falar abertamente sobre seus sentimentos no ambiente de trabalho?"
    )

    val respostasEscala = remember { mutableStateListOf<Int?>(null, null, null, null, null) }
    val respostasSimNao = remember { mutableStateListOf<Int?>(null, null, null, null, null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text("Autoavaliação Semanal", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1C1C1C))

        perguntasEscala.forEachIndexed { index, pergunta ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(pergunta, fontSize = 16.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    (1..5).forEach { nota ->
                        OptionItem(
                            label = nota.toString(),
                            selected = respostasEscala[index] == nota,
                            onSelect = { respostasEscala[index] = nota }
                        )
                    }
                }
            }
        }

        Text("Avaliação Psicossocial", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF1C1C1C))

        perguntasSimNao.forEachIndexed { index, pergunta ->
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(pergunta, fontSize = 16.sp)
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    OptionItem("Sim", respostasSimNao[index] == 1) { respostasSimNao[index] = 1 }
                    OptionItem("Não", respostasSimNao[index] == 0) { respostasSimNao[index] = 0 }
                }
            }
        }

        Button(
            onClick = {
                if (respostasEscala.all { it != null } && respostasSimNao.all { it != null }) {
                    scope.launch {
                        val db = AppDatabase.getDatabase(context)
                        val dao = db.avaliacaoDao()
                        val data = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

                        dao.inserir(
                            Avaliacao(
                                motivacao = respostasEscala[0]!!,
                                estresse = respostasEscala[1]!!,
                                apoio = respostasEscala[2]!!,
                                equilibrio = respostasEscala[3]!!,
                                sono = respostasEscala[4]!!,
                                cargaExcessiva = respostasSimNao[0]!!,
                                pressaoPorMetas = respostasSimNao[1]!!,
                                reconhecimento = respostasSimNao[2]!!,
                                conflitos = respostasSimNao[3]!!,
                                liberdadeExpressao = respostasSimNao[4]!!,
                                data = data
                            )
                        )
                        Toast.makeText(context, "Avaliação registrada com sucesso!", Toast.LENGTH_SHORT).show()
                        navController.navigate("home")
                    }
                } else {
                    Toast.makeText(context, "Responda todas as perguntas.", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2E7D32))
        ) {
            Text("Enviar Avaliação", fontSize = 16.sp, color = Color.White)
        }
    }
}
