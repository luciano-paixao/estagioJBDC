package entity;

import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.pessoa.Pessoa;

public class pessoaDAO {
    public void cadastrarPessoa(Pessoa p) {
        String sql = "INSERT INTO pessoa(nome, cpf, email, data_nascimento, sexo, cargo) VALUES (" +
                "?, ?, ?, ?, ?, ?" +
                ")";

        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {

            insertPs.setString(1, p.getNome());
            insertPs.setString(2, p.getCpf());
            insertPs.setString(3, p.getEmail());
            insertPs.setDate(4, java.sql.Date.valueOf(p.getDataNasc()));
            insertPs.setString(5, p.getSexo());
            insertPs.setString(6, p.getCargo());

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}