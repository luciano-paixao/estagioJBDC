package view;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroDocente extends JFrame {

    private PainelPessoa painelPessoa;
    private PainelDocente painelDocente;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroDocente() {
        setTitle("Cadastro de Docente");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        painelPessoa = new PainelPessoa();
        painelDocente = new PainelDocente();

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Docente");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        container.add(painelPessoa);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(painelDocente);

        setLayout(new BorderLayout());
        add(container, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public PainelPessoa getPainelPessoa() { return painelPessoa; }
    public PainelDocente getPainelDocente() { return painelDocente; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }
}