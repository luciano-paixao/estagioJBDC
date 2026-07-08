package DAO;

import conexao.Conexao;
import models.periodoLetivo.PeriodoLetivo; // Ajuste o import conforme o seu projeto
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodoLetivoDAO {

    public void cadastrarPeriodoLetivo(PeriodoLetivo periodoLetivo) {

        String sql = "INSERT INTO periodo_letivo (ano, periodo, data_inicio, data_fim) VALUES (" +
                "?, ?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setInt(1, periodoLetivo.getAno());

            insertPs.setString(2, Integer.toString(periodoLetivo.getPeriodo()));

            insertPs.setDate(3, java.sql.Date.valueOf(periodoLetivo.getDataInicio()));
            insertPs.setDate(4, java.sql.Date.valueOf(periodoLetivo.getDataFim()));

            int insertCount = insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PeriodoLetivo> listarTodos() {
        String sql = "SELECT * FROM periodo_letivo";
        List<PeriodoLetivo> periodos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PeriodoLetivo periodoLetivo = new PeriodoLetivo();
                periodoLetivo.setIdPeriodo(getInt(rs, "id_periodo", "id_periodo_letivo"));
                periodoLetivo.setAno(getInt(rs, "ano"));
                periodoLetivo.setPeriodo(getInt(rs, "periodo"));

                java.sql.Date inicio = rs.getDate("data_inicio");
                if (inicio != null) {
                    periodoLetivo.setDataInicio(inicio.toLocalDate());
                }

                java.sql.Date fim = rs.getDate("data_fim");
                if (fim != null) {
                    periodoLetivo.setDataFim(fim.toLocalDate());
                }

                periodos.add(periodoLetivo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return periodos;
    }

    private int getInt(ResultSet rs, String... columnNames) throws SQLException {
        for (String columnName : columnNames) {
            try {
                return rs.getInt(columnName);
            } catch (SQLException ignored) {
            }
        }
        return 0;
    }
}
