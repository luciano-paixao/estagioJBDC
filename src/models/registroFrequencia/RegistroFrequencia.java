package models.registroFrequencia;

import java.time.LocalDate;
import models.estagio.Estagio;

public class RegistroFrequencia {

    private int idRegistro;
    private LocalDate dataEntrega;
    private String observacoes;
    private LocalDate dataAssinaturaDiscente;
    private LocalDate dataAssinaturaSupervisor;
    private Estagio estagio;

    public RegistroFrequencia() {
    }

    public RegistroFrequencia(LocalDate dataEntrega, String observacoes, LocalDate dataAssinaturaDiscente,
                               LocalDate dataAssinaturaSupervisor, Estagio estagio) {
        this.dataEntrega = dataEntrega;
        this.observacoes = observacoes;
        this.dataAssinaturaDiscente = dataAssinaturaDiscente;
        this.dataAssinaturaSupervisor = dataAssinaturaSupervisor;
        this.estagio = estagio;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDate getDataAssinaturaDiscente() {
        return dataAssinaturaDiscente;
    }

    public void setDataAssinaturaDiscente(LocalDate dataAssinaturaDiscente) {
        this.dataAssinaturaDiscente = dataAssinaturaDiscente;
    }

    public LocalDate getDataAssinaturaSupervisor() {
        return dataAssinaturaSupervisor;
    }

    public void setDataAssinaturaSupervisor(LocalDate dataAssinaturaSupervisor) {
        this.dataAssinaturaSupervisor = dataAssinaturaSupervisor;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }
}
