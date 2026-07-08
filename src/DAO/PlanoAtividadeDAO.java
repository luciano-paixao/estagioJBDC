package DAO;

import database.Conexao;
import models.planoAtividade.PlanoAtividade;
import models.termoCompromisso.TermoCompromisso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<PlanoAtividade> listarTodos() {
        String sql = "SELECT * FROM plano_atividade";
        List<PlanoAtividade> planos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PlanoAtividade plano = new PlanoAtividade();
                plano.setIdPlanoAtividade(getInt(rs, "id_plano_atividade", "id_plano"));
                plano.setAreaAtuacao(rs.getString("area_atuacao"));
                plano.setJustificativa(rs.getString("justificativa"));
                plano.setObjetivos(rs.getString("objetivos"));
                plano.setPlanoAtividades(rs.getString("plano_atividades"));
                planos.add(plano);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return planos;
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
