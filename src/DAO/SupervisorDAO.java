package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Conexao;
import models.concedente.Concedente;
import models.pessoa.Pessoa;
import models.supervisorConcedente.SupervisorConcedente;

public class SupervisorDAO {
    public void salvar(Concedente c, Pessoa p, String registroProfissional) {
        String sql = "INSERT INTO supervisor(id_concedente, id_pessoa, registro_profissional) VALUES (" +
                "?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setInt(1,c.getIdConcedente());
            insertPs.setInt(2, p.getIdPessoa());
            insertPs.setString(3, registroProfissional);

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<SupervisorConcedente> listarTodos() {
        String sql = "SELECT * FROM supervisor";
        List<SupervisorConcedente> supervisores = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SupervisorConcedente supervisor = new SupervisorConcedente();
                supervisor.setIdSupervisor(getInt(rs, "id_supervisor", "id_supervisor_concedente"));
                supervisor.setRegistroProfissional(rs.getString("registro_profissional"));

                Concedente concedente = new Concedente();
                concedente.setIdConcedente(getInt(rs, "id_concedente"));
                supervisor.setConcedente(concedente);

                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(getInt(rs, "id_pessoa"));
                supervisor.setPessoa(pessoa);

                supervisores.add(supervisor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return supervisores;
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
