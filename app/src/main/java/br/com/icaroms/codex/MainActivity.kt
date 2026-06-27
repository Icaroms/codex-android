package br.com.icaroms.codex

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.icaroms.codex.network.RetrofitInstance
import br.com.icaroms.codex.ui.theme.CodexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodexTheme {
                AppCodex()
                Log.d("Codex", "Retrofit pronto: " + RetrofitInstance)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TituloCodex() {
    Text(text = "◆ CODEX ◆")
}

@Composable
fun FichaDoJogo(nome: String, nota: Double, ano: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text( text = nome)
        Row {
            Text(text = "Nota: $nota")
            Text(text = " - Ano: $ano")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FichaDoJogoPreview() {
    FichaDoJogo(nome = "Hollow Knight", nota = 9.4, ano = 2017)
}
@Composable
fun AppCodex() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "lista") {
        composable("lista") {
            TelaLista(navController)
        }
        composable("detalhe/{nome}") { entrada ->
            val nome = entrada.arguments?.getString("nome") ?: "?"
            TelaDetalhe(nome = nome)
        }
    }
}

@Composable
fun TelaLista(navController: NavController) {
    val jogos = listOf(
        Triple("Hollow Knight", 9.4, 2017),
        Triple("Hades", 9.0, 2020),
        Triple("Celeste", 9.2, 2018)
    )
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(jogos) {jogo ->
            Box(modifier = Modifier.clickable {
                navController.navigate("detalhe/${jogo.first}")
            }) {
                FichaDoJogo(nome = jogo.first, nota = jogo.second, ano = jogo.third)
            }
        }
    }

    LaunchedEffect(Unit) {
        try {
            val resposta = RetrofitInstance.api.getGames(
                apiKey = BuildConfig.RAWG_API_KEY,
                pageSize = 5
            )
            if (resposta.isSuccessful) {
                val corpo = resposta.body()?.string()
                Log.d("CODEX", "OK " + resposta.code() + " - " + corpo?.take(120))
            } else {
                Log.e("CODEX", "Erro HTTP " + resposta.code())
            }
        } catch (e: Exception) {
            Log.e("CODEX", "Falha de rede: " + e.message)
        }
    }

    LaunchedEffect(Unit) {
        try {
            val r = RetrofitInstance.api.searchGames(
                apiKey = BuildConfig.RAWG_API_KEY,
                term = "witcher",
                pageSize = 3
            )
            Log.d("CODEX", "Busca " + r.code() + " - " + r.body()?.string()?.take(120))
        } catch (e: Exception) {
            Log.e("CODEX", "Falha: " + e.message)
        }
    }
}

@Composable
fun TelaDetalhe(nome: String) {
    Column(modifier = Modifier.padding(16.dp)) {
        TituloCodex()
        Text(text = "Detalhe do Jogo:")
        Text(text = nome)
    }
}