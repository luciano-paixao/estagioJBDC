package models.concedente;

import models.pessoa.Pessoa;
import models.endereco.Endereco;

public class Concedente {

    private int idConcedente;
    private String nome;
    private String cnpj;
    private TipoConcedente tipo;
    private String areaAtuacao;
    private Pessoa representante;
    private Endereco endereco;

    public Concedente() {
    }

    public Concedente(String nome, String cnpj, TipoConcedente tipo, String areaAtuacao, Pessoa representante, Endereco endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.tipo = tipo;
        this.areaAtuacao = areaAtuacao;
        this.representante = representante;
        this.endereco = endereco;
    }

    public int getIdConcedente() {
        return idConcedente;
    }

    public void setIdConcedente(int idConcedente) {
        this.idConcedente = idConcedente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public TipoConcedente getTipo() {
        return tipo;
    }

    public void setTipo(TipoConcedente tipo) {
        this.tipo = tipo;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public Pessoa getRepresentante() {
        return representante;
    }

    public void setRepresentante(Pessoa representante) {
        this.representante = representante;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Concedente{" +
                "nome: '" + nome + '\'' +
                ", cnpj: '" + cnpj + '\'' +
                ", tipo: " + tipo +
                '}';
    }
}
