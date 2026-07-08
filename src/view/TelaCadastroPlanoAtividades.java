package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroPlanoAtividades extends JFrame {

    // Componente de linha única para campos mais curtos
    private JTextField txtAreaAtuacao;

    // Áreas de texto para os parágrafos longos
    private JTextArea txtJustificativa;
    private JTextArea txtObjetivosGerais;
    private JTextArea txtObjetivosEspecificos;
    private JTextArea txtPlanoAtividadesTexto;
    private JTextArea txtAdequacaoPedagogica;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroPlanoAtividades() {
        setTitle("Preenchimento de Documento: Plano de Atividades");
        setSize(700, 750);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Painel principal contendo todos os campos empilhados verticalmente
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 1. Área de Atuação (Campo Curto)
        JPanel painelAtuacao = new JPanel(new BorderLayout());
        painelAtuacao.setBorder(BorderFactory.createTitledBorder("Área de Atuação"));
        txtAreaAtuacao = new JTextField();
        painelAtuacao.add(txtAreaAtuacao, BorderLayout.CENTER);
        painelAtuacao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60)); // Limita a altura

        txtJustificativa = new JTextArea();
        txtObjetivosGerais = new JTextArea();
        txtObjetivosEspecificos = new JTextArea();
        txtPlanoAtividadesTexto = new JTextArea();
        txtAdequacaoPedagogica = new JTextArea();

        painelConteudo.add(painelAtuacao);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçador vertical

        painelConteudo.add(criarSecaoDocumento("Justificativa", txtJustificativa, 100));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Objetivos Gerais", txtObjetivosGerais, 80));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Objetivos Específicos", txtObjetivosEspecificos, 100));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Plano de Atividades (Cronograma/Metas)", txtPlanoAtividadesTexto, 150));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Adequação Pedagógica", txtAdequacaoPedagogica, 100));

        // Coloca painel de conteúdo em um Scroll, permitindo rolagem da tela
        JScrollPane scrollPrincipal = new JScrollPane(painelConteudo);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.setBorder(null); // Remove borda dupla

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Documento");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        // Montando a janela final
        setLayout(new BorderLayout());
        add(scrollPrincipal, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    /**
     * Método utilitário para formatar áreas de texto grandes como seções de documento.
     * Facilita a manutenção e padroniza o visual.
     */
    private JPanel criarSecaoDocumento(String titulo, JTextArea areaTexto, int altura) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder(titulo));

        // Configurações para simular um documento
        areaTexto.setLineWrap(true);       // Quebra a linha automaticamente
        areaTexto.setWrapStyleWord(true);  // Quebra a linha na palavra inteira, não no meio
        areaTexto.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JScrollPane scrollArea = new JScrollPane(areaTexto);
        scrollArea.setPreferredSize(new Dimension(600, altura));

        painel.add(scrollArea, BorderLayout.CENTER);
        return painel;
    }

    // ==========================================
    // GETTERS PARA O CONTROLLER
    // ==========================================

    public String getAreaAtuacao() { return txtAreaAtuacao.getText(); }
    public String getJustificativa() { return txtJustificativa.getText(); }
    public String getObjetivosGerais() { return txtObjetivosGerais.getText(); }
    public String getObjetivosEspecificos() { return txtObjetivosEspecificos.getText(); }
    public String getPlanoAtividades() { return txtPlanoAtividadesTexto.getText(); }
    public String getAdequacaoPedagogica() { return txtAdequacaoPedagogica.getText(); }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }
}