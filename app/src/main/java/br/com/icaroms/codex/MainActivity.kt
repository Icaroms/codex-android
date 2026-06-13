package br.com.icaroms.codex

import android.os.Bundle
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.icaroms.codex.ui.theme.CodexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CodexTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Icaroms",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "O Codex desperta, $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CodexTheme {
        Greeting("Android")
    }
}

@Composable
fun SaudacaoCodex(nomeInvocador: String) {
    Text(text = "Bem-vindo ao Codex, $nomeInvocador")
}

@Preview(showBackground = true)
@Composable
fun SaudacaoCodexPreview() {
    SaudacaoCodex(nomeInvocador = "")
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

@Preview(showBackground = true)
@Composable
fun GaleriaCodex(){
    Column(modifier = Modifier.padding(16.dp)) {
        TituloCodex()
        FichaDoJogo("Kenshi", 10.0, 2011)
        FichaDoJogo("Dragon Age: Origins", 9.9, 2009)
    }
}

@Composable
fun FichaDoEstudio(nome: String, pais: String, anoFundacao: Int) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = nome)
        Row {
            Text(text = "País: $pais")
            Text(text = " - Desde: $anoFundacao")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FichaDoEstudioPreview() {
    FichaDoEstudio(nome = "Gonner Estudio", pais = "Brasil", anoFundacao = 1995)
}

@Composable
fun ListaDeJogos() {
    // lista mock = trinca nome/nota/ano, igual sua FichaDoJogos
    val jogos = listOf(
        Triple("Hollow Knight", 9.4, 2017),
        Triple("Hades", 9.0, 2020),
        Triple("Celeste", 9.2, 2018),
        Triple("Stardew Valley", 9.5, 2016),
        Triple("Kenshi", 10.0, 2018),
        Triple("Dragon Age: Origins", 9.8, 2010),
        Triple("Harvest Moon: One World", 8.3, 2021),
        Triple("Rune Factory 3", 9.1, 2023),
        Triple("Digimon World 3", 9.9, 2001),
        Triple("Stardew Valley", 9.5, 2016),
        Triple("Fable 1", 9.7, 2004),
        Triple("The Sims 4", 8.4, 2018),
        Triple("The Elder Scrolls: Skyrim", 9.7, 2011),
    )
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(jogos) { jogo ->
            FichaDoJogo(nome = jogo.first, nota = jogo.second, ano = jogo.third)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun listaDeJogosPreview() {
    ListaDeJogos()
}

@Composable
fun ListaComTitulo() {
    Column {
        TituloCodex()
        // lista mock = trinca nome/nota/ano, igual sua FichaDoJogos
        val jogos = listOf(
            Triple("Hollow Knight", 9.4, 2017),
            Triple("Hades", 9.0, 2020),
            Triple("Celeste", 9.2, 2018),
            Triple("Stardew Valley", 9.5, 2016),
            Triple("Kenshi", 10.0, 2018),
            Triple("Dragon Age: Origins", 9.8, 2010),
            Triple("Harvest Moon: One World", 8.3, 2021),
            Triple("Rune Factory 3", 9.1, 2023),
            Triple("Digimon World 3", 9.9, 2001),
            Triple("Stardew Valley", 9.5, 2016),
            Triple("Fable 1", 9.7, 2004),
            Triple("The Sims 4", 8.4, 2018),
            Triple("The Elder Scrolls: Skyrim", 9.7, 2011),
        )
        LazyColumn(modifier = Modifier.padding(8.dp)) {
            items(jogos) { jogo ->
                FichaDoJogo(nome = jogo.first, nota = jogo.second, ano = jogo.third)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListaComTituloPreview() {
    ListaComTitulo()
}

@Composable
fun BotaoFavorito(nomeJogo: String) {
    var favoritado by remember {mutableStateOf(false)}

    Text(
        text = if(favoritado) "★ Favoritado" else "☆ $nomeJogo",
        modifier = Modifier
            .padding(16.dp)
            .clickable {favoritado = !favoritado}
    )
}

@Preview(showBackground = true)
@Composable
fun BotaoFavoritoPreview() {
    BotaoFavorito("Hades")
}