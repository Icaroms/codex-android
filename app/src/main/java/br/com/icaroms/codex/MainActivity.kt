package br.com.icaroms.codex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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