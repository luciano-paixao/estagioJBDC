package view;

import models.termoCompromisso.HorasSemanais; //[cite: 2]

import javax.swing.*;
import java.awt.*;

public class TelaCadastroTermoCompromisso extends JFrame {

    private JComboBox<String> cbEstagio; // Listar todos os estágios para selecionar

    // Campos Auto-preenchidos (Somente Leitura)
    private JTextField txtNomeAluno;
    private JTextField txtCursoAluno;
    private JTextField txtNomeConcedente;
    private JTextField txtRepresentanteConcedente;

    // Campos de preenchimento manual do Termo de Compromisso
    private JComboBox<HorasSemanais> cbHorasSemanais; //[cite: 2]
    private JTextField txtRemuneracao;
    private JTextField txtAuxTransporte;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroTermoCompromisso() {
        setTitle("Preenchimento: Termo de Compromisso");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- BUSCA/VÍNCULO ---
        JPanel painelBusca = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBusca.setBorder(BorderFactory.createTitledBorder("Vincular ao Estágio"));
        cbEstagio = new JComboBox<>();
        painelBusca.add(new JLabel("Selecione o Estágio:"));
        painelBusca.add(cbEstagio);

        // --- DADOS IMPORTADOS (Somente Leitura) ---
        JPanel painelImportados = new JPanel(new GridLayout(4, 2, 10, 10));
        painelImportados.setBorder(BorderFactory.createTitledBorder("Dados Vinculados (Automático)"));

        txtNomeAluno = criarCampoSomenteLeitura();
        txtCursoAluno = criarCampoSomenteLeitura();
        txtNomeConcedente = criarCampoSomenteLeitura();
        txtRepresentanteConcedente = criarCampoSomenteLeitura();

        painelImportados.add(new JLabel("Nome do Aluno:"));
        painelImportados.add(txtNomeAluno);
        painelImportados.add(new JLabel("Curso:"));
        painelImportados.add(txtCursoAluno);
        painelImportados.add(new JLabel("Empresa Concedente:"));
        painelImportados.add(txtNomeConcedente);
        painelImportados.add(new JLabel("Representante:"));
        painelImportados.add(txtRepresentanteConcedente);

        // --- DADOS DE PREENCHIMENTO MANUAL ---
        JPanel painelManual = new JPanel(new GridLayout(4, 2, 10, 10));
        painelManual.setBorder(BorderFactory.createTitledBorder("Dados do Termo"));

        cbHorasSemanais = new JComboBox<>(HorasSemanais.values());
        txtRemuneracao = new JTextField();
        txtAuxTransporte = new JTextField();

        painelManual.add(new JLabel("Horas Semanais:"));
        painelManual.add(cbHorasSemanais);
        painelManual.add(new JLabel("Remuneração (R$):"));
        painelManual.add(txtRemuneracao);
        painelManual.add(new JLabel("Auxílio Transporte (R$):"));
        painelManual.add(txtAuxTransporte);

        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.add(painelImportados);
        painelCentral.add(Box.createRigidArea(new Dimension(0, 10)));
        painelCentral.add(painelManual);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Termo");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        painelPrincipal.add(painelBusca, BorderLayout.NORTH);
        painelPrincipal.add(painelCentral, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        add(painelPrincipal);
    }

    // Metodo utilitário para criar os campos travados
    private JTextField criarCampoSomenteLeitura() {
        JTextField campo = new JTextField();
        campo.setEditable(false);
        campo.setBackground(new Color(240, 240, 240)); // Fundo cinza claro, indicando inatividade
        campo.setForeground(Color.DARK_GRAY);
        return campo;
    }

    // ==========================================
    // GETTERS & SETTERS PARA O CONTROLLER
    // ==========================================

    public JComboBox<String> getCbEstagio() { return cbEstagio; }

    public void setNomeAluno(String nome) { txtNomeAluno.setText(nome); }
    public void setCursoAluno(String curso) { txtCursoAluno.setText(curso); }
    public void setNomeConcedente(String nome) { txtNomeConcedente.setText(nome); }
    public void setRepresentanteConcedente(String nome) { txtRepresentanteConcedente.setText(nome); }

    public HorasSemanais getHorasSemanais() { return (HorasSemanais) cbHorasSemanais.getSelectedItem(); }
    public String getRemuneracao() { return txtRemuneracao.getText(); }
    public String getAuxTransporte() { return txtAuxTransporte.getText(); }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}