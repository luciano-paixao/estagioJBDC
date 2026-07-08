package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/estagio_facsi";
    private static final String user = "root";
    private static final String password = "root";

    // Toda vez que o método for chamado, ele agora retorna um novo DriverManager.getConnection
    public static Connection getConexao() {
        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}