//package controller;
//
//import models.planoAtividade.PlanoAtividade; //[cite: 2]
//import view.TelaCadastroPlanoAtividades;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class PlanoAtividadesController {
//
//    private TelaCadastroPlanoAtividades view;
//
////    private PlanoAtividadesDAO planoDAO;
////
////    public PlanoAtividadesController(TelaDocumentoPlanoAtividades view) {
////        this.view = view;
////        this.planoDAO = new PlanoAtividadesDAO();
////        inicializarEventos();
////    }
//
//    private void inicializarEventos() {
//        this.view.getBtnSalvar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                salvarDocumento();
//            }
//        });
//
//        this.view.getBtnCancelar().addActionListener(e -> view.dispose());
//    }
//
//    private void salvarDocumento() {
//        // Extrai os blocos de texto da View
//        String areaAtuacao = view.getAreaAtuacao();
//        String justificativa = view.getJustificativa();
//        String obGerais = view.getObjetivosGerais();
//        String obEspecificos = view.getObjetivosEspecificos();
//        String plano = view.getPlanoAtividades();
//        String adequacao = view.getAdequacaoPedagogica();
//
//        // Empacotamento dos dados
//        PlanoAtividade planoAtividades = new PlanoAtividade();
//
////        planoAtividades.setJustificativa(justificativa);
////        planoAtividades.setObjetivosGerais(obGerais);
//
//        // Enviar para o Service / DAO
//        // boolean salvo = planoAtividadeService.salvar(planoAtividades);
//
//        view.exibirMensagem("Documento salvo e anexado ao estágio com sucesso!");
//    }
//}