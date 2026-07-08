package DA0;

import conexao.Conexao;
import models.periodoLetivo.PeriodoLetivo;
import models.pessoa.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscenteDAO {
    public void cadastrarDiscente(PeriodoLetivo per, Pessoa p) {
        String sql = "INSERT INTO discente(matricula, situacao, curso, id_periodo, id_pessoa) VALUES (" +
                "?, ?, ?, ?, ?" +
                ")";

        String matricula = "";
        String situacao = null ;
        String curso = "";

        try(Connection con = Conexao.getConexao()) {

            PreparedStatement insertPs = con.prepareStatement(sql);

            insertPs.setString(1, matricula);
            insertPs.setString(2, situacao);
            insertPs.setString(3, curso);
            insertPs.setInt(4, per.getIdPeriodo());
            insertPs.setInt(5, p.getIdPessoa());

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}