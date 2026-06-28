# Primeira Anotação:
## Diferença entre QA x Teste:
- QA: Garante a qualidade
- Teste: Atividade de encontrar defeitos

## Testar não prova a ausência de bugs, porém, mostra a presença de bugs.

## Níveis e Tipos de Teste

### Caderno do Caçador:

#### - Unitário/Componente - cartao(), traz as informações corretas do Jogo?
#### - Integração - A Tela abre corretamentes o Jogo selecionado com seus detalhes?
#### - Sistema - A pesquisa de um jogo específico é exibida corretamente?
#### - Aceitação - O Sistema devolve corretamente o que o cliente desejou, um banco de dados com diversas informações de jogos

### Conexões

#### A) Verificar se Jogo.selo devolve "Lendário" para nota 9.0 -> Unitário/Componente : Funcional
#### B) Medir se a lista com 100 jogos rola sem travar no aparelho -> Sistema : Não-Funcional
#### C) Conferir se a tela de lista exibe os dados vindos do Repository -> Integração : Funcional
#### D) Um amigo usa o Codex e diz se o app resolve o que promete -> Aceitação
### E) Verificar se Estudio.mediaNotas devolve 0.0 com lista vazia -> Unitário : Funcional
### F) Testar se o Codex abre bem em aparelhos de tela pequena -> Não-Funcional(Compatibilidade)

# --------------------------------------------------------------------------------------------------

### CT-11-02 — Exibição da Lista 
### Passos:     1. Abrir o Preview da ListaComTitulo
###             2. Rolar a Lista
### Esperado:  Os itens aparecem, uma por jogo da lista, na ordem.
### Nível/Tipo: Componente · Funcional
### Veredito:  Passou

# --------------------------------------------------------------------------------------------------

## Registra teste de auth da RAWG no caderno de QA

## baseUrl SEMPRE termina com "/".

# --------------------------------------------------------------------------------------------------

## - Com internet: OK 200 + JSON no Logcat -> Passou
## - Modo Avião: "Falha de rede", sem crash -> Passou
## - Lição: rede SEMPRE no try/catch. Caminho de falha é teste obrigatório.

# --------------------------------------------------------------------------------------------------

## Sempre testar o valor exato do limite, e os valores na fronteira 