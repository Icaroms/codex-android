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
        get() = when {
            nota() >= 4.9 -> "Lendário"
            nota() >= 4.0 -> "Excelente"
            nota() >= 3.0 -> "Bom"
            else -> "Ruim"
        }

    override fun cartao(): String = "${super.cartao()} $selo (${nota()})"
}

class Dlc(
    id: Int,
    nome: String,
    private val avaliacao: Double,
    val jogoBase: String
) : EntidadeCodex(id, nome), Avaliavel {

    override fun nota(): Double = avaliacao

    override fun cartao(): String = "${super.cartao()} · [DLC de $jogoBase] (${nota()})"
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

fun main() {
    val zelda = Jogo(1, "Tears of the Kingdom", 4.9, listOf("Switch"))
    val pong = Jogo(2, "Pong Demo", 3.2, listOf("Web"))
    val kenshi = Jogo(3, "Kenshi", 4.0, listOf("PC"))
    // val tes = Jogo(4, "Skyrim", -1, listOf("PC"))
    // val theGuild = Jogo(5, "The Guild 3", 99, listOf("PC"))
    val metroid = Jogo(6, "Metroid Dread", 4.5, listOf("Switch"))
    // DLC
    val elderShadow = Dlc(1, "Shadow of the Erdtree", 4.9, "Elden Ring")
    val oldLands = Dlc(2, "The Old Lands", 5.0, "Kenshi")

    println(zelda.cartao()) // #1 - Tears of the Kingdom * Recomendado(4.9)
    println(pong.cartao()) // #2 - Pong Demo - Comum(3.2)
    println(kenshi.cartao()) // #3 - Kenshi - * Recomendado(4.0)
    // println(tes.cartao()) // #4 - Skyrim - Comum(-1)
    // println(theGuild.cartao()) // #5 - The Guild - * Recomendado(99)
    println(metroid.cartao()) // #6 - Metroid Dread = * Recomendado(4.5)

    // Estudios
    // Monto o estúdio passando a LISTA de jogos
    val nintendo = Estudio(10, "Nintendo", listOf(zelda, metroid))

    println("${nintendo.nome}: ${nintendo.nota()}") // Nintendo: 4.7
    println(nintendo.ehBemAvaliado())

    // DLC
    println(elderShadow.cartao())
    println(oldLands.cartao())
}