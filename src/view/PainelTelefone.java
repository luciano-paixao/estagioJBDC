package view;

import models.telefone.TipoTelefone;

import javax.swing.*;
import java.awt.*;

public class PainelTelefone extends JPanel {

    private JTextField txtDdd;
    private JTextField txtNumero;
    private JComboBox<TipoTelefone> cbTipoTelefone;

    public PainelTelefone() {
        // TitledBorder ajuda a organizar visualmente quando embutido em outras telas
        setBorder(BorderFactory.createTitledBorder("Dados de Contato"));
        setLayout(new GridLayout(3, 2, 10, 10));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtDdd = new JTextField();
        txtNumero = new JTextField();
        cbTipoTelefone = new JComboBox<>(TipoTelefone.values());

        add(new JLabel("DDD:"));
        add(txtDdd);

        add(new JLabel("Número:"));
        add(txtNumero);

        add(new JLabel("Tipo:"));
        add(cbTipoTelefone);
    }

    public String getDdd() { return txtDdd.getText(); }
    public String getNumero() { return txtNumero.getText(); }
    public TipoTelefone getTipoSelecionado() { return (TipoTelefone) cbTipoTelefone.getSelectedItem(); }

    public void limparCampos() {
        txtDdd.setText("");
        txtNumero.setText("");
        cbTipoTelefone.setSelectedIndex(0);
    }
}