package controller;

import view.TelaCadastroTermoCompromisso;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class TermoCompromissoController {

    private TelaCadastroTermoCompromisso view;

    private TermoCompromissoDAO termoDAO;
    private EstagioDAO estagioDAO;

    public TermoCompromissoController(TelaDocumentoTermoCompromisso view) {
        this.view = view;
        this.termoDAO = new TermoCompromissoDAO();
        this.estagioDAO = new EstagioDAO();

        inicializarEventos();
        carregarEstagiosNoCombo();
    }

    private void inicializarEventos() {
        view.getCbEstagio().addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    String estagioSelecionado = (String) e.getItem();
                    preencherDadosAutomaticos(estagioSelecionado);
                }
            }
        });

        view.getBtnCancelar().addActionListener(e -> view.dispose());
        view.getBtnSalvar().addActionListener(e -> salvarTermo());
    }

    private void carregarEstagiosNoCombo() {
        // Futuramente, isso virará de um EstagioService.listarTodos();
        view.getCbEstagio().addItem("Selecione...");
        view.getCbEstagio().addItem("Estágio #101 - Maria Silva");
        view.getCbEstagio().addItem("Estágio #102 - João Souza");
    }

    private void preencherDadosAutomaticos(String identificadorEstagio) {
        if (identificadorEstagio.equals("Selecione...")) {
            limparDadosAutomaticos();
            return;
        }

        // Simulando o retorno do banco para ilustrar o preenchimento:
        if (identificadorEstagio.contains("Maria")) {
            view.setNomeAluno("Maria Silva");
            view.setCursoAluno("Sistemas de Informação");
            view.setNomeConcedente("Tech Solutions S/A");
            view.setRepresentanteConcedente("Carlos Mendes");
        } else if (identificadorEstagio.contains("João")) {
            view.setNomeAluno("João Souza");
            view.setCursoAluno("Ciência da Computação");
            view.setNomeConcedente("Inova Web LTDA");
            view.setRepresentanteConcedente("Ana Paula Rocha");
        }
    }

    private void limparDadosAutomaticos() {
        view.setNomeAluno("");
        view.setCursoAluno("");
        view.setNomeConcedente("");
        view.setRepresentanteConcedente("");
    }

    private void salvarTermo() {
        // Lógica de coleta dos dados manuais e salvamento do Termo (como nos anteriores)
        System.out.println("Salvando Termo...");
    }
}