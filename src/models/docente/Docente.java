package models.docente;

import models.pessoa.Pessoa;

public class Docente {

    private int idDocente;
    private String matriculaSiape;
    private String departamento;
    private Titulacao titulacao;
    private Pessoa pessoa;

    public Docente() {
    }

    public Docente(String matriculaSiape, String departamento, Titulacao titulacao, Pessoa pessoa) {
        this.matriculaSiape = matriculaSiape;
        this.departamento = departamento;
        this.titulacao = titulacao;
        this.pessoa = pessoa;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getMatriculaSiape() {
        return matriculaSiape;
    }

    public void setMatriculaSiape(String matriculaSiape) {
        this.matriculaSiape = matriculaSiape;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public Titulacao getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(Titulacao titulacao) {
        this.titulacao = titulacao;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
