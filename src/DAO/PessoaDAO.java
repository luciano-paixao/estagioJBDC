package DAO;

import database.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.pessoa.Pessoa;
import models.pessoa.Sexo;

public class PessoaDAO {
    public void cadastrarPessoa(Pessoa p) {
        String sql = "INSERT INTO pessoa(nome, cpf, email, data_nascimento, sexo, cargo) VALUES (" +
                "?, ?, ?, ?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setString(1, p.getNome());
            insertPs.setString(2, p.getCpf());
            insertPs.setString(3, p.getEmail());
            insertPs.setDate(4, java.sql.Date.valueOf(p.getDataNascimento()));
            insertPs.setString(5, p.getSexo().toString());
            insertPs.setString(6, p.getCargo());

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Pessoa> listarTodos() {
        String sql = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(getInt(rs, "id_pessoa"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setEmail(rs.getString("email"));

                java.sql.Date nascimento = rs.getDate("data_nascimento");
                if (nascimento != null) {
                    pessoa.setDataNascimento(nascimento.toLocalDate());
                }

                String sexo = rs.getString("sexo");
                if (sexo != null) {
                    pessoa.setSexo(Sexo.valueOf(sexo));
                }

                pessoa.setCargo(rs.getString("cargo"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pessoas;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
