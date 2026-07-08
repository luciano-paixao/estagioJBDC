package controller;

import view.TelaCadastroDiscente;

// Importações dos seus Models
import models.discente.Discente;
import models.discente.SituacaoDiscente;
import models.pessoa.Pessoa;
import models.pessoa.Sexo;
import models.periodoLetivo.PeriodoLetivo;

// Importações dos seus DAOs
import DAO.DiscenteDAO;
import DAO.PessoaDAO;
import DAO.PeriodoLetivoDAO;

import java.util.List;

public class DiscenteController {

    private TelaCadastroDiscente view;

    // Instanciação direta dos DAOs para o MVP
    private DiscenteDAO discenteDAO;
    private PessoaDAO pessoaDAO;
    private PeriodoLetivoDAO periodoDAO;

    public DiscenteController(TelaCadastroDiscente view) {
        this.view = view;

        // Inicializa os DAOs
        this.discenteDAO = new DiscenteDAO();
        this.pessoaDAO = new PessoaDAO();
        this.periodoDAO = new PeriodoLetivoDAO();

        inicializarEventos();
        carregarPeriodosLetivos();
    }

    private void inicializarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarDiscente());
        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void carregarPeriodosLetivos() {
        try {
            // Se o seu combobox tiver um getter assim na View: getCbPeriodoLetivo()
            // Caso contrário, use o método view.adicionarPeriodoLetivo(p) que criamos antes
            view.getPeriodoLetivoSelecionado(); // Apenas para validar se existe

            // Supondo que na view o combo chama "cbPeriodoLetivo" e tem o método getCbPeriodoLetivo()
            // Se não tiver o getCbPeriodoLetivo() na view, recomendo criar para seguir o padrão!

            List<PeriodoLetivo> periodos = periodoDAO.listarTodos();

            for (PeriodoLetivo p : periodos) {
                view.adicionarPeriodoLetivo(p);
            }
        } catch (Exception e) {
            view.exibirMensagem("Erro ao carregar os Períodos Letivos do banco: " + e.getMessage());
        }
    }

    private void salvarDiscente() {
        // 1. Extrair os dados da View (Painel de Pessoa)
        String nome = view.getPainelPessoa().getNome();
        String cpf = view.getPainelPessoa().getCpf();
        String email = view.getPainelPessoa().getEmail();
        String dataNascimentoString = view.getPainelPessoa().getDataNascimento();
        Sexo sexo = view.getPainelPessoa().getSexoSelecionado();
        String cargo = view.getPainelPessoa().getCargo();

        // 2. Extrair os dados da View (Dados Académicos do Discente)
        String matricula = view.getMatricula();
        String curso = view.getCurso();
        SituacaoDiscente situacao = view.getSituacaoSelecionada();
        PeriodoLetivo periodo = view.getPeriodoLetivoSelecionado();

        try {
            // 4. Conversão da String de Data (DD/MM/AAAA) para LocalDate
            java.time.format.DateTimeFormatter formatador = java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy");
            java.time.LocalDate dataNascimento = java.time.LocalDate.parse(dataNascimentoString, formatador);

            /* NOTA: Se o seu Model estiver a usar java.util.Date em vez de LocalDate,
               substitua as duas linhas acima por estas:
               java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
               java.util.Date dataNascimento = sdf.parse(dataNascimentoString);
            */

            Pessoa novaPessoa = new Pessoa();
            novaPessoa.setNome(nome);
            novaPessoa.setCpf(cpf);
            novaPessoa.setEmail(email);
            novaPessoa.setDataNascimento(dataNascimento);
            novaPessoa.setSexo(sexo);
            novaPessoa.setCargo(cargo.isEmpty() ? "Discente" : cargo);

            // 6. Enviar para o DAO guardar a Pessoa (e resgatar o ID gerado)
            // Certifique-se de que o método do seu PessoaDAO se chama 'salvar' ou 'cadastrarPessoa'
            pessoaDAO.cadastrarPessoa(novaPessoa);

            // 7. Instanciar e popular TODOS os sets da Entidade Discente
            Discente novoDiscente = new Discente();
            //novoDiscente.setPessoa(novaPessoa); // Opcional agora, já que passamos no DAO
            novoDiscente.setMatricula(matricula);
            novoDiscente.setCurso(curso.isEmpty() ? "Sistemas de Informação" : curso);
            novoDiscente.setSituacao(situacao);
            //novoDiscente.setPeriodoLetivo(periodo); // Opcional agora

            // 8. CORREÇÃO: Usando o método exato do seu DiscenteDAO com os 3 argumentos
            discenteDAO.cadastrarDiscente(novoDiscente, periodo, novaPessoa);

            // 9. Feedback de Sucesso e limpeza do ecrã
            view.exibirMensagem("Discente cadastrado com sucesso!");
            view.limparCampos();

        } catch (java.time.format.DateTimeParseException e) {
            view.exibirMensagem("A data de nascimento informada é inválida. Use o formato Dia/Mês/Ano.");
        } catch (Exception ex) {
            view.exibirMensagem("Erro ao guardar dados no banco: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}