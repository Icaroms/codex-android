package br.com.icaroms.codex.network

import com.google.gson.annotations.SerializedName

data class JogoRawg(val name: String, val released: String?, val rating: Double, @SerializedName("background_image") val imagemUrl: String?)

data class RespostaJogos(val count: Int, val results: List<JogoRawg>)