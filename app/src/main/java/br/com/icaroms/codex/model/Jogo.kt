package br.com.icaroms.codex.model

// INTERFACE - um contrato? quem assina promete saber se avaliar
interface Avaliavel {
    fun nota(): Double
    fun ehBemAvaliado(): Boolean = nota() >= 4.0 // Regra Padrão
}

open class EntidadeCodex(val id: Int, val nome: String) {
    open fun cartao(): String = "#$id - $nome"
}

class Jogo(
    id: Int,
    nome: String,
    private val avaliacao: Double, // ENCAPSULAMENTO
    val plataformas: List<String>
) : EntidadeCodex(id, nome), Avaliavel {

    override fun nota(): Double = avaliacao

    val selo: String
        get() = if (ehBemAvaliado()) "* Recomendado" else "- Comum"

    override fun cartao(): String = "${super.cartao()} $selo (${nota()})"
}

class Estudio(
    id: Int,
    nome: String,
    private val jogos: List<Jogo>
) : EntidadeCodex(id, nome), Avaliavel {

    override fun nota(): Double {
        if (jogos.size == 0) return 0.0 // Estúdio sem jogos. caso-limite

        var soma = 0.0
        for (jogo in jogos) { // percorre a lista, um jogo por vez
            soma = soma + jogo.nota()
        }
        return soma / jogos.size // total / quantidade = médio
    }

}