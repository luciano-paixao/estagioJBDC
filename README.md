# estagio

Sistema Java Swing para cadastro e acompanhamento de estágios, usando JDBC para falar com o banco.

O projeto foi pensado para atender uma universidade e os cursos ligados a ela, tomando como base o Regimento de Estagio Supervisionado da Unifesspa. A ideia é centralizar os dados de estágio em um só sistema, para que o coordenador de estágio consiga consultar e acompanhar as informações com mais facilidade. O foco de uso da aplicação fica justamente nesse perfil de usuário, visto que normalmente é aquele que precisa ter maior controle dos vínculos, documentos e registros dos estágios.

## Visão geral

A aplicação está dividida em partes:

- `view`: telas Swing e painéis reaproveitáveis.
- `controller`: faz a ligação entre a tela, os modelos e os DAOs.
- `DAO`: salva e consulta os dados no banco.
- `models`: entidades e enums do sistema.
- `database`: conexão com o banco.

O ponto de entrada é [`src/Main.java`](src/Main.java), que abre a tela de cadastro de estágio.

Estrutura principal:

```text
src
├── Main.java
├── controller
├── DAO
├── database
├── models
└── view
```

## Estrutura de módulos

### `src/controller`
Controladores da aplicação. Cada controller recebe uma tela, trata os botões e monta os objetos que vão para o banco.

- `EstagioController`: junta os dados do estágio e carrega os vínculos nos combos.
- `AproveitamentoController`: cuida da solicitação de aproveitamento profissional.
- `ConcedenteController`: cadastro de concedente e dados ligados a ele.
- `CoordenadorController`: cadastro de coordenador de estágio.
- `DiscenteController`: cadastro de discente.
- `DocenteController`: cadastro de docente.
- `PlanoAtividadesController`: cadastro do plano de atividades.
- `SupervisorController`: cadastro do supervisor da concedente.
- `TermoCompromissoController`: cadastro do termo de compromisso.

### `src/DAO`
Camada que conversa com o banco. Os DAOs fazem `INSERT`, consultas como `listarTodos()` e, no caso de `EstagioDAO`, também têm consultas extras e o método `salvar()`.

Os DAOs atuais são:

- `EstagioDAO`
- `PessoaDAO`
- `EnderecoDAO`
- `TelefoneDAO`
- `DiscenteDAO`
- `DocenteDAO`
- `PeriodoLetivoDAO`
- `ConcedenteDAO`
- `SupervisorDAO`
- `CoordenadorDAO`
- `PlanoAtividadeDAO`
- `TermoCompromissoDAO`
- `RelatorioFinalDAO`
- `AproveitamentoProfissionalDAO`
- `ApoliceSeguroDAO`

### `src/database`
Aqui fica a conexão JDBC com o banco.

- `Conexao`: junta URL, usuário e senha do MySQL e devolve a `Connection`.

### `src/models`
Tem as entidades do sistema e os enums usados na aplicação.

#### Entidades principais

- `pessoa`: dados básicos da pessoa ligada aos outros cadastros.
- `endereco`: endereço físico.
- `telefone`: telefones de pessoa ou concedente.
- `discente`: aluno que faz estágio.
- `docente`: professor do sistema.
- `coordenadorEstagio`: coordenador institucional.
- `concedente`: empresa ou instituição concedente.
- `supervisorConcedente`: supervisor da concedente.
- `periodoLetivo`: período acadêmico.
- `estagio`: parte central que liga discente, concedente, supervisor, coordenador e período.
- `termoCompromisso`: dados formais do estágio.
- `planoAtividade`: plano de atividades.
- `relatorioFinal`: relatório final.
- `apoliceSeguro`: apólice do estágio.
- `aproveitamentoProfissional`: pedido de aproveitamento de experiência profissional.

#### Subpacotes e enums

- `models.discente.SituacaoDiscente`
- `models.docente.Titulacao`
- `models.concedente.TipoConcedente`
- `models.estagio.TipoEstagio`, `AmbitoEstagio`, `StatusEstagio`
- `models.telefone.TipoTelefone`
- `models.aproveitamentoProfissional.CondicaoProfissional`, `StatusDeferimento`
- `models.apoliceSeguro.ResponsavelContratacao`
- `models.termoCompromisso.HorasSemanais`

### `src/view`
Telas Swing e painéis reaproveitáveis. A ideia é deixar cada cadastro em uma tela própria e reaproveitar componentes menores onde fizer sentido.

## Arquitetura MVC

O projeto segue uma estrutura parecida com MVC:

- `View` mostra a interface e pega os dados do usuário.
- `Controller` recebe os eventos, monta as entidades e controla o fluxo.
- `DAO` salva e consulta os dados no banco.

Essa divisão ajuda a separar a interface do banco e deixa o código mais organizado.

## Execução

Pré-requisitos:

- JDK instalado.
- MySQL disponível.
- Banco configurado do jeito que a aplicação espera.

Antes de executar, ajuste os dados de conexão em [`src/database/Conexao.java`](src/database/Conexao.java):

- URL do banco
- usuário
- senha

Depois é só rodar a classe `Main`.

## Observações sobre a base atual

- A branch atual está organizada em Swing + JDBC, sem framework web.
- Tem código antigo e partes que ainda estão sendo ajustadas.
- Alguns nomes de pacote e classe ainda convivem com versões mais antigas da modelagem.
