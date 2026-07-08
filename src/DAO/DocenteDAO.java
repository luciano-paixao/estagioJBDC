package DAO; // Corrigi o DA0 (com zero) para dao

import database.Conexao;
import models.docente.Docente; // Importe o seu model de Docente aqui
import models.docente.Titulacao;
import models.pessoa.Pessoa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocenteDAO {

    public void salvar(Docente docente) {

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

    public List<Docente> listarTodos() {
        String sql = "SELECT * FROM docente";
        List<Docente> docentes = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(getInt(rs, "id_docente"));
                docente.setMatriculaSiape(rs.getString("matricula_siape"));
                docente.setDepartamento(rs.getString("departamento"));

                String titulacao = rs.getString("titulacao");
                if (titulacao != null) {
                    docente.setTitulacao(Titulacao.valueOf(titulacao));
                }

                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(getInt(rs, "id_pessoa"));
                docente.setPessoa(pessoa);

                docentes.add(docente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return docentes;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
