package DAO;

import conexao.Conexao;
import models.planoAtividade.PlanoAtividade;
import models.termoCompromisso.TermoCompromisso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanoAtividadeDAO {

    public void cadastrarPlanoAtividade(PlanoAtividade plano, TermoCompromisso termo) {
        String sql = "INSERT INTO plano_atividade (id_termo_compromisso, area_atuacao, justificativa, objetivos, plano_atividades) VALUES (?, ?, ?, ?, ?)";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setInt(1, termo.getIdTermo());
            insertPs.setString(2, plano.getAreaAtuacao());
            insertPs.setString(3, plano.getJustificativa());
            insertPs.setString(4, plano.getObjetivos());
            insertPs.setString(5, plano.getPlanoAtividades());

            int insertCount = insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}