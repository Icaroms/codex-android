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

### --------------------------------------------------------------------------------------------------

### CT-11-02 — Exibição da Lista 
### Passos:     1. Abrir o Preview da ListaComTitulo
###             2. Rolar a Lista
### Esperado:  Os itens aparecem, uma por jogo da lista, na ordem.
### Nível/Tipo: Componente · Funcional
### Veredito:  Passou

### --------------------------------------------------------------------------------------------------

## Registra teste de auth da RAWG no caderno de QA

## baseUrl SEMPRE termina com "/".

### --------------------------------------------------------------------------------------------------

## - Com internet: OK 200 + JSON no Logcat -> Passou
## - Modo Avião: "Falha de rede", sem crash -> Passou
## - Lição: rede SEMPRE no try/catch. Caminho de falha é teste obrigatório.

### --------------------------------------------------------------------------------------------------

## Sempre testar o valor exato do limite, e os valores na fronteira

### --------------------------------------------------------------------------------------------------

- Com @SerializedName: imagemUrl = URL real -> PASSOU
- Sem a anotação: imagemUrl = null, SEM crash e SEM erro no log
- Lição: erro de mapeamento é SILENCIOSO. Nome do campo = chave JSON exata, ou @SerializedName. Campo anulável evitou o crash.

### --------------------------------------------------------------------------------------------------

## CT-18-01 - Lista com dados reais (recomposição)
- Com internet: 20 jogos aparecem sem interação -> Passou
- Modo avião: sem crash, "Falha de rede" no log -> Passou
- Débito registrado: tela vazia sem aviso ao usuário(UX). Correção planejada: M33(loading/vazio/erro).

### --------------------------------------------------------------------------------------------------

## CT-19-01 - Carregamento de capas(coil)
- URL válida: 20 capas na lista -> Passou
- URL quebrada: sem crash, espaço vazio no lugar -> Passou
- Débito: falha visual sem aviso(sem placeholder/erro) - Conserto agendado: M33, junto do débito do M18

### --------------------------------------------------------------------------------------------------

## CT-20-01 - Tela de detalhe com dados reais
- 3 jogos diferentes: cada detalhe mostra 0 jogo tocado -> Passou
- Modo avião: sem crash, mas "carregando..." eterno -> Passou
-  Família de débitos UX(M18/M19/M20) -> Conserto na M33

### --------------------------------------------------------------------------------------------------

- ID -> CT-20-01
- Título -> A tela de detalhe exibe os dados do jogo selecionado
- Rastreabilidade -> M20 - A Câmara de Detalhes
- Pré-condição -> App instalado, Internet ligada, lista já carregada com jogos
- Dados de teste -> 3 jogos distintos da lista
- Passos -> 1. Na lista, toque no primeiro jogo; 2. Observe a lista de detalhe; 3. Volte com o botão de voltar; 3. Repita com o 2 e o 3 jogo.
- Resultado esperado -> Cada detalhe mostra capa, nome, nota do jogo tocado(3/3 corretos)
- resultado real -> 3/3 Corretos
- Status -> Passou

### --------------------------------------------------------------------------------------------------

## CT-23-01 - Isolamento do GameViewModel(Repository)
- Regressão: Lista carrega normalmente, mesmos jogos de sempre -> Passou
- Estrutural: "Retrofit" não aparece mais em GameViewModel.kt, só em GameRespository..kt -> Passou
- ViewModel agora depende só do Repository(não mais do Retrofit direto)