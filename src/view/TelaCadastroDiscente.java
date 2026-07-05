package view;

import models.discente.SituacaoDiscente;
import models.periodoLetivo.PeriodoLetivo;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroDiscente extends JFrame {

    // Campos simplificados para representar os dados de Pessoa
    private JTextField txtNomePessoa;
    private JTextField txtCpfPessoa;

    // Campos específicos de Discente
    private JTextField txtMatricula;
    private JTextField txtCurso;
    private JComboBox<SituacaoDiscente> cbSituacao;
    private JComboBox<PeriodoLetivo> cbPeriodoLetivo;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroDiscente() {
        setTitle("Cadastro de Discente");
        setSize(450, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painelFormulario = new JPanel(new GridLayout(6, 2, 10, 10));
        painelFormulario.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtNomePessoa = new JTextField();
        txtCpfPessoa = new JTextField();
        txtMatricula = new JTextField();
        txtCurso = new JTextField();

        // Inicializa carregando automaticamente valores do Enum SituacaoDiscente
        cbSituacao = new JComboBox<>(SituacaoDiscente.values());

        // Inicializa vazio para ser preenchido via Controller futuramente
        cbPeriodoLetivo = new JComboBox<>();

        painelFormulario.add(new JLabel("Nome (Pessoa):"));
        painelFormulario.add(txtNomePessoa);

        painelFormulario.add(new JLabel("CPF (Pessoa):"));
        painelFormulario.add(txtCpfPessoa);

        painelFormulario.add(new JLabel("Matrícula:"));
        painelFormulario.add(txtMatricula);

        painelFormulario.add(new JLabel("Curso:"));
        painelFormulario.add(txtCurso);

        painelFormulario.add(new JLabel("Situação:"));
        painelFormulario.add(cbSituacao);

        painelFormulario.add(new JLabel("Período Letivo:"));
        painelFormulario.add(cbPeriodoLetivo);

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
    public String getNomePessoa() { return txtNomePessoa.getText(); }
    public String getCpfPessoa() { return txtCpfPessoa.getText(); }
    public String getMatricula() { return txtMatricula.getText(); }
    public String getCurso() { return txtCurso.getText(); }
    public SituacaoDiscente getSituacaoSelecionada() { return (SituacaoDiscente) cbSituacao.getSelectedItem(); }
    public PeriodoLetivo getPeriodoLetivoSelecionado() { return (PeriodoLetivo) cbPeriodoLetivo.getSelectedItem(); }

    // Metodo para o Controller popular o ComboBox de Período Letivo
    public void adicionarPeriodoLetivo(PeriodoLetivo periodo) {
        cbPeriodoLetivo.addItem(periodo);
    }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public void limparCampos() {
        txtNomePessoa.setText("");
        txtCpfPessoa.setText("");
        txtMatricula.setText("");
        txtCurso.setText("");
        cbSituacao.setSelectedIndex(0);
    }
}