package br.com.fiap.softtek.api

import kotlinx.coroutines.delay

class MotivacionalApiImpl : MotivacionalApi {

    private val frases = mapOf(
        "Triste 🙁" to listOf(
            "Você é mais forte do que imagina.",
            "Dias difíceis também acabam.",
            "Não está sozinho, respire e siga em frente."
        ),
        "Alegre 😀" to listOf(
            "Continue espalhando essa alegria!",
            "Seu sorriso faz diferença.",
            "A felicidade contagia, compartilhe-a!"
        ),
        "Cansado 😩" to listOf(
            "Você merece descansar.",
            "Não se cobre tanto, um passo de cada vez.",
            "Cuide de você com carinho."
        ),
        "Ansioso 😫" to listOf(
            "Tente respirar fundo. Vai passar.",
            "Seu sentimento é válido. Você está indo bem.",
            "Confie em si mesmo, um dia de cada vez."
        ),
        "Medo 😱" to listOf(
            "Coragem não é ausência de medo, é agir apesar dele.",
            "Você está seguro agora.",
            "Tudo vai ficar bem."
        ),
        "Raiva 😤" to listOf(
            "Respire fundo, você está no controle.",
            "Está tudo bem sentir raiva.",
            "Transforme essa energia em força positiva."
        )
    )

    override suspend fun getMensagem(emoji: String): MensagemResposta {
        delay(500) // simula uma API real
        val lista = frases[emoji] ?: listOf("Tudo bem sentir o que você está sentindo.")
        return MensagemResposta(lista.random())
    }
}
