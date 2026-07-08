package view;

import models.apoliceSeguro.ResponsavelContratacao;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class PainelApolice extends JPanel {

    private JComboBox<ResponsavelContratacao> cbResponsavel;
    private JTextField txtNumeroApolice;
    private JTextField txtSeguradora;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;

    public PainelApolice() {
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setLayout(new GridLayout(5, 2, 10, 15));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtNumeroApolice = new JTextField();
        txtSeguradora = new JTextField();
        cbResponsavel = new JComboBox<>(ResponsavelContratacao.values());

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtDataInicio = new JFormattedTextField(mascaraData);
            txtDataFim = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtDataInicio = new JFormattedTextField();
            txtDataFim = new JFormattedTextField();
        }

        add(new JLabel("Responsável pela Contratação:"));
        add(cbResponsavel);

        add(new JLabel("Número da Apólice:"));
        add(txtNumeroApolice);

        add(new JLabel("Seguradora:"));
        add(txtSeguradora);

        add(new JLabel("Data de Início:"));
        add(txtDataInicio);

        add(new JLabel("Data de Fim:"));
        add(txtDataFim);
    }

    public String getNumeroApolice() { return txtNumeroApolice.getText(); }
    public String getSeguradora() { return txtSeguradora.getText(); }
    public ResponsavelContratacao getResponsavelSelecionado() { return (ResponsavelContratacao) cbResponsavel.getSelectedItem(); }
    public String getDataInicio() { return txtDataInicio.getText(); }
    public String getDataFim() { return txtDataFim.getText(); }

    public void limparCampos() {
        txtNumeroApolice.setText("");
        txtSeguradora.setText("");
        cbResponsavel.setSelectedIndex(0);
        txtDataInicio.setValue(null);
        txtDataFim.setValue(null);
    }
}