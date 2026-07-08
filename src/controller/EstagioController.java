//package controller;
//
//import DAO.*;
//import models.concedente.Concedente;
//import models.coordenadorEstagio.CoordenadorEstagio;
//import models.discente.Discente;
//import models.estagio.Estagio;
//import models.periodoLetivo.PeriodoLetivo;
//import models.supervisorConcedente.SupervisorConcedente;
//
//import view.TelaCadastroEstagio;
//
//import java.util.List;
//
//public class EstagioController {
//
//    private TelaCadastroEstagio view;
//
//    // Instanciando todos os DAOs necessários para popular a Aba 2
//    private EstagioDAO estagioDAO;
//    private DiscenteDAO discenteDAO;
//    private ConcedenteDAO concedenteDAO;
//    private SupervisorDAO supervisorDAO;
//    private CoordenadorDAO coordenadorDAO;
//    private PeriodoLetivoDAO periodoDAO;
//
//    public EstagioController(TelaCadastroEstagio view) {
//        this.view = view;
//        this.estagioDAO = new EstagioDAO();
//        this.discenteDAO = new DiscenteDAO();
//        this.concedenteDAO = new ConcedenteDAO();
//        this.supervisorDAO = new SupervisorDAO();
//        this.coordenadorDAO = new CoordenadorDAO();
//        this.periodoDAO = new PeriodoLetivoDAO();
//
//        inicializarEventos();
//        carregarListasDeVinculos();
//    }
//
//    private void inicializarEventos() {
//        view.getBtnSalvar().addActionListener(e -> salvarEstagioCompleto());
//        view.getBtnCancelar().addActionListener(e -> view.dispose());
//    }
//
//    private void carregarListasDeVinculos() {
//        try {
//            // Limpa as listas primeiro (boa prática para evitar duplicação)
//            view.getCbDiscente().removeAllItems();
//            view.getCbConcedente().removeAllItems();
//            view.getCbSupervisor().removeAllItems();
//            view.getCbCoordenador().removeAllItems();
//            view.getCbPeriodo().removeAllItems();
//
//            // Busca os dados reais do banco usando os DAOs
//            List<Discente> discentes = discenteDAO.listarTodos();
//            List<Concedente> concedentes = concedenteDAO.listarTodos();
//            List<SupervisorConcedente> supervisores = supervisorDAO.listarTodos();
//            List<CoordenadorEstagio> coordenadores = coordenadorDAO.listarTodos();
//            List<PeriodoLetivo> periodos = periodoDAO.listarTodos();
//
//            // Preenche os ComboBoxes com os objetos retornados
//            for (Discente d : discentes) {
//                view.getCbDiscente().addItem(d);
//            }
//            for (Concedente c : concedentes) {
//                view.getCbConcedente().addItem(c);
//            }
//            for (SupervisorConcedente s : supervisores) {
//                view.getCbSupervisor().addItem(s);
//            }
//            for (CoordenadorEstagio c : coordenadores) {
//                view.getCbCoordenador().addItem(c);
//            }
//            for (PeriodoLetivo p : periodos) {
//                view.getCbPeriodo().addItem(p);
//            }
//
//        } catch (Exception e) {
//            view.exibirMensagem("Erro ao carregar dados do banco: " + e.getMessage());
//        }
//    }
//
//    private void salvarEstagioCompleto() {
//
//        try {
//            // CapturA os objetos das chaves estrangeiras
//            Discente discenteSelecionado = (Discente) view.getCbDiscente().getSelectedItem();
//            Concedente concedenteSelecionada = (Concedente) view.getCbConcedente().getSelectedItem();
//            SupervisorConcedente supervisorSelecionado = (SupervisorConcedente) view.getCbSupervisor().getSelectedItem();
//            CoordenadorEstagio coordenadorSelecionado = (CoordenadorEstagio) view.getCbCoordenador().getSelectedItem();
//            PeriodoLetivo periodoSelecionado = (PeriodoLetivo) view.getCbPeriodo().getSelectedItem();
//
//            // Instancia e popula o Estágio
//            Estagio novoEstagio = new Estagio();
//            novoEstagio.setDiscente(discenteSelecionado);
//            novoEstagio.setConcedente(concedenteSelecionada);
//            novoEstagio.setSupervisorConcedente(supervisorSelecionado);
//            novoEstagio.setCoordenadorEstagio(coordenadorSelecionado);
//            novoEstagio.setPeriodoLetivo(periodoSelecionado);
//            //novoEstagio.setCargaHoraria(Integer.parseInt(view.getCargaHoraria()));
//            // COMPLETAR!!!!!
//
//            // Primeiro salva o Estágio para gerar o ID (a Chave Primária)
//            //estagioDAO.cadastrarEstagio(novoEstagio);
//
//            // Depois você salvaria os documentos vinculados a ele
//            // apoliceDAO.salvar(novaApolice, novoEstagio.getId());
//            // planoDAO.salvar(novoPlano, novoEstagio.getId());
//
//            view.exibirMensagem("Estágio cadastrado com sucesso!");
//            view.dispose();
//
//        } catch (Exception ex) {
//            view.exibirMensagem("Erro ao gravar estágio: " + ex.getMessage());
//        }
//    }
//}