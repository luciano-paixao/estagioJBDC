package view;

import models.pessoa.Sexo;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class PainelPessoa extends JPanel {

    private JTextField txtNome;
    private JFormattedTextField txtCpf;
    private JTextField txtEmail;
    private JFormattedTextField txtDataNascimento;
    private JComboBox<Sexo> cbSexo;
    private JTextField txtCargo;

    public PainelPessoa() {
        setLayout(new GridLayout(6, 2, 10, 10));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtNome = new JTextField();
        txtEmail = new JTextField();
        txtCargo = new JTextField();
        cbSexo = new JComboBox<>(Sexo.values());

        // Aplicando máscaras para CPF e Data de Nascimento
        try {
            MaskFormatter mascaraCpf = new MaskFormatter("###.###.###-##");
            mascaraCpf.setPlaceholderCharacter('_');
            txtCpf = new JFormattedTextField(mascaraCpf);

            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtDataNascimento = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtCpf = new JFormattedTextField();
            txtDataNascimento = new JFormattedTextField();
        }

        add(new JLabel("Nome Completo:"));
        add(txtNome);

        add(new JLabel("CPF:"));
        add(txtCpf);

        add(new JLabel("E-mail:"));
        add(txtEmail);

        add(new JLabel("Data de Nascimento:"));
        add(txtDataNascimento);

        add(new JLabel("Sexo:"));
        add(cbSexo);

        add(new JLabel("Cargo:"));
        add(txtCargo);
    }

    // Getters para o Controller
    public String getNome() { return txtNome.getText(); }
    public String getCpf() { return txtCpf.getText(); }
    public String getEmail() { return txtEmail.getText(); }
    public String getDataNascimento() { return txtDataNascimento.getText(); }
    public Sexo getSexoSelecionado() { return (Sexo) cbSexo.getSelectedItem(); }
    public String getCargo() { return txtCargo.getText(); }

    public void limparCampos() {
        txtNome.setText("");
        txtCpf.setValue(null);
        txtEmail.setText("");
        txtDataNascimento.setValue(null);
        cbSexo.setSelectedIndex(0);
        txtCargo.setText("");
    }
}