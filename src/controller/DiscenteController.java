package controller;

import models.discente.Discente;
import models.discente.SituacaoDiscente;
import models.pessoa.Pessoa;
import models.periodoLetivo.PeriodoLetivo;
import view.TelaCadastroDiscente;

// import service.DiscenteService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiscenteController {

    private TelaCadastroDiscente view;
    // private DiscenteService service;

    public DiscenteController(TelaCadastroDiscente view) {
        this.view = view;
        // this.service = new DiscenteService();

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
        // Supondo que sua classe PeriodoLetivo tenha métodos como setAno e setPeriodo:
        // periodoMock.setAno(2026);
        // periodoMock.setPeriodo(1);

        view.adicionarPeriodoLetivo(periodoMock);
    }

    private void salvarDiscente() {
        try {
            // Resgatar os dados da View
            String nome = view.getNomePessoa();
            String cpf = view.getCpfPessoa();
            String matricula = view.getMatricula();
            String curso = view.getCurso();
            SituacaoDiscente situacao = view.getSituacaoSelecionada();
            PeriodoLetivo periodo = view.getPeriodoLetivoSelecionado();

            // Validar, de maneira básica, campos obrigatórios
            if (nome.isEmpty() || matricula.isEmpty()) {
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