package view;

import javax.swing.*;
import java.awt.*;

public class PainelPlanoAtividades extends JPanel {

    private JTextField txtAreaAtuacao;

    private JTextArea txtJustificativa;
    private JTextArea txtObjetivos;
    private JTextArea txtPlanoAtividadesTexto;
    private JTextArea txtAdequacaoPedagogica;

    public PainelPlanoAtividades() {
        setLayout(new BorderLayout());
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        // Painel interno que empilha as seções verticalmente
        JPanel painelConteudo = new JPanel();
        painelConteudo.setLayout(new BoxLayout(painelConteudo, BoxLayout.Y_AXIS));
        painelConteudo.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Área de Atuação (Campo Curto)
        JPanel painelAtuacao = new JPanel(new BorderLayout());
        painelAtuacao.setBorder(BorderFactory.createTitledBorder("Área de Atuação"));
        txtAreaAtuacao = new JTextField();
        painelAtuacao.add(txtAreaAtuacao, BorderLayout.CENTER);
        painelAtuacao.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));

        // Inicializa as Áreas de Texto
        txtJustificativa = new JTextArea();
        txtObjetivos = new JTextArea();
        txtPlanoAtividadesTexto = new JTextArea();
        txtAdequacaoPedagogica = new JTextArea();

        // Adiciona os campos longos ao painel de conteúdo
        painelConteudo.add(painelAtuacao);
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10))); // Espaçador vertical

        painelConteudo.add(criarSecaoDocumento("Justificativa", txtJustificativa, 100));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Objetivos", txtObjetivos, 80));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Plano de Atividades", txtPlanoAtividadesTexto, 120));
        painelConteudo.add(Box.createRigidArea(new Dimension(0, 10)));

        painelConteudo.add(criarSecaoDocumento("Adequação Pedagógica", txtAdequacaoPedagogica, 100));

        // Coloca o painel de conteúdo dentro de um Scroll para a aba
        JScrollPane scrollPrincipal = new JScrollPane(painelConteudo);
        scrollPrincipal.getVerticalScrollBar().setUnitIncrement(16);
        scrollPrincipal.setBorder(null);

        add(scrollPrincipal, BorderLayout.CENTER);
    }

    private JPanel criarSecaoDocumento(String titulo, JTextArea areaTexto, int alturaPreferida) {
        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createTitledBorder(titulo));

        areaTexto.setLineWrap(true);       // Quebra a linha na borda
        areaTexto.setWrapStyleWord(true);  // Não corta palavras ao meio
        areaTexto.setFont(new Font("SansSerif", Font.PLAIN, 12));

        JScrollPane scrollArea = new JScrollPane(areaTexto);
        scrollArea.setPreferredSize(new Dimension(600, alturaPreferida));

        painel.add(scrollArea, BorderLayout.CENTER);
        return painel;
    }

    // ==========================================
    // GETTERS PARA O CONTROLLER EXTRAIR OS DADOS
    // ==========================================

    public String getAreaAtuacao() { return txtAreaAtuacao.getText(); }
    public String getJustificativa() { return txtJustificativa.getText(); }
    public String getObjetivos() { return txtObjetivos.getText(); }
    public String getPlanoAtividadesTexto() { return txtPlanoAtividadesTexto.getText(); }
    public String getAdequacaoPedagogica() { return txtAdequacaoPedagogica.getText(); }

    public void limparCampos() {
        txtAreaAtuacao.setText("");
        txtJustificativa.setText("");
        txtObjetivos.setText("");
        txtPlanoAtividadesTexto.setText("");
        txtAdequacaoPedagogica.setText("");
    }
}