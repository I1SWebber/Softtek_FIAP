package br.com.fiap.menteleve.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.softtek.data.model.CheckIn

@Dao
interface CheckInDao {
    @Insert
    suspend fun inserir(checkIn: CheckIn)

    @Query("SELECT * FROM checkin ORDER BY id DESC")
    suspend fun listarTodos(): List<CheckIn>

    @Query("SELECT emoji, COUNT(*) as total FROM checkin GROUP BY emoji ORDER BY total DESC")
    suspend fun contarPorEmoji(): List<EmojiCount>

    data class EmojiCount(val emoji: String, val total: Int)

}
