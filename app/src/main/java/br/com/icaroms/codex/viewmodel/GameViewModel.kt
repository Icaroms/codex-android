package br.com.icaroms.codex.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.icaroms.codex.repository.GameRepository
import br.com.icaroms.codex.network.JogoRawg
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/** O Oráculo do Codex - Guarda o estado e lógica a partir da M22. */
class GameViewModel(

    private val repository: GameRepository = GameRepository()
) : ViewModel() {

    private val _jogos = MutableStateFlow<List<JogoRawg>>(emptyList())
    val jogos: StateFlow<List<JogoRawg>> = _jogos

    init {
        buscarJogos()
    }
    private fun buscarJogos() {
        viewModelScope.launch {
            try {
                _jogos.value = repository.buscarJogos()
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