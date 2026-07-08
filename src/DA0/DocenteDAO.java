package DAO; // Corrigi o DA0 (com zero) para dao

import conexao.Conexao;
import models.docente.Docente; // Importe o seu model de Docente aqui
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DocenteDAO {

    public void cadastrarDocente(Docente docente) {

        String sql = "INSERT INTO docente(matricula_siape, departamento, titulacao, id_pessoa) VALUES (" +
                "?, ?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            // Substituindo as interrogações pelos dados do objeto
            insertPs.setString(1, docente.getMatriculaSiape());
            insertPs.setString(2, docente.getDepartamento());
            insertPs.setString(3, docente.getTitulacao().toString()); // Envia o valor do ENUM como String

            // Aqui pegamos o ID da pessoa associada ao docente
            insertPs.setInt(4, docente.getIdDocente());

            int insertCount = insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}