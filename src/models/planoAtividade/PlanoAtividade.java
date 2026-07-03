package models.planoAtividade;

import models.termoCompromisso.TermoCompromisso;

public class PlanoAtividade {

    private int idPlanoAtividade;
    private String arquivo;
    private TermoCompromisso termoCompromisso;

    public PlanoAtividade() {
    }

    public PlanoAtividade(String arquivo, TermoCompromisso termoCompromisso) {
        this.arquivo = arquivo;
        this.termoCompromisso = termoCompromisso;
    }

    public int getIdPlanoAtividade() {
        return idPlanoAtividade;
    }

    public void setIdPlanoAtividade(int idPlanoAtividade) {
        this.idPlanoAtividade = idPlanoAtividade;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public TermoCompromisso getTermoCompromisso() {
        return termoCompromisso;
    }

    public void setTermoCompromisso(TermoCompromisso termoCompromisso) {
        this.termoCompromisso = termoCompromisso;
    }
}
