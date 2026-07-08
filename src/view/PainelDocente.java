package view;

import models.docente.Titulacao;

import javax.swing.*;
import java.awt.*;

public class PainelDocente extends JPanel {

    private JTextField txtSiape;
    private JTextField txtDepartamento;
    private JComboBox<Titulacao> cbTitulacao;

    public PainelDocente() {
        setBorder(BorderFactory.createTitledBorder("Dados Institucionais"));
        setLayout(new GridLayout(3, 2, 10, 10));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        txtSiape = new JTextField();
        txtDepartamento = new JTextField();

        cbTitulacao = new JComboBox<>(Titulacao.values());

        add(new JLabel("Matrícula SIAPE:"));
        add(txtSiape);

        add(new JLabel("Departamento:"));
        add(txtDepartamento);

        add(new JLabel("Titulação:"));
        add(cbTitulacao);
    }

    public String getSiape() { return txtSiape.getText(); }
    public String getDepartamento() { return txtDepartamento.getText(); }
    public Titulacao getTitulacaoSelecionada() { return (Titulacao) cbTitulacao.getSelectedItem(); }

    public void limparCampos() {
        txtSiape.setText("");
        txtDepartamento.setText("");
        cbTitulacao.setSelectedIndex(0);
    }
}