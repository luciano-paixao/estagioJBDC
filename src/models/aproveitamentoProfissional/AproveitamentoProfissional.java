package models.aproveitamentoProfissional;

import java.time.LocalDate;
import models.discente.Discente;
import models.coordenadorEstagio.CoordenadorEstagio;
import models.estagio.Estagio;

public class AproveitamentoProfissional {

    private int idAproveitamento;
    private models.aproveitamentoProfissional.CondicaoProfissional condicao;
    private String cargoExercido;
    private int cargaHoraria;
    private StatusDeferimento statusDeferimento;
    private String documentoComprobatorio;
    private String nomeEmpresa;
    private LocalDate dataInicioAtividade;
    private LocalDate dataFimAtividade;
    private String relatorioTecnico;
    private Discente discente;
    private CoordenadorEstagio coordenadorEstagio;
    private Estagio estagio;

    public AproveitamentoProfissional() {
    }

    public AproveitamentoProfissional(CondicaoProfissional condicao, String cargoExercido, int cargaHoraria,
                                       StatusDeferimento statusDeferimento, String documentoComprobatorio, String nomeEmpresa,
                                       LocalDate dataInicioAtividade, LocalDate dataFimAtividade, String relatorioTecnico,
                                       Discente discente, CoordenadorEstagio coordenadorEstagio, Estagio estagio) {
        this.condicao = condicao;
        this.cargoExercido = cargoExercido;
        this.cargaHoraria = cargaHoraria;
        this.statusDeferimento = statusDeferimento;
        this.documentoComprobatorio = documentoComprobatorio;
        this.nomeEmpresa = nomeEmpresa;
        this.dataInicioAtividade = dataInicioAtividade;
        this.dataFimAtividade = dataFimAtividade;
        this.relatorioTecnico = relatorioTecnico;
        this.discente = discente;
        this.coordenadorEstagio = coordenadorEstagio;
        this.estagio = estagio;
    }

    public int getIdAproveitamento() {
        return idAproveitamento;
    }

    public void setIdAproveitamento(int idAproveitamento) {
        this.idAproveitamento = idAproveitamento;
    }

    public CondicaoProfissional getCondicao() {
        return condicao;
    }

    public void setCondicao(CondicaoProfissional condicao) {
        this.condicao = condicao;
    }

    public String getCargoExercido() {
        return cargoExercido;
    }

    public void setCargoExercido(String cargoExercido) {
        this.cargoExercido = cargoExercido;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public StatusDeferimento getStatusDeferimento() {
        return statusDeferimento;
    }

    public void setStatusDeferimento(StatusDeferimento statusDeferimento) {
        this.statusDeferimento = statusDeferimento;
    }

    public String getDocumentoComprobatorio() {
        return documentoComprobatorio;
    }

    public void setDocumentoComprobatorio(String documentoComprobatorio) {
        this.documentoComprobatorio = documentoComprobatorio;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public LocalDate getDataInicioAtividade() {
        return dataInicioAtividade;
    }

    public void setDataInicioAtividade(LocalDate dataInicioAtividade) {
        this.dataInicioAtividade = dataInicioAtividade;
    }

    public LocalDate getDataFimAtividade() {
        return dataFimAtividade;
    }

    public void setDataFimAtividade(LocalDate dataFimAtividade) {
        this.dataFimAtividade = dataFimAtividade;
    }

    public String getRelatorioTecnico() {
        return relatorioTecnico;
    }

    public void setRelatorioTecnico(String relatorioTecnico) {
        this.relatorioTecnico = relatorioTecnico;
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

    public Estagio getEstagio() {
        return estagio;
    }

    public void setEstagio(Estagio estagio) {
        this.estagio = estagio;
    }
}
