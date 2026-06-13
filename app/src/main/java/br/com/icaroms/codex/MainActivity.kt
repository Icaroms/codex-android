package br.com.icaroms.codex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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