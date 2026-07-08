package view;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaCadastroCoordenador extends JFrame {

    private PainelPessoa painelPessoa;
    private PainelDocente painelDocente;

    // Campos específicos do Coordenador
    private JTextField txtNumeroPortaria;
    private JFormattedTextField txtInicioVigencia;
    private JFormattedTextField txtFimVigencia;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroCoordenador() {
        setTitle("Cadastro de Coordenador de Estágio");
        setSize(550, 650); // Maior para caber todos os blocos
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        painelPessoa = new PainelPessoa();
        painelDocente = new PainelDocente();

        JPanel painelCoordenador = new JPanel(new GridLayout(3, 2, 10, 10));
        painelCoordenador.setBorder(BorderFactory.createTitledBorder("Dados de Coordenação"));

        txtNumeroPortaria = new JTextField();

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtInicioVigencia = new JFormattedTextField(mascaraData);
            txtFimVigencia = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtInicioVigencia = new JFormattedTextField();
            txtFimVigencia = new JFormattedTextField();
        }

        painelCoordenador.add(new JLabel("Nº Portaria:"));
        painelCoordenador.add(txtNumeroPortaria);
        painelCoordenador.add(new JLabel("Início Vigência:"));
        painelCoordenador.add(txtInicioVigencia);
        painelCoordenador.add(new JLabel("Fim Vigência:"));
        painelCoordenador.add(txtFimVigencia);

        // Agrupando todos os painéis (Pessoa, Docente e Coordenador)
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        container.add(painelPessoa);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(painelDocente);
        container.add(Box.createRigidArea(new Dimension(0, 10)));
        container.add(painelCoordenador);

        JScrollPane scroll = new JScrollPane(container);
        scroll.setBorder(null);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Salvar Coordenador");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    public PainelPessoa getPainelPessoa() { return painelPessoa; }
    public PainelDocente getPainelDocente() { return painelDocente; }
    public String getNumeroPortaria() { return txtNumeroPortaria.getText(); }
    public String getInicioVigencia() { return txtInicioVigencia.getText(); }
    public String getFimVigencia() { return txtFimVigencia.getText(); }

    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }

    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }

    public void limparCampos() {
        painelPessoa.limparCampos();
        painelDocente.limparCampos();
        txtNumeroPortaria.setText("");
        txtInicioVigencia.setText("");
        txtFimVigencia.setText("");
    }
}