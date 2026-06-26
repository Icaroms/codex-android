package br.com.icaroms.codex

data class Criatura(val nome: String, val poder: Int) {
    fun classe(): String {
        return when {
            poder < 50 -> "Comum"
            poder < 90 -> "Elite"
            else -> "Lendária"
        }
    }
    fun resumo(): String {
        return "$nome - Poder $poder (${classe()})"
    }
}