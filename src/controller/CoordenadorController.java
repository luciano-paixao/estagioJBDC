package controller;

import DAO.CoordenadorDAO;
import DAO.DocenteDAO;
import DAO.PessoaDAO;
import view.TelaCadastroCoordenador;

public class CoordenadorController {

    private TelaCadastroCoordenador view;

    private CoordenadorDAO coordenadorDAO;
    private DocenteDAO docenteDAO;
    private PessoaDAO pessoaDAO;

    public CoordenadorController(TelaCadastroCoordenador view) {
        this.view = view;
        this.coordenadorDAO = new CoordenadorDAO();
        this.docenteDAO = new DocenteDAO();
        this.pessoaDAO = new PessoaDAO();

        inicializarEventos();
    }

    private void inicializarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarCoordenador());
        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void salvarCoordenador() {
        String nome = view.getPainelPessoa().getNome();
        String siape = view.getPainelDocente().getSiape();
        String portaria = view.getNumeroPortaria();

        if (nome.isEmpty() || siape.isEmpty() || portaria.isEmpty()) {
            view.exibirMensagem("Preencha todos os campos obrigatórios (Nome, SIAPE e Portaria).");
            return;
        }

        boolean sucesso = true; // Apenas para simulação!

        if (sucesso) {
            view.exibirMensagem("Discente cadastrado com sucesso!");
            view.limparCampos();
        } else {
            view.exibirMensagem("Erro ao cadastrar o discente no banco de dados.");
        }
    }
}