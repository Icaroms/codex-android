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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import br.com.icaroms.codex.network.JogoRawg
import br.com.icaroms.codex.network.RetrofitInstance
import br.com.icaroms.codex.ui.theme.CodexTheme
import coil.compose.AsyncImage

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
fun FichaDoJogo(nome: String, nota: Double, ano: String, imagemUrl: String?) {
    Row(modifier = Modifier.padding(12.dp)) {
        AsyncImage(
            model = imagemUrl,
            contentDescription = nome,
            modifier = Modifier.size(80.dp)
        )
        Column(modifier = Modifier.padding(start = 12.dp)) {
            Text(text = nome)
            Row {
                Text(text = "Nota: $nota")
                Text(text = " - Ano: $ano")

            }
            if (nota >= 4.5) {
                Text(text = "★  Obra-Prima")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FichaDoJogoPreview() {
    FichaDoJogo(nome = "Hollow Knight", nota = 9.4, ano = "2017", imagemUrl = null)
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
            TelaDetalhe(nome = nome, navController = navController)
        }
    }
}

@Composable
fun TelaLista(navController: NavController) {
    var jogos by remember {mutableStateOf(listOf<JogoRawg>())}

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(jogos) {jogo ->
            Box(modifier = Modifier.clickable {
                navController.navigate("detalhe/" + jogo.name)
            }) {
                FichaDoJogo(
                    nome = jogo.name,
                    nota = jogo.rating,
                    ano = jogo.released?.take(4) ?: "?",
                    imagemUrl = jogo.imagemUrl
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        try {
            val resposta = RetrofitInstance.api.getGames(
                apiKey = BuildConfig.RAWG_API_KEY,
                pageSize = 20
            )
            if (resposta.isSuccessful) {
                val corpo = resposta.body()
                Log.d("CODEX", "Total no Reino: " + corpo?.count)
                if (corpo != null) {
                    jogos = corpo.results
                }
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
            Log.d("CODEX", "Busca " + r.code() + " - " + r.body())
        } catch (e: Exception) {
            Log.e("CODEX", "Falha: " + e.message)
        }
    }
}

@Composable
fun TelaDetalhe(nome: String, navController: NavController) {
    var jogo by remember { mutableStateOf<JogoRawg?>(null) }
    LaunchedEffect(Unit) {
        try {
            val r = RetrofitInstance.api.searchGames(
                apiKey = BuildConfig.RAWG_API_KEY,
                term = nome, pageSize = 1
            )
            if (r.isSuccessful) {
                val corpo = r.body()
                if (corpo != null && corpo.results.size > 0) {
                    jogo = corpo.results[0]
                }
            }
        } catch (e: Exception) {
            Log.e("CODEX", "Falha de rede: " + e.message)
        }
    }
    Column(modifier = Modifier.padding(16.dp)) {
        TituloCodex()
        Text(text = "← Voltar",
            modifier = Modifier.clickable { navController.popBackStack() })
        if (jogo == null) {
            Text(text = "Carregando...")
        } else {
            AsyncImage(
                model = jogo?.imagemUrl,
                contentDescription = jogo?.name,
                modifier = Modifier.size(220.dp)
            )
            Text(text = jogo?.name ?: nome)
            Text(text = "Nota: " + jogo?.rating)
            Text(text = "Lançamento: " + (jogo?.released ?: "?"))
        }
    }
}