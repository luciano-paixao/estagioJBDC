package view;

import models.periodoLetivo.Periodo;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaCadastroPeriodo extends JFrame {

    private JTextField txtAno;
    private JComboBox<Periodo> cbPeriodo;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroPeriodo() {
        setTitle("Cadastro de Período Letivo");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painelFormulario = new JPanel(new GridLayout(4, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtAno = new JTextField();
        cbPeriodo = new JComboBox<>(Periodo.values());

        // Aplicando máscaras para as Datas
        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');

            txtDataInicio = new JFormattedTextField(mascaraData);
            txtDataFim = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtDataInicio = new JFormattedTextField();
            txtDataFim = new JFormattedTextField();
        }

        painelFormulario.add(new JLabel("Ano:"));
        painelFormulario.add(txtAno);

        painelFormulario.add(new JLabel("Período:"));
        painelFormulario.add(cbPeriodo);

        painelFormulario.add(new JLabel("Data de Início:"));
        painelFormulario.add(txtDataInicio);

        painelFormulario.add(new JLabel("Data de Fim:"));
        painelFormulario.add(txtDataFim);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");

        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(painelFormulario, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // Getters para o Controller
    public String getAno() { return txtAno.getText(); }
    public Periodo getPeriodoSelecionado() { return (Periodo) cbPeriodo.getSelectedItem(); }
    public String getDataInicio() { return txtDataInicio.getText(); }
    public String getDataFim() { return txtDataFim.getText(); }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}