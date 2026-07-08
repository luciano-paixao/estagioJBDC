package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroSupervisor extends JFrame {

    // Componente modularizado de Pessoa
    private PainelPessoa painelPessoa;

    // Campos específicos do Supervisor
    private JComboBox<String> cbConcedente; // Futuramente carregará objetos Concedente
    private JTextField txtRegistroProfissional;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroSupervisor() {
        setTitle("Cadastro de Supervisor (Concedente)");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Inicializa o bloco de dados pessoais
        painelPessoa = new PainelPessoa();

        // Cria o bloco de dados específicos do Supervisor
        JPanel painelSupervisor = new JPanel(new GridLayout(2, 2, 10, 10));
        painelSupervisor.setBorder(BorderFactory.createTitledBorder("Dados Profissionais"));

        cbConcedente = new JComboBox<>();
        txtRegistroProfissional = new JTextField();

        painelSupervisor.add(new JLabel("Empresa Concedente:"));
        painelSupervisor.add(cbConcedente);

        painelSupervisor.add(new JLabel("Registro Profissional:"));
        painelSupervisor.add(txtRegistroProfissional);

        // Agrupando os painéis
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        container.add(painelPessoa);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(painelSupervisor);

        // Botões de ação
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Supervisor");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(container, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // ==========================================
    // GETTERS PARA O CONTROLLER
    // ==========================================

    public PainelPessoa getPainelPessoa() {
        return painelPessoa;
    }

    public String getConcedenteSelecionada() {
        return (String) cbConcedente.getSelectedItem();
    }

    public void adicionarConcedente(String concedente) {
        cbConcedente.addItem(concedente);
    }

    public String getRegistroProfissional() {
        return txtRegistroProfissional.getText();
    }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}