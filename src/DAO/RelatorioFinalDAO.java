package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import models.relatorioFinal.RelatorioFinal;

public class RelatorioFinalDAO {
    public void cadastrarRelatorioFinal(RelatorioFinal rel) {
        String sql = "INSERT INTO plano_atividade(arquivo, titulo, assinatura_discente, assinatura_supervisor, id_estagio) VALUES(" +
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
}