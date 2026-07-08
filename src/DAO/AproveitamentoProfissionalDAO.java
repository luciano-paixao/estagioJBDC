package DAO;

import conexao.Conexao;
import models.aproveitamentoProfissional.AproveitamentoProfissional;
import models.aproveitamentoProfissional.CondicaoProfissional;
import models.aproveitamentoProfissional.StatusDeferimento;
import models.coordenadorEstagio.CoordenadorEstagio;
import models.discente.Discente;
import models.estagio.Estagio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AproveitamentoProfissionalDAO {

    public void cadastrarAproveitamento(AproveitamentoProfissional aproveitamento) {
        String sql = "INSERT INTO aproveitamento_profissional " +
                "(condicao, cargo_exercido, carga_horaria, status_deferimento, documento_comprobatorio, nome_empresa, " +
                "data_inicio_atividade, data_fim_atividade, relatorio_tecnico, id_discente, id_coordenador_estagio, id_estagio) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, aproveitamento.getCondicao().toString());
            ps.setString(2, aproveitamento.getCargoExercido());
            ps.setInt(3, aproveitamento.getCargaHoraria());
            ps.setString(4, aproveitamento.getStatusDeferimento().toString());
            ps.setString(5, aproveitamento.getDocumentoComprobatorio());
            ps.setString(6, aproveitamento.getNomeEmpresa());
            ps.setDate(7, java.sql.Date.valueOf(aproveitamento.getDataInicioAtividade()));
            ps.setDate(8, java.sql.Date.valueOf(aproveitamento.getDataFimAtividade()));
            ps.setString(9, aproveitamento.getRelatorioTecnico());
            ps.setInt(10, aproveitamento.getDiscente().getIdDiscente());
            ps.setInt(11, aproveitamento.getCoordenadorEstagio().getIdCoordenadorEstagio());
            ps.setInt(12, aproveitamento.getEstagio().getIdEstagio());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AproveitamentoProfissional> listarTodos() {
        String sql = "SELECT * FROM aproveitamento_profissional";
        List<AproveitamentoProfissional> aproveitamentos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                AproveitamentoProfissional aproveitamento = new AproveitamentoProfissional();
                aproveitamento.setIdAproveitamento(getInt(rs, "id_aproveitamento"));

                String condicao = rs.getString("condicao");
                if (condicao != null) {
                    aproveitamento.setCondicao(CondicaoProfissional.valueOf(condicao));
                }

                aproveitamento.setCargoExercido(rs.getString("cargo_exercido"));
                aproveitamento.setCargaHoraria(getInt(rs, "carga_horaria"));

                String status = rs.getString("status_deferimento");
                if (status != null) {
                    aproveitamento.setStatusDeferimento(StatusDeferimento.valueOf(status));
                }

                aproveitamento.setDocumentoComprobatorio(rs.getString("documento_comprobatorio"));
                aproveitamento.setNomeEmpresa(rs.getString("nome_empresa"));

                java.sql.Date inicio = rs.getDate("data_inicio_atividade");
                if (inicio != null) {
                    aproveitamento.setDataInicioAtividade(inicio.toLocalDate());
                }

                java.sql.Date fim = rs.getDate("data_fim_atividade");
                if (fim != null) {
                    aproveitamento.setDataFimAtividade(fim.toLocalDate());
                }

                aproveitamento.setRelatorioTecnico(rs.getString("relatorio_tecnico"));

                Discente discente = new Discente();
                discente.setIdDiscente(getInt(rs, "id_discente"));
                aproveitamento.setDiscente(discente);

                CoordenadorEstagio coordenador = new CoordenadorEstagio();
                coordenador.setIdCoordenadorEstagio(getInt(rs, "id_coordenador_estagio"));
                aproveitamento.setCoordenadorEstagio(coordenador);

                Estagio estagio = new Estagio();
                estagio.setIdEstagio(getInt(rs, "id_estagio"));
                aproveitamento.setEstagio(estagio);

                aproveitamentos.add(aproveitamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return aproveitamentos;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
