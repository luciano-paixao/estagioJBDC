package models.planoAtividade;

import models.termoCompromisso.TermoCompromisso;

public class PlanoAtividade {

    private int idPlanoAtividade;
    private String areaAtuacao;
    private String justificativa;
    private String objetivos;
    private String planoAtividades;

    public PlanoAtividade() {
    }

    public PlanoAtividade(int idPlanoAtividade, String areaAtuacao, String justificativa, String objetivos, String planoAtividades) {
        this.idPlanoAtividade = idPlanoAtividade;
        this.areaAtuacao = areaAtuacao;
        this.justificativa = justificativa;
        this.objetivos = objetivos;
        this.planoAtividades = planoAtividades;
    }

    public int getIdPlanoAtividade() {
        return idPlanoAtividade;
    }

    public void setIdPlanoAtividade(int idPlanoAtividade) {
        this.idPlanoAtividade = idPlanoAtividade;
    }

    public String getAreaAtuacao() {
        return areaAtuacao;
    }

    public void setAreaAtuacao(String areaAtuacao) {
        this.areaAtuacao = areaAtuacao;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getPlanoAtividades() {
        return planoAtividades;
    }

    public void setPlanoAtividades(String planoAtividades) {
        this.planoAtividades = planoAtividades;
    }
}
