package models.estagio;

import java.time.LocalDate;
import models.discente.Discente;
import models.supervisorConcedente.SupervisorConcedente;
import models.coordenadorEstagio.CoordenadorEstagio;
import models.concedente.Concedente;
import models.periodoLetivo.PeriodoLetivo;

public class Estagio {

    private int idEstagio;
    private TipoEstagio tipo;
    private AmbitoEstagio ambito;
    private Integer cargaHoraria;
    private StatusEstagio status;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String observacoes;
    private Discente discente;
    private SupervisorConcedente supervisorConcedente;
    private CoordenadorEstagio coordenadorEstagio;
    private Concedente concedente;
    private PeriodoLetivo periodoLetivo;

    public Estagio() {
    }

    public Estagio(TipoEstagio tipo, AmbitoEstagio ambito, Integer cargaHoraria, StatusEstagio status, LocalDate dataInicio,
                    LocalDate dataFim, String observacoes, Discente discente, SupervisorConcedente supervisorConcedente,
                    CoordenadorEstagio coordenadorEstagio, Concedente concedente, PeriodoLetivo periodoLetivo) {
        this.tipo = tipo;
        this.ambito = ambito;
        this.cargaHoraria = cargaHoraria;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacoes = observacoes;
        this.discente = discente;
        this.supervisorConcedente = supervisorConcedente;
        this.coordenadorEstagio = coordenadorEstagio;
        this.concedente = concedente;
        this.periodoLetivo = periodoLetivo;
    }

    public int getIdEstagio() {
        return idEstagio;
    }

    public void setIdEstagio(int idEstagio) {
        this.idEstagio = idEstagio;
    }

    public TipoEstagio getTipo() {
        return tipo;
    }

    public void setTipo(TipoEstagio tipo) {
        this.tipo = tipo;
    }

    public AmbitoEstagio getAmbito() {
        return ambito;
    }

    public void setAmbito(AmbitoEstagio ambito) {
        this.ambito = ambito;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public StatusEstagio getStatus() {
        return status;
    }

    public void setStatus(StatusEstagio status) {
        this.status = status;
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

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public SupervisorConcedente getSupervisorConcedente() {
        return supervisorConcedente;
    }

    public void setSupervisorConcedente(SupervisorConcedente supervisorConcedente) {
        this.supervisorConcedente = supervisorConcedente;
    }

    public CoordenadorEstagio getCoordenadorEstagio() {
        return coordenadorEstagio;
    }

    public void setCoordenadorEstagio(CoordenadorEstagio coordenadorEstagio) {
        this.coordenadorEstagio = coordenadorEstagio;
    }

    public Concedente getConcedente() {
        return concedente;
    }

    public void setConcedente(Concedente concedente) {
        this.concedente = concedente;
    }

    public PeriodoLetivo getPeriodoLetivo() {
        return periodoLetivo;
    }

    public void setPeriodoLetivo(PeriodoLetivo periodoLetivo) {
        this.periodoLetivo = periodoLetivo;
    }
}
