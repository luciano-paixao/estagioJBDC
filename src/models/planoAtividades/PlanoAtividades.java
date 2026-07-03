package models.planoAtividades;

import java.time.LocalDate;
import models.estagio.Estagio;
import models.discente.Discente;
import models.coordenadorEstagio.CoordenadorEstagio;
import models.supervisorConcedente.SupervisorConcedente;

public class PlanoAtividades {

    private int idPlano;
    private boolean assinadoDiscente;
    private LocalDate dataElaboracao;
    private StatusPlanoAtividades status;
    private Estagio estagio;
    private LocalDate dataAssinaturaDiscente;
    private LocalDate dataAssinaturaSupervisor;
    private LocalDate dataAssinaturaCoordenador;
    private Discente discente;
    private CoordenadorEstagio coordenadorEstagio;
    private SupervisorConcedente supervisorConcedente;
    private Integer cargaHorariaSemanal;

    public PlanoAtividades() {
    }

    public PlanoAtividades(boolean assinadoDiscente, LocalDate dataElaboracao, StatusPlanoAtividades status, Estagio estagio,
                            LocalDate dataAssinaturaDiscente, LocalDate dataAssinaturaSupervisor,
                            LocalDate dataAssinaturaCoordenador, Discente discente, CoordenadorEstagio coordenadorEstagio,
                            SupervisorConcedente supervisorConcedente, Integer cargaHorariaSemanal) {
        this.assinadoDiscente = assinadoDiscente;
        this.dataElaboracao = dataElaboracao;
        this.status = status;
        this.estagio = estagio;
        this.dataAssinaturaDiscente = dataAssinaturaDiscente;
        this.dataAssinaturaSupervisor = dataAssinaturaSupervisor;
        this.dataAssinaturaCoordenador = dataAssinaturaCoordenador;
        this.discente = discente;
        this.coordenadorEstagio = coordenadorEstagio;
        this.supervisorConcedente = supervisorConcedente;
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public boolean isAssinadoDiscente() {
        return assinadoDiscente;
    }

    public void setAssinadoDiscente(boolean assinadoDiscente) {
        this.assinadoDiscente = assinadoDiscente;
    }

    public LocalDate getDataElaboracao() {
        return dataElaboracao;
    }

    public void setDataElaboracao(LocalDate dataElaboracao) {
        this.dataElaboracao = dataElaboracao;
    }

    public StatusPlanoAtividades getStatus() {
        return status;
    }

    public void setStatus(StatusPlanoAtividades status) {
        this.status = status;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
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

    public LocalDate getDataAssinaturaCoordenador() {
        return dataAssinaturaCoordenador;
    }

    public void setDataAssinaturaCoordenador(LocalDate dataAssinaturaCoordenador) {
        this.dataAssinaturaCoordenador = dataAssinaturaCoordenador;
    }

    public Discente getDiscente() {
        return discente;
    }

    public void setDiscente(Discente discente) {
        this.discente = discente;
    }

    public CoordenadorEstagio getCoordenadorEstagio() {
        return coordenadorEstagio;
    }

    public void setCoordenadorEstagio(CoordenadorEstagio coordenadorEstagio) {
        this.coordenadorEstagio = coordenadorEstagio;
    }

    public SupervisorConcedente getSupervisorConcedente() {
        return supervisorConcedente;
    }

    public void setSupervisorConcedente(SupervisorConcedente supervisorConcedente) {
        this.supervisorConcedente = supervisorConcedente;
    }

    public Integer getCargaHorariaSemanal() {
        return cargaHorariaSemanal;
    }

    public void setCargaHorariaSemanal(Integer cargaHorariaSemanal) {
        this.cargaHorariaSemanal = cargaHorariaSemanal;
    }
}
