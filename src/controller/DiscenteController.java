package controller;

import DAO.DiscenteDAO;
import DAO.PessoaDAO; // Assumindo que você criará/tem este DAO
import models.discente.Discente;
import models.pessoa.Pessoa;
import view.TelaCadastroDiscente;

public class DiscenteController {

    private TelaCadastroDiscente view;
    private DiscenteDAO discenteDAO;
    private PessoaDAO pessoaDAO;

    public DiscenteController(TelaCadastroDiscente view) {
        this.view = view;
        this.discenteDAO = new DiscenteDAO();
        this.pessoaDAO = new PessoaDAO();
    }
}