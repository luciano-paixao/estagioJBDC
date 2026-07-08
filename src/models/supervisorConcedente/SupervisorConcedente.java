package models.supervisorConcedente;

import models.concedente.Concedente;
import models.pessoa.Pessoa;

public class SupervisorConcedente {

    private int idSupervisor;
    private Concedente concedente;
    private Pessoa pessoa;
    private String registroProfissional;

    public SupervisorConcedente() {
    }

    public SupervisorConcedente(Concedente concedente, Pessoa pessoa, String registroProfissional) {
        this.concedente = concedente;
        this.pessoa = pessoa;
        this.registroProfissional = registroProfissional;
    }

    public int getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(int idSupervisor) {
        this.idSupervisor = idSupervisor;
    }

    public Concedente getConcedente() {
        return concedente;
    }

    public void setConcedente(Concedente concedente) {
        this.concedente = concedente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    @Override
    public String toString() {
        return "SupervisorConcedente #" + this.idSupervisor + " - " + this.concedente.getAreaAtuacao() + " - " + this.idSupervisor + " - " + this.concedente.getAreaAtuacao();
    }
}
