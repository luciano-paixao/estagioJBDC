package view;

import models.estagio.TipoEstagio;
import models.estagio.AmbitoEstagio;
import models.estagio.StatusEstagio;

// Importações dos Models para os ComboBoxes
import models.discente.Discente;
import models.concedente.Concedente;
import models.supervisorConcedente.SupervisorConcedente;
import models.coordenadorEstagio.CoordenadorEstagio;
import models.periodoLetivo.PeriodoLetivo;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.text.ParseException;

public class TelaCadastroEstagio extends JFrame {

    // --- Componentes da Aba 1 ---
    private JComboBox<TipoEstagio> cbTipo;
    private JComboBox<AmbitoEstagio> cbAmbito;
    private JComboBox<StatusEstagio> cbStatus;
    private JTextField txtCargaHoraria;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;
    private JTextArea txtObservacoes;

    // --- ComboBoxes agora tipados com os Models Reais ---
    private JComboBox<Discente> cbDiscente;
    private JComboBox<Concedente> cbConcedente;
    private JComboBox<SupervisorConcedente> cbSupervisor;
    private JComboBox<CoordenadorEstagio> cbCoordenador;
    private JComboBox<PeriodoLetivo> cbPeriodo;

    // --- Abas de Documentos ---
    private PainelApolice painelApolice;
    private PainelPlanoAtividades painelPlanoAtividades;
    private PainelTermoCompromisso painelTermoCompromisso;

    private JButton btnSalvar;
    private JButton btnCancelar;

    public TelaCadastroEstagio() {
        setTitle("Abertura e Consolidação de Novo Estágio");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JTabbedPane painelAbas = new JTabbedPane();

        painelAbas.addTab("1. Dados Gerais e Envolvidos", criarAbaUnificada());

        painelApolice = new PainelApolice();
        painelAbas.addTab("2. Apólice de Seguro", painelApolice);

        painelPlanoAtividades = new PainelPlanoAtividades();
        painelAbas.addTab("3. Plano de Atividades", painelPlanoAtividades);

        painelTermoCompromisso = new PainelTermoCompromisso();
        painelAbas.addTab("4. Termo de Compromisso", painelTermoCompromisso);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSalvar = new JButton("Consolidar e Salvar Estágio");
        btnCancelar = new JButton("Cancelar");
        painelBotoes.add(btnCancelar);
        painelBotoes.add(btnSalvar);

        setLayout(new BorderLayout());
        add(painelAbas, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);
    }

    private JScrollPane criarAbaUnificada() {
        JPanel containerUnificado = new JPanel();
        containerUnificado.setLayout(new BoxLayout(containerUnificado, BoxLayout.Y_AXIS));
        containerUnificado.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel painelGerais = new JPanel(new GridLayout(7, 2, 10, 10));
        painelGerais.setBorder(BorderFactory.createTitledBorder("Especificações do Estágio"));

        cbTipo = new JComboBox<>(TipoEstagio.values());
        cbAmbito = new JComboBox<>(AmbitoEstagio.values());
        cbStatus = new JComboBox<>(StatusEstagio.values());
        txtCargaHoraria = new JTextField();

        try {
            MaskFormatter mascaraData = new MaskFormatter("##/##/####");
            mascaraData.setPlaceholderCharacter('_');
            txtDataInicio = new JFormattedTextField(mascaraData);
            txtDataFim = new JFormattedTextField(mascaraData);
        } catch (ParseException e) {
            txtDataInicio = new JFormattedTextField();
            txtDataFim = new JFormattedTextField();
        }

        txtObservacoes = new JTextArea(3, 20);
        txtObservacoes.setLineWrap(true);
        txtObservacoes.setWrapStyleWord(true);

        painelGerais.add(new JLabel("Tipo de Estágio:")); painelGerais.add(cbTipo);
        painelGerais.add(new JLabel("Âmbito:")); painelGerais.add(cbAmbito);
        painelGerais.add(new JLabel("Status Inicial:")); painelGerais.add(cbStatus);
        painelGerais.add(new JLabel("Carga Horária Total:")); painelGerais.add(txtCargaHoraria);
        painelGerais.add(new JLabel("Data Início:")); painelGerais.add(txtDataInicio);
        painelGerais.add(new JLabel("Data Fim:")); painelGerais.add(txtDataFim);
        painelGerais.add(new JLabel("Observações:")); painelGerais.add(new JScrollPane(txtObservacoes));

        JPanel painelEnvolvidos = new JPanel(new GridLayout(5, 2, 10, 15));
        painelEnvolvidos.setBorder(BorderFactory.createTitledBorder("Entidades Envolvidas (Vínculos)"));

        // Instanciando os ComboBoxes Tipados
        cbDiscente = new JComboBox<>();
        cbConcedente = new JComboBox<>();
        cbSupervisor = new JComboBox<>();
        cbCoordenador = new JComboBox<>();
        cbPeriodo = new JComboBox<>();

        painelEnvolvidos.add(new JLabel("Discente:")); painelEnvolvidos.add(cbDiscente);
        painelEnvolvidos.add(new JLabel("Concedente (Empresa):")); painelEnvolvidos.add(cbConcedente);
        painelEnvolvidos.add(new JLabel("Supervisor (na Empresa):")); painelEnvolvidos.add(cbSupervisor);
        painelEnvolvidos.add(new JLabel("Coordenador (na IES):")); painelEnvolvidos.add(cbCoordenador);
        painelEnvolvidos.add(new JLabel("Período Letivo:")); painelEnvolvidos.add(cbPeriodo);

        containerUnificado.add(painelGerais);
        containerUnificado.add(Box.createRigidArea(new Dimension(0, 15)));
        containerUnificado.add(painelEnvolvidos);

        JScrollPane scrollAba = new JScrollPane(containerUnificado);
        scrollAba.getVerticalScrollBar().setUnitIncrement(16);
        scrollAba.setBorder(null);

        return scrollAba;
    }

    // Getters atualizados
    public JComboBox<Discente> getCbDiscente() { return cbDiscente; }
    public JComboBox<Concedente> getCbConcedente() { return cbConcedente; }
    public JComboBox<SupervisorConcedente> getCbSupervisor() { return cbSupervisor; }
    public JComboBox<CoordenadorEstagio> getCbCoordenador() { return cbCoordenador; }
    public JComboBox<PeriodoLetivo> getCbPeriodo() { return cbPeriodo; }

    // (Omitindo os demais getters e botões que permanecem iguais para poupar espaço)
    public String getCargaHoraria() { return txtCargaHoraria.getText(); }
    public PainelApolice getPainelApolice() { return painelApolice; }
    public PainelPlanoAtividades getPainelPlanoAtividades() { return painelPlanoAtividades; }
    public PainelTermoCompromisso getPainelTermoCompromisso() { return painelTermoCompromisso; }
    public JButton getBtnSalvar() { return btnSalvar; }
    public JButton getBtnCancelar() { return btnCancelar; }
    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }
}