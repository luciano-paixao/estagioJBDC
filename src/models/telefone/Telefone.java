package models.telefone;

import models.concedente.Concedente;
import models.pessoa.Pessoa;

public class Telefone {

    private int idTelefone;
    private String ddd;
    private String numero;
    private TipoTelefone tipo;
    private Concedente concedente;
    private Pessoa pessoa;

    public Telefone() {
    }

    public Telefone(String ddd, String numero, TipoTelefone tipo, Concedente concedente, Pessoa pessoa) {
        this.ddd = ddd;
        this.numero = numero;
        this.tipo = tipo;
        this.concedente = concedente;
        this.pessoa = pessoa;
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getDdd() {
        return ddd;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public TipoTelefone getTipo() {
        return tipo;
    }

    public void setTipo(TipoTelefone tipo) {
        this.tipo = tipo;
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
}
