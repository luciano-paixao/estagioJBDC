package entity;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pessoaDAO {
    public void cadastrarPessoa(Pessoa p) {
        String sql = "INSERT INTO pessoa(nome, cpf, email, data_nascimento, sexo, cargo) VALUES (" +
                "?, ?, ?, ?, ?, ?" +
                ")";

        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {

            insertPs.setString(1, p.nome);
            insertPs.setString(2, p.cpf);
            insertPs.setString(3, p.email);
            insertPs.setDate(4, java.sql.Date.valueOf(p.dataNasc));
            insertPs.setString(5, p.sexo);
            insertPs.setString(6, p.cargo);

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
