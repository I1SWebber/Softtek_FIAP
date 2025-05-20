package br.com.fiap.menteleve.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CheckInDao {
    @Insert
    suspend fun inserir(checkIn: CheckIn)

    @Query("SELECT * FROM checkin ORDER BY id DESC")
    suspend fun listarTodos(): List<CheckIn>
}
