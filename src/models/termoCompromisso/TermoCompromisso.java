package models.termoCompromisso;

import models.estagio.Estagio;

import java.time.LocalDate;

public class TermoCompromisso {

    private int idTermo;
    private HorasSemanais horasSemanais;
    private int horasTotais;
    private Double remuneracao;
    private Double auxTransporte;
    private boolean assinaturaDiscente;
    private boolean assinaturaCoordenador;
    private boolean assinaturaConcedente;
    private Estagio estagio;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private LocalDate dataRecesso;

    public TermoCompromisso() {
    }

    public TermoCompromisso(HorasSemanais horasSemanais, int horasTotais, Double remuneracao, Double auxTransporte,
                            boolean assinaturaDiscente, boolean assinaturaCoordenador, boolean assinaturaConcedente,
                            Estagio estagio, LocalDate dataInicio, LocalDate dataFim, LocalDate dataRecesso) {
        this.horasSemanais = horasSemanais;
        this.horasTotais = horasTotais;
        this.remuneracao = remuneracao;
        this.auxTransporte = auxTransporte;
        this.assinaturaDiscente = assinaturaDiscente;
        this.assinaturaCoordenador = assinaturaCoordenador;
        this.assinaturaConcedente = assinaturaConcedente;
        this.estagio = estagio;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataRecesso = dataRecesso;
    }

    public int getIdTermo() {
        return idTermo;
    }

    public void setIdTermo(int idTermo) {
        this.idTermo = idTermo;
    }

    public HorasSemanais getHorasSemanais() {
        return horasSemanais;
    }

    public void setHorasSemanais(HorasSemanais horasSemanais) {
        this.horasSemanais = horasSemanais;
    }

    public int getHorasTotais() {
        return horasTotais;
    }

    public void setHorasTotais(int horasTotais) {
        this.horasTotais = horasTotais;
    }

    public Double getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(Double remuneracao) {
        this.remuneracao = remuneracao;
    }

    public Double getAuxTransporte() {
        return auxTransporte;
    }

    public void setAuxTransporte(Double auxTransporte) {
        this.auxTransporte = auxTransporte;
    }

    public boolean isAssinaturaDiscente() {
        return assinaturaDiscente;
    }

    public void setAssinaturaDiscente(boolean assinaturaDiscente) {
        this.assinaturaDiscente = assinaturaDiscente;
    }

    public boolean isAssinaturaCoordenador() {
        return assinaturaCoordenador;
    }

    public void setAssinaturaCoordenador(boolean assinaturaCoordenador) {
        this.assinaturaCoordenador = assinaturaCoordenador;
    }

    public boolean isAssinaturaConcedente() {
        return assinaturaConcedente;
    }

    public void setAssinaturaConcedente(boolean assinaturaConcedente) {
        this.assinaturaConcedente = assinaturaConcedente;
    }

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
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

    public LocalDate getDataRecesso() {
        return dataRecesso;
    }

    public void setDataRecesso(LocalDate dataRecesso) {
        this.dataRecesso = dataRecesso;
    }
}
