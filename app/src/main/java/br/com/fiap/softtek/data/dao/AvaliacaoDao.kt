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

    @Query("""
    SELECT 
        AVG(motivacao) AS motivacao,
        AVG(estresse) AS estresse,
        AVG(apoio) AS apoio,
        AVG(equilibrio) AS equilibrio,
        AVG(sono) AS sono
    FROM avaliacoes
""")
    suspend fun mediasAutoavaliacao(): MediasAvaliacao

    data class MediasAvaliacao(
        val motivacao: Double,
        val estresse: Double,
        val apoio: Double,
        val equilibrio: Double,
        val sono: Double
    )


}
