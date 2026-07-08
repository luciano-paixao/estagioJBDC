//package controller;
//
//import DAO.EstagioDAO;
//import DAO.TermoCompromissoDAO;
//
//import models.estagio.Estagio;
//
//import view.TelaCadastroTermoCompromisso;
//
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//import java.util.List;
//
//public class TermoCompromissoController {
//
//    private TelaCadastroTermoCompromisso view;
//    private TermoCompromissoDAO termoDAO;
//    private EstagioDAO estagioDAO;
//
//    public TermoCompromissoController(TelaCadastroTermoCompromisso view) {
//        this.view = view;
//        this.termoDAO = new TermoCompromissoDAO();
//        this.estagioDAO = new EstagioDAO();
//
//        inicializarEventos();
//        carregarEstagiosNoCombo();
//    }
//
//    private void inicializarEventos() {
//        view.getCbEstagio().addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                if (e.getStateChange() == ItemEvent.SELECTED) {
//                    Estagio estagioSelecionado = (Estagio) e.getItem();
//                    preencherDadosAutomaticos(estagioSelecionado);
//                }
//            }
//        });
//
//        view.getBtnCancelar().addActionListener(e -> view.dispose());
//        view.getBtnSalvar().addActionListener(e -> salvarTermo());
//    }
//
//    private void carregarEstagiosNoCombo() {
//        try {
//            view.getCbEstagio().removeAllItems();
//
//            // Busca os estágios no banco (o ideal seria listar apenas os sem termo ainda)
//            List<Estagio> estagios = estagioDAO.listarTodos();
//
//            for (Estagio estagio : estagios) {
//                view.getCbEstagio().addItem(estagio);
//            }
//        } catch (Exception e) {
//            view.exibirMensagem("Erro ao buscar estágios: " + e.getMessage());
//        }
//    }
//
//    private void preencherDadosAutomaticos(String identificadorEstagio) {
//        if (estagio == null) {
//            limparDadosAutomaticos();
//            return;
//        }
//
//        try {
//            view.setNomeAluno(estagio.getDiscente().getPessoa().getNome());
//            view.setCursoAluno(estagio.getDiscente().getCurso());
//            view.setNomeConcedente(estagio.getConcedente().getNome());
//            view.setRepresentanteConcedente(estagio.getConcedente().getRepresentante().getNome());
//        } catch (Exception e) {
//            view.exibirMensagem("Aviso: Dados incompletos no estágio selecionado.");
//        }
//    }
//
//    private void limparDadosAutomaticos() {
//        view.setNomeAluno("");
//        view.setCursoAluno("");
//        view.setNomeConcedente("");
//        view.setRepresentanteConcedente("");
//    }
//
//    private void salvarTermo() {
//        // Lógica de coleta dos dados manuais e salvamento do Termo (como nos anteriores)
//        System.out.println("Salvando Termo...");
//    }
//}