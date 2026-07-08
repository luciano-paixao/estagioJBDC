//package controller;
//
//import models.aproveitamentoProfissional.AproveitamentoProfissional;
//import models.coordenadorEstagio.CoordenadorEstagio;
//import models.discente.Discente;
//import view.TelaCadastroAproveitamento;
////import DAO.AproveitamentoDAO;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//
//public class AproveitamentoController {
//
//    private TelaCadastroAproveitamento view;
//    //private AproveitamentoDAO aproveitamentoDAO;
//
//    public AproveitamentoController(TelaCadastroAproveitamento view) {
//        this.view = view;
//        //this.aproveitamentoDAO = new AproveitamentoDAO(); // Instanciando o DAO
//        inicializarEventos();
//    }
//
//    private void inicializarEventos() {
//        // Evento de abrir o selecionador de arquivos
//        view.getBtnAnexarDocumento().addActionListener(e -> selecionarDocumento());
//
//        view.getBtnSalvar().addActionListener(e -> salvarAproveitamento());
//        view.getBtnCancelar().addActionListener(e -> view.dispose());
//    }
//
//    private void selecionarDocumento() {
//        // Abre o explorador de arquivos nativo do sistema operacional
//        JFileChooser fileChooser = new JFileChooser();
//        fileChooser.setDialogTitle("Selecione o Documento Comprobatório");
//
//        int result = fileChooser.showOpenDialog(view);
//
//        if (result == JFileChooser.APPROVE_OPTION) {
//            // Se o usuário selecionou um arquivo, pegamos o objeto File
//            File arquivoSelecionado = fileChooser.getSelectedFile();
//
//            // Envia o caminho absoluto para a View exibir
//            view.setCaminhoDocumento(arquivoSelecionado.getAbsolutePath());
//        }
//    }
//
//    private void salvarAproveitamento() {
//        String empresa = view.getNomeEmpresa();
//        String caminhoDoc = view.getCaminhoDocumento();
//        String relatorio = view.getTxtRelatorioTecnico();
//
//        try {
//            AproveitamentoProfissional apro = new AproveitamentoProfissional();
//
//            // 2. Seta os dados textuais da tela
//            apro.setNomeEmpresa(empresa);
//            apro.setDocumentoComprobatorio(caminhoDoc);
//            apro.setRelatorioTecnico(relatorio);
//            apro.setCargoExercido(view.getTxtCargoExercido());
//            // COMPELTAR!!!
//
//            Discente discente = (Discente) view.getCbDiscente().getSelectedItem();
//            CoordenadorEstagio coordenador = (CoordenadorEstagio) view.getCbCoordenador().getSelectedItem();
//
//            apro.setDiscente(discente);
//            apro.setCoordenadorEstagio(coordenador);
//
//            //aproveitamentoDAO.salvar(apro);
//
//            view.exibirMensagem("Solicitação de aproveitamento salva com sucesso no banco de dados!");
//            view.dispose();
//
//        } catch (Exception ex) {
//            view.exibirMensagem("Erro ao salvar no banco: " + ex.getMessage());
//        }
//    }
//}