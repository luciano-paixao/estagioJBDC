package models.apoliceSeguro;

import java.time.LocalDate;
import models.estagio.Estagio;

public class ApoliceSeguro {

    private int idApolice;
    private String numero;
    private ResponsavelContratacao responsavelContratacao;
    private String seguradora;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Estagio estagio;

    public ApoliceSeguro() {
    }

    public ApoliceSeguro(String numero, ResponsavelContratacao responsavelContratacao, String seguradora, LocalDate dataInicio,
                          LocalDate dataFim, Estagio estagio) {
        this.numero = numero;
        this.responsavelContratacao = responsavelContratacao;
        this.seguradora = seguradora;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.estagio = estagio;
    }

    public int getIdApolice() {
        return idApolice;
    }

    public void setIdApolice(int idApolice) {
        this.idApolice = idApolice;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public ResponsavelContratacao getResponsavelContratacao() {
        return responsavelContratacao;
    }

    public void setResponsavelContratacao(ResponsavelContratacao responsavelContratacao) {
        this.responsavelContratacao = responsavelContratacao;
    }

    public String getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(String seguradora) {
        this.seguradora = seguradora;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }
}
