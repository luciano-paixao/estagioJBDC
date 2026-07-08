package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class PainelEndereco extends JPanel {

    private JFormattedTextField txtCep;
    private JTextField txtLogradouro;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JTextField txtEstado;

    public PainelEndereco() {
        setLayout(new GridLayout(7, 2, 10, 10));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        try {
            MaskFormatter mascaraCep = new MaskFormatter("#####-###");
            mascaraCep.setPlaceholderCharacter('_');
            txtCep = new JFormattedTextField(mascaraCep);
        } catch (ParseException e) {
            txtCep = new JFormattedTextField();
        }

        txtLogradouro = new JTextField();
        txtNumero = new JTextField();
        txtComplemento = new JTextField();
        txtBairro = new JTextField();
        txtCidade = new JTextField();
        txtEstado = new JTextField();

        add(new JLabel("CEP:")); add(txtCep);
        add(new JLabel("Logradouro:")); add(txtLogradouro);
        add(new JLabel("Número:")); add(txtNumero);
        add(new JLabel("Complemento:")); add(txtComplemento);
        add(new JLabel("Bairro:")); add(txtBairro);
        add(new JLabel("Cidade:")); add(txtCidade);
        add(new JLabel("Estado:")); add(txtEstado);
    }

    public String getCep() { return txtCep.getText(); }
    public String getLogradouro() { return txtLogradouro.getText(); }
    public String getNumero() { return txtNumero.getText(); }
    public String getComplemento() { return txtComplemento.getText(); }
    public String getBairro() { return txtBairro.getText(); }
    public String getCidade() { return txtCidade.getText(); }
    public String getEstado() { return txtEstado.getText(); }

    public void limparCampos() {
        txtCep.setValue(null);
        txtLogradouro.setText("");
        txtNumero.setText("");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        txtEstado.setText("");
    }
}