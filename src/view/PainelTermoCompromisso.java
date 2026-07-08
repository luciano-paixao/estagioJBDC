package view;

import models.termoCompromisso.HorasSemanais; // Assumindo que você tem essa Enum

import javax.swing.*;
import java.awt.*;

public class PainelTermoCompromisso extends JPanel {

    private JTextField txtNumeroTermo;
    private JComboBox<HorasSemanais> cbHorasSemanais;
    private JTextField txtHorasTotais;
    private JTextField txtRemuneracao;
    private JTextField txtAuxTransporte;

    // Representa os TINYINT de assinatura do diagrama como Checkboxes
    private JCheckBox chkAssinaturaDiscente;
    private JCheckBox chkAssinaturaCoordenador;
    private JCheckBox chkAssinaturaConcedente;

    public PainelTermoCompromisso() {
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setLayout(new BorderLayout(10, 10));
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // --- SEÇÃO DE DADOS FINANCEIROS E HORAS ---
        JPanel painelDados = new JPanel(new GridLayout(5, 2, 10, 15));
        painelDados.setBorder(BorderFactory.createTitledBorder("Cláusulas do Termo"));

        txtNumeroTermo = new JTextField();
        cbHorasSemanais = new JComboBox<>(HorasSemanais.values());
        txtHorasTotais = new JTextField();
        txtRemuneracao = new JTextField();
        txtAuxTransporte = new JTextField();

        painelDados.add(new JLabel("Número do Termo:"));
        painelDados.add(txtNumeroTermo);

        painelDados.add(new JLabel("Horas Semanais:"));
        painelDados.add(cbHorasSemanais);

        painelDados.add(new JLabel("Horas Totais Previstas:"));
        painelDados.add(txtHorasTotais);

        painelDados.add(new JLabel("Remuneração / Bolsa (R$):"));
        painelDados.add(txtRemuneracao);

        painelDados.add(new JLabel("Auxílio Transporte (R$):"));
        painelDados.add(txtAuxTransporte);

        // --- SEÇÃO DE ASSINATURAS (TINYINT) ---
        JPanel painelAssinaturas = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        painelAssinaturas.setBorder(BorderFactory.createTitledBorder("Status das Assinaturas"));

        chkAssinaturaDiscente = new JCheckBox("Discente");
        chkAssinaturaConcedente = new JCheckBox("Concedente");
        chkAssinaturaCoordenador = new JCheckBox("Coordenador");

        painelAssinaturas.add(chkAssinaturaDiscente);
        painelAssinaturas.add(chkAssinaturaConcedente);
        painelAssinaturas.add(chkAssinaturaCoordenador);

        // Montagem do painel principal
        add(painelDados, BorderLayout.CENTER);
        add(painelAssinaturas, BorderLayout.SOUTH);
    }

    // Getters
    public String getNumeroTermo() { return txtNumeroTermo.getText(); }
    public HorasSemanais getHorasSemanais() { return (HorasSemanais) cbHorasSemanais.getSelectedItem(); }
    public String getHorasTotais() { return txtHorasTotais.getText(); }
    public String getRemuneracao() { return txtRemuneracao.getText(); }
    public String getAuxTransporte() { return txtAuxTransporte.getText(); }

    public boolean isAssinadoDiscente() { return chkAssinaturaDiscente.isSelected(); }
    public boolean isAssinadoConcedente() { return chkAssinaturaConcedente.isSelected(); }
    public boolean isAssinadoCoordenador() { return chkAssinaturaCoordenador.isSelected(); }

    public void limparCampos() {
        txtNumeroTermo.setText("");
        cbHorasSemanais.setSelectedIndex(0);
        txtHorasTotais.setText("");
        txtRemuneracao.setText("");
        txtAuxTransporte.setText("");
        chkAssinaturaDiscente.setSelected(false);
        chkAssinaturaConcedente.setSelected(false);
        chkAssinaturaCoordenador.setSelected(false);
    }
}