package models.coordenadorEstagio;

import java.time.LocalDate;
import models.docente.Docente;

public class CoordenadorEstagio {

    private int idCoordenadorEstagio;
    private int idDocente;
    private int idPessoa;
    private String numeroPortaria;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;
    private Docente docente;

    public CoordenadorEstagio() {
    }

    public CoordenadorEstagio(String numeroPortaria, LocalDate dataInicioVigencia, LocalDate dataFimVigencia, Docente docente) {
        this.numeroPortaria = numeroPortaria;
        this.dataInicioVigencia = dataInicioVigencia;
        this.dataFimVigencia = dataFimVigencia;
        this.docente = docente;
    }

    public int getIdCoordenadorEstagio() {
        return idCoordenadorEstagio;
    }

    public void setIdCoordenadorEstagio(int idCoordenadorEstagio) {
        this.idCoordenadorEstagio = idCoordenadorEstagio;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNumeroPortaria() {
        return numeroPortaria;
    }

    public void setNumeroPortaria(String numeroPortaria) {
        this.numeroPortaria = numeroPortaria;
    }

    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }


    @Override
    public String toString() {
        return "CoordenadorEstagio #" + this.idCoordenadorEstagio + " - " + this.numeroPortaria;
    }
}
