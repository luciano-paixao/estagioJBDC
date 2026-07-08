package view;

import models.concedente.TipoConcedente;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TelaCadastroConcedente extends JFrame {

    // --- Dinâmica de Telefones ---
    private JPanel painelListaTelefones;
    private List<PainelTelefone> listaPaineisTelefone;
    private JButton btnAdicionarTelefone;

    // --- Campos de Concedente ---
    private JTextField txtNomeEmpresa;
    private JFormattedTextField txtCnpj;
    private JComboBox<TipoConcedente> cbTipoConcedente;
    private JTextField txtAreaAtuacao;

    // --- Paineis Reutilizáveis ---
    private PainelEndereco painelEndereco;
    private PainelPessoa painelRepresentante;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroConcedente() {
        setTitle("Cadastro de Concedente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JTabbedPane painelAbas = new JTabbedPane();

        // Inicializando e adicionando abas modulares

        painelAbas.addTab("Dados da Empresa", criarPainelEmpresa());
        painelEndereco = new PainelEndereco();
        painelAbas.addTab("Endereço", painelEndereco);
        painelAbas.addTab("Representante", criarPainelRepresentante());
        painelAbas.addTab("Telefones", criarPainelTelefonesDinâmicos());

        // Painel de Botões Inferior
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Cadastro Completo");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(painelAbas, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    // ==================================
    // MÉTODOS CONSTRUTORES DAS ABAS
    // ==================================

    private JPanel criarPainelEmpresa() {
        JPanel painel = new JPanel(new GridLayout(4, 2, 10, 10));
        painel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        txtNomeEmpresa = new JTextField();
        txtAreaAtuacao = new JTextField();
        cbTipoConcedente = new JComboBox<>(TipoConcedente.values());

        try {
            MaskFormatter mascaraCnpj = new MaskFormatter("##.###.###/####-##");
            mascaraCnpj.setPlaceholderCharacter('_');
            txtCnpj = new JFormattedTextField(mascaraCnpj);
        } catch (ParseException e) {
            txtCnpj = new JFormattedTextField();
        }

        painel.add(new JLabel("Razão Social / Nome:")); painel.add(txtNomeEmpresa);
        painel.add(new JLabel("CNPJ:")); painel.add(txtCnpj);
        painel.add(new JLabel("Tipo:")); painel.add(cbTipoConcedente);
        painel.add(new JLabel("Área de Atuação:")); painel.add(txtAreaAtuacao);

        JPanel container = new JPanel(new BorderLayout());
        container.add(painel, BorderLayout.NORTH);
        return container;
    }

    private JPanel criarPainelTelefonesDinâmicos() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Container para empilhar telefones verticalmente
        painelListaTelefones = new JPanel();
        painelListaTelefones.setLayout(new BoxLayout(painelListaTelefones, BoxLayout.Y_AXIS));
        listaPaineisTelefone = new ArrayList<>();

        JScrollPane scrollTelefones = new JScrollPane(painelListaTelefones);
        scrollTelefones.setBorder(null);
        scrollTelefones.getVerticalScrollBar().setUnitIncrement(16);

        // Botão para adicionar novos telefones
        btnAdicionarTelefone = new JButton("+ Adicionar Telefone");
        JPanel painelBotaoTopo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelBotaoTopo.add(btnAdicionarTelefone);

        adicionarNovoPainelTelefone();

        // Comportamento visual: ao clicar, insere um novo painel e atualiza a tela
        btnAdicionarTelefone.addActionListener(e -> adicionarNovoPainelTelefone());

        painelPrincipal.add(painelBotaoTopo, BorderLayout.NORTH);
        painelPrincipal.add(scrollTelefones, BorderLayout.CENTER);

        return painelPrincipal;
    }

    private void adicionarNovoPainelTelefone() {
        PainelTelefone novoTelefone = new PainelTelefone();

        novoTelefone.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        listaPaineisTelefone.add(novoTelefone);
        painelListaTelefones.add(novoTelefone);

        painelListaTelefones.revalidate();
        painelListaTelefones.repaint();
    }

    private JPanel criarPainelRepresentante() {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        painelRepresentante = new PainelPessoa();
        painelRepresentante.setBorder(BorderFactory.createTitledBorder("Representante da Empresa"));
        painel.add(painelRepresentante, BorderLayout.NORTH);
        return painel;
    }

    // ==========================================
    // GETTERS PARA O CONTROLLER
    // ==========================================

    public String getNomeEmpresa() { return txtNomeEmpresa.getText(); }
    public String getCnpj() { return txtCnpj.getText(); }
    public TipoConcedente getTipoConcedente() { return (TipoConcedente) cbTipoConcedente.getSelectedItem(); }
    public String getAreaAtuacao() { return txtAreaAtuacao.getText(); }

    // Retorna a lista completa de painéis de telefone inseridos
    public List<PainelTelefone> getListaPaineisTelefone() { return listaPaineisTelefone; }
    public PainelEndereco getPainelEndereco() { return painelEndereco; }
    public PainelPessoa getPainelRepresentante() { return painelRepresentante; }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }

    public void limparCampos() {
        painelEndereco.limparCampos();
        painelRepresentante.limparCampos();
        txtAreaAtuacao.setText("");
        txtCnpj.setText("");
        txtNomeEmpresa.setText("");
    }
}