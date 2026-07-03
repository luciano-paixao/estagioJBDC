package models.relatorioFinal;

import models.estagio.Estagio;

public class RelatorioFinal {

    private int idRelatorio;
    private String arquivo;
    private String titulo;
    private boolean assinaturaDiscente;
    private boolean assinaturaSupervisor;
    private Estagio estagio;

    public RelatorioFinal() {
    }

    public RelatorioFinal(String arquivo, String titulo, boolean assinaturaDiscente, boolean assinaturaSupervisor,
                           Estagio estagio) {
        this.arquivo = arquivo;
        this.titulo = titulo;
        this.assinaturaDiscente = assinaturaDiscente;
        this.assinaturaSupervisor = assinaturaSupervisor;
        this.estagio = estagio;
    }

    public int getIdRelatorio() {
        return idRelatorio;
    }

    public void setIdRelatorio(int idRelatorio) {
        this.idRelatorio = idRelatorio;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isAssinaturaDiscente() {
        return assinaturaDiscente;
    }

    public void setAssinaturaDiscente(boolean assinaturaDiscente) {
        this.assinaturaDiscente = assinaturaDiscente;
    }

    public boolean isAssinaturaSupervisor() {
        return assinaturaSupervisor;
    }

    public void setAssinaturaSupervisor(boolean assinaturaSupervisor) {
        this.assinaturaSupervisor = assinaturaSupervisor;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }
}
