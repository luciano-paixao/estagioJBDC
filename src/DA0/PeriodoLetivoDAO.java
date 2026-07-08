package DAO;

import conexao.Conexao;
import models.periodoLetivo.PeriodoLetivo; // Ajuste o import conforme o seu projeto
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}