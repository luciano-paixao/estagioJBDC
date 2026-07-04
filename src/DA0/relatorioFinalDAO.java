package entity;

public class relatorioFinalDAO {
    public void cadastrarRelatorioFinal(RelatorioFinal rel) {
        String sql = "INSERT INTO plano_atividade(arquivo, titulo, assinatura_discente, assinatura_supervisor, id_estagio) VALUES(" +
                    "?, ?, ?, ?, ?" +
                    ")";
        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {
            insertPs.setString(1, rel.arquivo);
            insertPs.setString(2, rel.titulo);
            insertPs.setBoolean(3, true);
            insertPs.setBoolean(4, true);
            insertPs.setInt(5, rel.idEstagio);

            int insertCount = insertPs.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
