package DA0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import models.concedente.Concedente;
import models.pessoa.Pessoa;

public class supervisorDAO {
    public void cadastrarSupervisor(Concedente c, Pessoa p, String registroProfissional) {
        String sql = "INSERT INTO supervisor(id_concedente, id_pessoa, registro_profissional) VALUES (" +
                "?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao()) {

            PreparedStatement insertPs = con.prepareStatement(sql);

            insertPs.setInt(1,c.getIdConcedente());
            insertPs.setInt(2, p.getIdPessoa());
            insertPs.setString(3, registroProfissional);

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}