package models.periodoLetivo;

import java.time.LocalDate;

public class PeriodoLetivo {

    private int idPeriodo;
    private int ano;
    private int periodo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    public PeriodoLetivo() {
    }

    public PeriodoLetivo(int ano, int periodo, LocalDate dataInicio, LocalDate dataFim) {
        this.ano = ano;
        this.periodo = periodo;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public int getIdPeriodo() {
        return idPeriodo;
    }

    public void setIdPeriodo(int idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
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

    @Override
    public String toString() {
        return "PeriodoLetivo #" + this.idPeriodo + " - " + this.periodo + " - " + this.ano;
    }
}
