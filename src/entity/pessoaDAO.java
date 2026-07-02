package entity;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class pessoaDAO {
    public void cadastrarPessoa(String nome , String cpf, String email,
                                String dataNasc, String sexo, String cargo) {
        String sql = "INSERT INTO pessoa(nome, cpf, email, data_nascimento, sexo, cargo) VALUES (" +
                "?, ?, ?, ?, ?, ?" +
                ")";

        try {
            PreparedStatement insertPs = conexao.prepareStatement(sql);

            insertPs.setString(1, nome);
            insertPs.setString(2, cpf);
            insertPs.setString(3, email);
            insertPs.setDate(4, java.sql.Date.valueOf(dataNasc));
            insertPs.setString(5, sexo);
            insertPs.setString(6, cargo);

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
