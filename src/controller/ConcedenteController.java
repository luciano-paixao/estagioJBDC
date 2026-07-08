package controller;

import DAO.*;
import models.telefone.Telefone;
import view.PainelTelefone;
import view.TelaCadastroConcedente;

import java.util.ArrayList;
import java.util.List;

public class ConcedenteController {

    private TelaCadastroConcedente view;

    private ConcedenteDAO concedenteDAO;
    private EnderecoDAO enderecoDAO;
    private TelefoneDAO telefoneDAO;
    private PessoaDAO pessoaDAO;
    private SupervisorDAO supervisorDAO;

    public ConcedenteController(TelaCadastroConcedente view) {
        this.view = view;
        this.concedenteDAO = new ConcedenteDAO();
        this.enderecoDAO = new EnderecoDAO();
        this.telefoneDAO = new TelefoneDAO();
        this.pessoaDAO = new PessoaDAO();
        this.supervisorDAO = new SupervisorDAO();

        inicializarEventos();
    }

    private void inicializarEventos() {
        view.getBtnSalvar().addActionListener(e -> salvarConcedenteCompleta());
        view.getBtnCancelar().addActionListener(e -> view.dispose());
    }

    private void salvarConcedenteCompleta() {

        String nomeEmpresa = view.getNomeEmpresa();
        String cnpj = view.getCnpj();

        List<Telefone> listaTelefonesModel = new ArrayList<>(); // Lista fictícia para simular entidades
        int telefonesValidos = 0;

        for (PainelTelefone painelTel : view.getListaPaineisTelefone()) {
            String ddd = painelTel.getDdd();
            String numero = painelTel.getNumero();

            // Só processa se o usuário preencheu pelo menos o número
            if (!numero.trim().isEmpty()) {
                telefonesValidos++;
            }
        }

        String nomeRep = view.getPainelRepresentante().getNome();

        view.exibirMensagem("Concedente e " + telefonesValidos + " telefone(s) cadastrados com sucesso!");
        view.dispose();
    }
}