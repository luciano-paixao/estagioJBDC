package models.termoCompromisso;

import models.estagio.Estagio;

public class TermoCompromisso {

    private int idTermo;
    private String numeroTermo;
    private HorasSemanais horasSemanais;
    private int horasTotais;
    private Double remuneracao;
    private Double auxTransporte;
    private boolean assinaturaDiscente;
    private boolean assinaturaCoordenador;
    private boolean assinaturaConcedente;
    private Estagio estagio;

    public TermoCompromisso() {
    }

    public TermoCompromisso(String numeroTermo, HorasSemanais horasSemanais, int horasTotais, Double remuneracao,
                             Double auxTransporte, boolean assinaturaDiscente, boolean assinaturaCoordenador,
                             boolean assinaturaConcedente, Estagio estagio) {
        this.numeroTermo = numeroTermo;
        this.horasSemanais = horasSemanais;
        this.horasTotais = horasTotais;
        this.remuneracao = remuneracao;
        this.auxTransporte = auxTransporte;
        this.assinaturaDiscente = assinaturaDiscente;
        this.assinaturaCoordenador = assinaturaCoordenador;
        this.assinaturaConcedente = assinaturaConcedente;
        this.estagio = estagio;
    }

    public int getIdTermo() {
        return idTermo;
    }

    public void setIdTermo(int idTermo) {
        this.idTermo = idTermo;
    }

    public String getNumeroTermo() {
        return numeroTermo;
    }

    public void setNumeroTermo(String numeroTermo) {
        this.numeroTermo = numeroTermo;
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
}
