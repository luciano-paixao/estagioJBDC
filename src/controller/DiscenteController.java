package controller;

import models.discente.Discente;
import models.discente.SituacaoDiscente;
import models.pessoa.Pessoa;
import models.pessoa.Sexo;
import models.periodoLetivo.PeriodoLetivo;
import view.TelaCadastroDiscente;
import view.PainelPessoa;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscenteController {

    private TelaCadastroDiscente view;

    private DiscenteDAO discenteDAO;
    private PessoaDAO pessoaDAO;

    public DiscenteController(TelaCadastroDiscente view) {
        this.view = view;
        this.discenteDAO = new DiscenteDAO();
        this.pessoaDAO = new PessoaDAO();

        inicializarEventos();
        carregarDadosIniciais();
    }

    private void inicializarEventos() {
        // Mapeando ação do botão Salvar
        this.view.getBtnSalvar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarDiscente();
            }
        });

        // Mapeando ação do botão Cancelar
        this.view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void carregarDadosIniciais() {
        // Aqui, futuramente, você chamaria um PeriodoLetivoService para listar do banco.
        // Por enquanto, criamos um "mock" (dado falso) apenas para testar a interface.
        PeriodoLetivo periodoMock = new PeriodoLetivo();
        view.adicionarPeriodoLetivo(periodoMock);
        // Supondo que sua classe PeriodoLetivo tenha métodos como setAno e setPeriodo:
        // periodoMock.setAno(2026);
        // periodoMock.setPeriodo(1);

        view.adicionarPeriodoLetivo(periodoMock);
    }

    private void salvarDiscente() {
        try {
            // Resgatar dados de pessoa
            PainelPessoa painelPessoa = view.getPainelPessoa();

            String nome = view.getPainelPessoa().getNome();
            String cpf = view.getPainelPessoa().getCpf();
            String email = painelPessoa.getEmail();
            String dataNascimento = painelPessoa.getDataNascimento();
            Sexo sexo = painelPessoa.getSexoSelecionado();
            String cargo = painelPessoa.getCargo();

            // Resgatar dados de discente
            String matricula = view.getMatricula();
            String curso = view.getCurso();
            SituacaoDiscente situacao = view.getSituacaoSelecionada();
            PeriodoLetivo periodo = view.getPeriodoLetivoSelecionado();

            if (nome.trim().isEmpty() || matricula.trim().isEmpty()) {
                view.exibirMensagem("Preencha os campos obrigatórios (Nome e Matrícula).");
                return;
            }

            // Montar Entidades
            Pessoa novaPessoa = new Pessoa();
            // novaPessoa.setNome(nome);
            // novaPessoa.setCpf(cpf);

            Discente novoDiscente = new Discente();
            // novoDiscente.setPessoa(novaPessoa);
            // novoDiscente.setMatricula(matricula);
            // novoDiscente.setCurso(curso);
            // novoDiscente.setSituacao(situacao);
            // novoDiscente.setPeriodoLetivo(periodo);

            // Enviar para o Service (Comentado por enquanto)
            // boolean sucesso = service.salvar(novoDiscente);
            boolean sucesso = true; // Apenas para simulação!

            if (sucesso) {
                view.exibirMensagem("Discente cadastrado com sucesso!");
                view.limparCampos();
            } else {
                view.exibirMensagem("Erro ao cadastrar o discente no banco de dados.");
            }

        } catch (Exception ex) {
            view.exibirMensagem("Erro inesperado: " + ex.getMessage());
        }
    }
}