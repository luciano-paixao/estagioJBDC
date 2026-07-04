package entity;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.planoAtividade.PlanoAtividade;

public class planoAtividadeDAO {
    public void cadastrarPlanoAtividade(PlanoAtividade pl) {
        String sql = "INSERT INTO plano_atividade(arquivo, id_termo_compromisso) VALUES(" +
                "?, ?" +
                ")";
        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {

            insertPs.setString(1, pl.getArquivo());
            // Corrigido: antes estava repetindo pl.arquivo para o id
            insertPs.setInt(2, pl.getIdTermoCompromisso());

            int insertCount = insertPs.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}