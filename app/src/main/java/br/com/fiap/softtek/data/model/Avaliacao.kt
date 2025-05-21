package br.com.fiap.softtek.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "avaliacoes")
data class Avaliacao(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    // Autoavaliação Semanal
    val motivacao: Int,
    val estresse: Int,
    val apoio: Int,
    val equilibrio: Int,
    val sono: Int,

    // Avaliação Psicossocial
    val cargaExcessiva: Int,
    val pressaoPorMetas: Int,
    val reconhecimento: Int,
    val conflitos: Int,
    val liberdadeExpressao: Int,

    val data: String
)
