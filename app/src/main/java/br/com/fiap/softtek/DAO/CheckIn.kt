package br.com.fiap.menteleve.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkin")
data class CheckIn(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val emoji: String,
    val sentimento: String,
    val data: String // pode ser "2025-05-20", por exemplo
)
