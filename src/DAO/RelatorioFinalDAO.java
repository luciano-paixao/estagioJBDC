package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Conexao;
import models.relatorioFinal.RelatorioFinal;

public class RelatorioFinalDAO {
    public void cadastrarRelatorioFinal(RelatorioFinal rel) {
        String sql = "INSERT INTO relatorio_final(arquivo, titulo, assinatura_discente, assinatura_supervisor, id_estagio) VALUES(" +
                "?, ?, ?, ?, ?" +
                ")";
        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setString(1, rel.getArquivo());
            insertPs.setString(2, rel.getTitulo());
            insertPs.setBoolean(3, true);
            insertPs.setBoolean(4, true);
            insertPs.setInt(5, rel.getEstagio().getIdEstagio());

            int insertCount = insertPs.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public List<RelatorioFinal> listarTodos() {
        String sql = "SELECT * FROM relatorio_final";
        List<RelatorioFinal> relatorios = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                RelatorioFinal relatorio = new RelatorioFinal();
                relatorio.setIdRelatorio(getInt(rs, "id_relatorio", "id_relatorio_final"));
                relatorio.setArquivo(rs.getString("arquivo"));
                relatorio.setTitulo(rs.getString("titulo"));
                relatorio.setAssinaturaDiscente(rs.getBoolean("assinatura_discente"));
                relatorio.setAssinaturaSupervisor(rs.getBoolean("assinatura_supervisor"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return relatorios;
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
