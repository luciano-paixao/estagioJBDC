package DA0;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import models.pessoa.Pessoa;

public class PessoaDAO {
    public void cadastrarPessoa(Pessoa p) {
        String sql = "INSERT INTO pessoa(nome, cpf, email, data_nascimento, sexo, cargo) VALUES (" +
                "?, ?, ?, ?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao()) {

            PreparedStatement insertPs = con.prepareStatement(sql);

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
}