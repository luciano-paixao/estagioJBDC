package DAO;

import database.Conexao;
import models.endereco.Endereco;
import models.estagio.Estagio;
import models.termoCompromisso.HorasSemanais;
import models.termoCompromisso.TermoCompromisso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TermoCompromissoDAO {

    public void salvar(TermoCompromisso termo, Estagio es, Endereco en) {
        String sql = "INSERT INTO termo_compromisso (horas_semanais, horas_totais, remuneracao, aux_transporte, assinatura_discente, assinatura_coodenadador, assinatura_concedente, data_inicio, data_fim, data_recesso, id_estagio, id_endereco_instituicao) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setInt(1, termo.getHorasSemanais().getHoras());
            insertPs.setInt(2, termo.getHorasTotais());
            insertPs.setDouble(3, termo.getRemuneracao());
            insertPs.setDouble(4, termo.getAuxTransporte());
            insertPs.setBoolean(5, true);
            insertPs.setBoolean(6, true);
            insertPs.setBoolean(7, true);

            insertPs.setDate(8, java.sql.Date.valueOf(termo.getDataInicio()));
            insertPs.setDate(9, java.sql.Date.valueOf(termo.getDataFim()));
            insertPs.setDate(10, java.sql.Date.valueOf(termo.getDataRecesso()));

            insertPs.setInt(11, es.getIdEstagio());
            insertPs.setInt(12, en.getIdEndereco());

            int insertCount = insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<TermoCompromisso> listarTodos() {
        String sql = "SELECT * FROM termo_compromisso";
        List<TermoCompromisso> termos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                TermoCompromisso termo = new TermoCompromisso();
                termo.setIdTermo(getInt(rs, "id_termo_compromisso", "id_termo"));
                termo.setHorasSemanais(HorasSemanais.fromHoras(getInt(rs, "horas_semanais")));
                termo.setHorasTotais(getInt(rs, "horas_totais"));
                termo.setRemuneracao(rs.getDouble("remuneracao"));
                termo.setAuxTransporte(rs.getDouble("aux_transporte"));
                termo.setAssinaturaDiscente(rs.getBoolean("assinatura_discente"));
                termo.setAssinaturaCoordenador(rs.getBoolean("assinatura_coodenadador"));
                termo.setAssinaturaConcedente(rs.getBoolean("assinatura_concedente"));

                java.sql.Date inicio = rs.getDate("data_inicio");
                if (inicio != null) {
                    termo.setDataInicio(inicio.toLocalDate());
                }

                java.sql.Date fim = rs.getDate("data_fim");
                if (fim != null) {
                    termo.setDataFim(fim.toLocalDate());
                }

                java.sql.Date recesso = rs.getDate("data_recesso");
                if (recesso != null) {
                    termo.setDataRecesso(recesso.toLocalDate());
                }

                Estagio estagio = new Estagio();
                estagio.setIdEstagio(getInt(rs, "id_estagio"));
                termo.setEstagio(estagio);

                termos.add(termo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return termos;
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
