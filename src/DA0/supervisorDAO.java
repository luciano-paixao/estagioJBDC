package entity;

public class supervisorDAO {
        public void cadastrarSupervisor(Pessoa p, String registroProfissional) {
        String sql = "INSERT INTO supervisor(id_concedente, id_pessoa, registro_profissional) VALUES (" +
                "?, ?, ?" +
                ")";

        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {

            insertPs.setInt(1, p.idConcedente);
            insertPs.setInt(2, p.id_pessoa);
            insertPs.setString(3, registroProfissional);

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
