package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PainelPrincipal extends JFrame {

    // --- Menus de Cadastros Base ---
    private JMenuItem menuDiscente;
    private JMenuItem menuDocente;
    private JMenuItem menuCoordenador;
    private JMenuItem menuConcedente;
    private JMenuItem menuSupervisor;
    private JMenuItem menuPeriodo;

    // --- Menus de Processos e Documentos ---
    private JMenuItem menuEstagio;
    private JMenuItem menuTermoCompromisso;
    private JMenuItem menuPlanoAtividades;
    private JMenuItem menuAproveitamento;

    private JMenuItem menuSair;

    // --- Componentes do Dashboard ---
    private JComboBox<String> cbConsultas;
    private JButton btnExecutarConsulta;
    private JTable tabelaResultados;
    private DefaultTableModel modeloTabela;

    public PainelPrincipal() {
        setTitle("Sistema de Gestão de Estágios - FACSI");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarMenu();
        inicializarDashboard();
    }

    private void inicializarMenu() {
        JMenuBar barraMenu = new JMenuBar();

        // 1. Cadastros de Pessoas e Instituições
        JMenu menuCadastros = new JMenu("Cadastros Base");
        menuDiscente = new JMenuItem("Discentes");
        menuDocente = new JMenuItem("Docentes");
        menuCoordenador = new JMenuItem("Coordenadores de Estágio");
        menuCadastros.addSeparator();
        menuConcedente = new JMenuItem("Concedentes (Empresas)");
        menuSupervisor = new JMenuItem("Supervisores");

        menuCadastros.add(menuDiscente);
        menuCadastros.add(menuDocente);
        menuCadastros.add(menuCoordenador);
        menuCadastros.add(menuConcedente);
        menuCadastros.add(menuSupervisor);

        // 2. Fluxo de Estágio e Documentação
        JMenu menuProcessos = new JMenu("Gestão de Estágios");
        menuEstagio = new JMenuItem("Abertura de Estágio");
        menuTermoCompromisso = new JMenuItem("Termos de Compromisso");
        menuPlanoAtividades = new JMenuItem("Planos de Atividades");
        menuProcessos.addSeparator();
        menuAproveitamento = new JMenuItem("Aproveitamento Profissional");

        menuProcessos.add(menuEstagio);
        menuProcessos.add(menuTermoCompromisso);
        menuProcessos.add(menuPlanoAtividades);
        menuProcessos.add(menuAproveitamento);

        // 4. Sistema
        JMenu menuSistema = new JMenu("Sistema");
        menuSair = new JMenuItem("Sair");
        menuSistema.add(menuSair);

        barraMenu.add(menuCadastros);
        barraMenu.add(menuProcessos);
        barraMenu.add(menuSistema);

        setJMenuBar(barraMenu);
    }

    private void inicializarDashboard() {
        JPanel painelPrincipal = new JPanel(new BorderLayout(10, 10));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel painelFiltro = new JPanel(new FlowLayout(FlowLayout.LEFT));
        painelFiltro.setBorder(BorderFactory.createTitledBorder("Consultas Rápidas no Banco de Dados"));

        // Array atualizado com as 4 consultas originais + as 19 novas consultas
        String[] consultas = {
                "Selecione uma consulta...",
                "1. Listar todos os Discentes",
                "2. Listar todas as Concedentes",
                "3. Listar todos os Estágios",
                "4. Listar Coordenadores de Estágio",
                "5. Carga Horária por Tipo de Estágio",
                "6. Soma de Auxílio Transporte por Período",
                "7. Menor Remuneração por Termo",
                "8. Maior Carga Horária Contratada",
                "9. Soma da Carga Horária por Empresa",
                "10. Períodos com mais de Dois Estágios",
                "11. Cursos com Remuneração Média Acima de R$ 600",
                "12. Empresas com mais de 500 Horas",
                "13. Supervisores com mais de um Estagiário",
                "14. Discentes com Remuneração Acima da Média",
                "15. Estágios em Cursos de TI",
                "16. Cursos com mais de Dois Discentes por Período",
                "17. Discentes sem Estágio",
                "18. Estágios com Plano Aprovado",
                "19. Estágios Ativos com mais de um Discente por Período",
                "20. Carga Horária por Curso",
                "21. Remuneração e Auxílio por Discente",
                "22. Quantidade de Estágios por Empresa",
                "23. Discentes com Estágio Ativo"
        };

        cbConsultas = new JComboBox<>(consultas);
        cbConsultas.setPreferredSize(new Dimension(450, 25)); // Aumentado para caber os textos maiores

        btnExecutarConsulta = new JButton("Executar Consulta");

        painelFiltro.add(new JLabel("Relatório:"));
        painelFiltro.add(cbConsultas);
        painelFiltro.add(btnExecutarConsulta);

        modeloTabela = new DefaultTableModel();
        tabelaResultados = new JTable(modeloTabela);
        tabelaResultados.setRowHeight(25);
        tabelaResultados.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 12));

        JScrollPane scrollTabela = new JScrollPane(tabelaResultados);
        scrollTabela.setBorder(BorderFactory.createTitledBorder("Resultados"));

        painelPrincipal.add(painelFiltro, BorderLayout.NORTH);
        painelPrincipal.add(scrollTabela, BorderLayout.CENTER);

        add(painelPrincipal);
    }

    // Getters dos Menus
    public JMenuItem getMenuDiscente() { return menuDiscente; }
    public JMenuItem getMenuDocente() { return menuDocente; }
    public JMenuItem getMenuCoordenador() { return menuCoordenador; }
    public JMenuItem getMenuConcedente() { return menuConcedente; }
    public JMenuItem getMenuSupervisor() { return menuSupervisor; }
    public JMenuItem getMenuPeriodo() { return menuPeriodo; }
    public JMenuItem getMenuEstagio() { return menuEstagio; }
    public JMenuItem getMenuTermoCompromisso() { return menuTermoCompromisso; }
    public JMenuItem getMenuPlanoAtividades() { return menuPlanoAtividades; }
    public JMenuItem getMenuAproveitamento() { return menuAproveitamento; }
    public JMenuItem getMenuSair() { return menuSair; }

    // Getters do Dashboard
    public JComboBox<String> getCbConsultas() { return cbConsultas; }
    public JButton getBtnExecutarConsulta() { return btnExecutarConsulta; }

    public void atualizarTabela(String[] colunas, Object[][] dados) {
        modeloTabela.setDataVector(dados, colunas);
    }

    public void exibirMensagem(String msg) { JOptionPane.showMessageDialog(this, msg); }
}