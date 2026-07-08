package view;

import models.discente.SituacaoDiscente;
import models.periodoLetivo.PeriodoLetivo;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroDiscente extends JFrame {

    private PainelPessoa painelPessoa;

    // Campos específicos de Discente
    private JTextField txtMatricula;
    private JTextField txtCurso;
    private JComboBox<SituacaoDiscente> cbSituacao;
    private JComboBox<PeriodoLetivo> cbPeriodoLetivo;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroDiscente() {
        setTitle("Cadastro de Discente");
        setSize(500, 525);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        painelPessoa = new PainelPessoa();

        JPanel painelDiscente = new JPanel(new GridLayout(4, 2, 10, 10));

        txtMatricula = new JTextField();
        txtCurso = new JTextField();
        cbSituacao = new JComboBox<>(SituacaoDiscente.values());
        cbPeriodoLetivo = new JComboBox<>();

        painelDiscente.add(new JLabel("Matrícula:"));
        painelDiscente.add(txtMatricula);

        painelDiscente.add(new JLabel("Curso:"));
        painelDiscente.add(txtCurso);

        painelDiscente.add(new JLabel("Situação:"));
        painelDiscente.add(cbSituacao);

        painelDiscente.add(new JLabel("Período Letivo:"));
        painelDiscente.add(cbPeriodoLetivo);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout(15, 15));

        // Contêiner que agrupa os painéis de Pessoa e Discente
        JPanel containerCentral = new JPanel(new BorderLayout());
        containerCentral.add(painelPessoa, BorderLayout.NORTH);
        containerCentral.add(painelDiscente, BorderLayout.CENTER);

        containerCentral.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));

        add(containerCentral, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // ==========================================
    // GETTERS PARA O CONTROLLER
    // ==========================================

    public PainelPessoa getPainelPessoa() { return painelPessoa; }
    public String getMatricula() { return txtMatricula.getText(); }
    public String getCurso() { return txtCurso.getText(); }
    public SituacaoDiscente getSituacaoSelecionada() { return (SituacaoDiscente) cbSituacao.getSelectedItem(); }
    public PeriodoLetivo getPeriodoLetivoSelecionado() { return (PeriodoLetivo) cbPeriodoLetivo.getSelectedItem(); }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void adicionarPeriodoLetivo(PeriodoLetivo periodo) { cbPeriodoLetivo.addItem(periodo); }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public void limparCampos() {
        painelPessoa.limparCampos();
        txtMatricula.setText("");
        txtCurso.setText("");
        cbSituacao.setSelectedIndex(0);
    }
}