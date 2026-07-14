package br.com.icaroms.codex.repository

import br.com.icaroms.codex.BuildConfig
import br.com.icaroms.codex.network.JogoRawg
import br.com.icaroms.codex.network.RetrofitInstance

class GameRepository {

    suspend fun buscarJogos(): List<JogoRawg> {
        val resposta = RetrofitInstance.api.getGames(
            apiKey = BuildConfig.RAWG_API_KEY,
            pageSize = 20
        )
        return if (resposta.isSuccessful) {
            resposta.body()?.results ?: emptyList()
        } else {
            emptyList()
        }
    }
}