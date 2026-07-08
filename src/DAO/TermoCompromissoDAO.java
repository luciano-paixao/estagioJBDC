package DAO;

import conexao.Conexao;
import models.endereco.Endereco;
import models.estagio.Estagio;
import models.termoCompromisso.TermoCompromisso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TermoCompromissoDAO {

    public void cadastrarTermoCompromisso(TermoCompromisso termo, Estagio es, Endereco en) {
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
}