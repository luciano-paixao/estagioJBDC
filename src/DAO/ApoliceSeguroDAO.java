package DAO;

import database.Conexao;
import models.apoliceSeguro.ApoliceSeguro;
import models.apoliceSeguro.ResponsavelContratacao;
import models.estagio.Estagio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApoliceSeguroDAO {

    public void salvarApoliceSeguro(ApoliceSeguro apolice) {
        String sql = "INSERT INTO apolice_seguro " +
                "(numero, responsavel_contratacao, seguradora, data_inicio, data_fim, id_estagio) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, apolice.getNumero());
            ps.setString(2, apolice.getResponsavelContratacao().toString());
            ps.setString(3, apolice.getSeguradora());
            ps.setDate(4, java.sql.Date.valueOf(apolice.getDataInicio()));
            ps.setDate(5, java.sql.Date.valueOf(apolice.getDataFim()));
            ps.setInt(6, apolice.getEstagio().getIdEstagio());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ApoliceSeguro> listarTodos() {
        String sql = "SELECT * FROM apolice_seguro";
        List<ApoliceSeguro> apolices = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ApoliceSeguro apolice = new ApoliceSeguro();
                apolice.setIdApolice(getInt(rs, "id_apolice"));
                apolice.setNumero(rs.getString("numero"));

                String responsavel = rs.getString("responsavel_contratacao");
                if (responsavel != null) {
                    apolice.setResponsavelContratacao(ResponsavelContratacao.valueOf(responsavel));
                }

                apolice.setSeguradora(rs.getString("seguradora"));

                java.sql.Date inicio = rs.getDate("data_inicio");
                if (inicio != null) {
                    apolice.setDataInicio(inicio.toLocalDate());
                }

                java.sql.Date fim = rs.getDate("data_fim");
                if (fim != null) {
                    apolice.setDataFim(fim.toLocalDate());
                }

                Estagio estagio = new Estagio();
                estagio.setIdEstagio(getInt(rs, "id_estagio"));
                apolice.setEstagio(estagio);

                apolices.add(apolice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return apolices;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
