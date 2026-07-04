package entity;

public class planoAtividadeDAO {
    public void cadastrarPlanoAtividade(PlanoAtividade pl) {
        String sql = "INSERT INTO plano_atividade(arquivo, id_termo_compromisso) VALUES(" +
                    "?, ?" +
                    ")";
        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {
            insertPs.setString(1, pl.arquivo);
            insertPs.setString(2, pl.arquivo);

            int insertCount = insertPs.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
