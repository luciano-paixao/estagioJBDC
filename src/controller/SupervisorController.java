//package controller;
//
//import DAO.PessoaDAO;
//import DAO.SupervisorDAO;
//import view.TelaCadastroSupervisor;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class SupervisorController {
//
//    private TelaCadastroSupervisor view;
//
//    private SupervisorDAO supervisorDAO;
//    private PessoaDAO pessoaDAO;
//
//    public SupervisorController(TelaCadastroSupervisor view) {
//        this.view = view;
//        this.supervisorDAO = new SupervisorDAO();
//        this.pessoaDAO = new PessoaDAO();
//        inicializarEventos();
//    }
//
//    private void inicializarEventos() {
//        view.getBtnSalvar().addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                salvarSupervisor();
//            }
//        });
//
//        view.getBtnCancelar().addActionListener(e -> view.dispose());
//    }
//
//    private void carregarConcedentesIniciais() {
//        // Simulação: No futuro, o ConcedenteService buscará as empresas do banco.
//        view.adicionarConcedente("Selecione...");
//        view.adicionarConcedente("Tech Solutions S/A");
//        view.adicionarConcedente("Inova Web LTDA");
//        view.adicionarConcedente("Prefeitura Municipal");
//    }
//
//    private void salvarSupervisor() {
//        // Extraindo os dados do componente modularizado
//        String nome = view.getPainelPessoa().getNome();
//        String cpf = view.getPainelPessoa().getCpf();
//
//        // Extraindo os dados específicos do supervisor
//        String registroProfissional = view.getRegistroProfissional();
//        String concedente = view.getConcedenteSelecionada();
//
//        if (nome.trim().isEmpty() || registroProfissional.trim().isEmpty() || concedente.equals("Selecione...")) {
//            view.exibirMensagem("Preencha o Nome, Registro Profissional e selecione a Concedente.");
//            return;
//        }
//
//        // Fluxo futuro de empacotamento:
//        // 1. Pessoa pessoa = new Pessoa(nome, cpf, ...);
//        // 2. Concedente emp = concedenteService.buscarPorNome(concedente);
//        // 3. SupervisorConcedente supervisor = new SupervisorConcedente(pessoa, emp, registroProfissional);
//        // 4. supervisorService.salvar(supervisor);
//
//        view.exibirMensagem("Supervisor cadastrado e vinculado à " + concedente + " com sucesso!");
//        view.getPainelPessoa().limparCampos();
//        // Lógica adicional para limpar os campos da view principal
//    }
//}