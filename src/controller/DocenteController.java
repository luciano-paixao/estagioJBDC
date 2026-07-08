package controller;

import view.TelaCadastroDocente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocenteController {

    public class DocenteController {
        private TelaCadastroDocente view;

        //private DocenteDAO docenteDAO;
        //private PessoaDAO pessoaDAO;

        public DocenteController(TelaCadastroDocente view) {
            this.view = view;
            //this.docenteDAO = new DocenteDAO();
            //this.pessoaDAO = new PessoaDAO();

            inicializarEventos();
        }
    }

    private void inicializarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarDocente());
        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void salvarDocente() {
        String nome = view.getPainelPessoa().getNome();
        String siape = view.getPainelDocente().getSiape();

        // Pessoa pessoa = new Pessoa(nome, ...);
        // Docente docente = new Docente(pessoa, siape, ...);

        view.exibirMensagem("Docente salvo com sucesso!");
        view.getPainelPessoa().limparCampos();
        view.getPainelDocente().limparCampos();
    }
}