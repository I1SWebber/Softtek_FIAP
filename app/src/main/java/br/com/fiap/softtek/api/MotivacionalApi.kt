package br.com.fiap.softtek.api

import retrofit2.http.GET
import retrofit2.http.Query

data class MensagemResposta(val mensagem: String)

interface MotivacionalApi {
    @GET("responder")
    suspend fun getMensagem(@Query("emoji") emoji: String): MensagemResposta
}
