package DAO;

import database.Conexao;
import models.discente.Discente;
import models.discente.SituacaoDiscente;
import models.periodoLetivo.PeriodoLetivo;
import models.pessoa.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Discente> listarTodos() {
        String sql = "SELECT * FROM discente";
        List<Discente> discentes = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Discente discente = new Discente();
                discente.setIdDiscente(getInt(rs, "id_discente"));
                discente.setMatricula(rs.getString("matricula"));
                discente.setCurso(rs.getString("curso"));

                String situacao = rs.getString("situacao");
                if (situacao != null) {
                    discente.setSituacao(SituacaoDiscente.valueOf(situacao));
                }

                PeriodoLetivo periodoLetivo = new PeriodoLetivo();
                periodoLetivo.setIdPeriodo(getInt(rs, "id_periodo"));
                discente.setPeriodoLetivo(periodoLetivo);

                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(getInt(rs, "id_pessoa"));
                discente.setPessoa(pessoa);

                discentes.add(discente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return discentes;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
