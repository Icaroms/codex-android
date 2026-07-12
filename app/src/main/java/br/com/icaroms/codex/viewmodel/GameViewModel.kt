package br.com.icaroms.codex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.icaroms.codex.BuildConfig
import br.com.icaroms.codex.network.JogoRawg
import br.com.icaroms.codex.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** O Oráculo do Codex - Guarda o estado e lógica a partir da M22. */
class GameViewModel : ViewModel() {

    private val _jogos = MutableStateFlow<List<JogoRawg>>(emptyList())
    val jogos: StateFlow<List<JogoRawg>> = _jogos

    init {
        buscarJogos()
    }

    private fun buscarJogos() {
        viewModelScope.launch {
            try {
                val resposta = RetrofitInstance.api.getGames(
                    apiKey = BuildConfig.RAWG_API_KEY,
                    pageSize = 20
                )
                if (resposta.isSuccessful) {
                    _jogos.value = resposta.body()?.results ?: emptyList()
                }
            } catch (e: Exception) {
                // Tratamento de erro chega no M33 (Estados de Batalha)
            }
        }
    }
}

// 1. StateFlow privado guarda a lista; versão ública e só-leitura
// 2. Ao criar o ViewModel, já dispara a busca(init)
// 3. Sucesso: atualiza o StateFlow com o resultado
// 4. Erro: por enquanto não faz nada (M33 trata isso)