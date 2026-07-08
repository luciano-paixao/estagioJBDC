package models.discente;

import models.pessoa.Pessoa;
import models.periodoLetivo.PeriodoLetivo;

public class Discente {

    private int idDiscente;
    private Pessoa pessoa;
    private String matricula;
    private String curso;
    private SituacaoDiscente situacao;
    private PeriodoLetivo periodoLetivo;

    public Discente() {
    }

    public Discente(Pessoa pessoa, String matricula, String curso, SituacaoDiscente situacao, PeriodoLetivo periodoLetivo) {
        this.pessoa = pessoa;
        this.matricula = matricula;
        this.curso = curso;
        this.situacao = situacao;
        this.periodoLetivo = periodoLetivo;
    }

    public int getIdDiscente() {
        return idDiscente;
    }

    public void setIdDiscente(int idDiscente) {
        this.idDiscente = idDiscente;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public SituacaoDiscente getSituacao() {
        return situacao;
    }

    public void setSituacao(SituacaoDiscente situacao) {
        this.situacao = situacao;
    }

    public PeriodoLetivo getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }

    @Override
    public String toString() {
        return "Discente #" + this.idDiscente + " - " + this.pessoa.getNome() + " - " + this.matricula;
    }
}
