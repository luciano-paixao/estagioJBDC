package DA0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import models.planoAtividade.PlanoAtividade;

public class planoAtividadeDAO {
    public void cadastrarPlanoAtividade(PlanoAtividade pl) {
        String sql = "INSERT INTO plano_atividade(arquivo, id_termo_compromisso) VALUES(" +
                "?, ?" +
                ")";
        try(Connection con = Conexao.getConexao()) {

            PreparedStatement insertPs = con.prepareStatement(sql);

            insertPs.setString(1, pl.getArquivo());
            // Corrigido: antes estava repetindo pl.arquivo para o id
            insertPs.setInt(2, pl.getTermoCompromisso().getIdTermo());

            int insertCount = insertPs.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}