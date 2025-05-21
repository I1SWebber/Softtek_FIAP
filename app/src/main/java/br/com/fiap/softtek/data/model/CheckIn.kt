package br.com.fiap.softtek.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checkin")
data class CheckIn(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val emoji: String,
    val sentimento: String,
    val data: String
)
