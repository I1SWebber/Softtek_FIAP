package br.com.fiap.softtek.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.fiap.softtek.data.model.Avaliacao

@Dao
interface AvaliacaoDao {
    @Insert
    suspend fun inserir(avaliacao: Avaliacao)

    @Query("SELECT * FROM avaliacoes ORDER BY id DESC")
    suspend fun listarTodas(): List<Avaliacao>
}
