package br.com.fiap.softtek.api

import br.com.fiap.softtek.api.MotivacionalApi
import br.com.fiap.softtek.api.MotivacionalApiImpl

object MotivacionalApiService {
    fun getInstance(): MotivacionalApi {
        return MotivacionalApiImpl()
    }
}
