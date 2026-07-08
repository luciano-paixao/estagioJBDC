package DAO;

import conexao.Conexao;
import models.discente.Discente;
import models.periodoLetivo.PeriodoLetivo;
import models.pessoa.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DiscenteDAO {
    public void cadastrarDiscente(Discente d, PeriodoLetivo per, Pessoa p) {
        String sql = "INSERT INTO discente(matricula, situacao, curso, id_periodo, id_pessoa) VALUES (" +
                "?, ?, ?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setString(1, d.getMatricula());
            insertPs.setString(2, d.getSituacao().toString());
            insertPs.setString(3, d.getCurso());
            insertPs.setInt(4, per.getIdPeriodo());
            insertPs.setInt(5, p.getIdPessoa());

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}