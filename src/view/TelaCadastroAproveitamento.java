package view;

import models.aproveitamentoProfissional.CondicaoProfissional;
import models.aproveitamentoProfissional.StatusDeferimento;

// Importações dos Models Reais
import models.discente.Discente;
import models.coordenadorEstagio.CoordenadorEstagio;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaCadastroAproveitamento extends JFrame {

    // --- Vínculos Tipados ---
    private JComboBox<Discente> cbDiscente;
    private JComboBox<CoordenadorEstagio> cbCoordenador;

    private JComboBox<CondicaoProfissional> cbCondicao;
    private JComboBox<StatusDeferimento> cbStatus;
    private JTextField txtNomeEmpresa;
    private JTextField txtCargoExercido;
    private JTextField txtCargaHoraria;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;

    private JTextField txtCaminhoDocumento;
    private JButton btnAnexarDocumento;
    private JTextArea txtRelatorioTecnico;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroAproveitamento() {
        setTitle("Solicitação de Aproveitamento Profissional");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel painelVinculos = new JPanel(new GridLayout(2, 2, 10, 10));
        painelVinculos.setBorder(BorderFactory.createTitledBorder("Requerente e Avaliador"));

        // Instanciando tipado
        cbDiscente = new JComboBox<>();
        cbCoordenador = new JComboBox<>();

        painelVinculos.add(new JLabel("Discente Requerente:")); painelVinculos.add(cbDiscente);
        painelVinculos.add(new JLabel("Coordenador Avaliador:")); painelVinculos.add(cbCoordenador);

        JPanel painelProfissional = new JPanel(new GridLayout(6, 2, 10, 10));
        painelProfissional.setBorder(BorderFactory.createTitledBorder("Dados da Atividade Profissional"));

        cbCondicao = new JComboBox<>(CondicaoProfissional.values());
        cbStatus = new JComboBox<>(StatusDeferimento.values());
        txtNomeEmpresa = new JTextField();
        txtCargoExercido = new JTextField();
        txtCargaHoraria = new JTextField();

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtDataInicio = new JFormattedTextField(mascaraData);
            txtDataFim = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtDataInicio = new JFormattedTextField();
            txtDataFim = new JFormattedTextField();
        }

        painelProfissional.add(new JLabel("Condição:")); painelProfissional.add(cbCondicao);
        painelProfissional.add(new JLabel("Nome da Empresa:")); painelProfissional.add(txtNomeEmpresa);
        painelProfissional.add(new JLabel("Cargo Exercido:")); painelProfissional.add(txtCargoExercido);
        painelProfissional.add(new JLabel("Carga Horária:")); painelProfissional.add(txtCargaHoraria);
        painelProfissional.add(new JLabel("Data de Início:")); painelProfissional.add(txtDataInicio);
        painelProfissional.add(new JLabel("Data de Fim:")); painelProfissional.add(txtDataFim);

        JPanel painelDocs = new JPanel(new BorderLayout(10, 10));
        painelDocs.setBorder(BorderFactory.createTitledBorder("Comprovação e Relatório"));

        JPanel painelAnexo = new JPanel(new BorderLayout(5, 0));
        txtCaminhoDocumento = new JTextField();
        txtCaminhoDocumento.setEditable(false);
        txtCaminhoDocumento.setBackground(new Color(245, 245, 245));

        btnAnexarDocumento = new JButton("Procurar PDF...");
        painelAnexo.add(new JLabel("Documento Comprobatório: "), BorderLayout.WEST);
        painelAnexo.add(txtCaminhoDocumento, BorderLayout.CENTER);
        painelAnexo.add(btnAnexarDocumento, BorderLayout.EAST);

        txtRelatorioTecnico = new JTextArea(8, 20);
        txtRelatorioTecnico.setLineWrap(true);
        txtRelatorioTecnico.setWrapStyleWord(true);

        JPanel painelRelatorio = new JPanel(new BorderLayout());
        painelRelatorio.add(new JLabel("Relatório Técnico:"), BorderLayout.NORTH);
        painelRelatorio.add(new JScrollPane(txtRelatorioTecnico), BorderLayout.CENTER);

        painelDocs.add(painelAnexo, BorderLayout.NORTH);
        painelDocs.add(painelRelatorio, BorderLayout.CENTER);

        painelCentral.add(painelVinculos);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelCentral.add(painelProfissional);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelCentral.add(painelDocs);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Registrar Solicitação");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(new JScrollPane(painelCentral), BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // Getters atualizados
    public JComboBox<Discente> getCbDiscente() { return cbDiscente; }
    public JComboBox<CoordenadorEstagio> getCbCoordenador() { return cbCoordenador; }

    // (Omitindo os demais métodos que não mudaram para poupar espaço)
    public String getNomeEmpresa() { return txtNomeEmpresa.getText(); }
    public void setCaminhoDocumento(String caminho) { txtCaminhoDocumento.setText(caminho); }
    public String getCaminhoDocumento() { return txtCaminhoDocumento.getText(); }
    public JButton getBtnAnexarDocumento() { return btnAnexarDocumento; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }
}