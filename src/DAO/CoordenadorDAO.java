package DAO;

import database.Conexao;

import models.coordenadorEstagio.CoordenadorEstagio;
import models.docente.Docente;
import models.pessoa.Pessoa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoordenadorDAO {

    public void cadastrarCoordenador(CoordenadorEstagio coor) {

        String sql = "INSERT INTO coordenador_estagio (numero_portaria, data_inicio_vigencia, data_fim_vigencia, id_docente) VALUES (?, ?, ?, ?)";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setString(1, coor.getNumeroPortaria());

            insertPs.setDate(2, java.sql.Date.valueOf(coor.getDataInicioVigencia()));
            insertPs.setDate(3, java.sql.Date.valueOf(coor.getDataFimVigencia()));

            insertPs.setInt(4, coor.getDocente().getIdDocente());

            int insertCount = insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CoordenadorEstagio> listarTodos() {
        List<CoordenadorEstagio> lista = new ArrayList<>();

        // JOIN para pegar o Coordenador, o Docente e a Pessoa de uma vez só
        String sql = "SELECT c.id_coordenador_estagio, c.numero_portaria, " +
                "d.id_docente, d.matricula_siape, " +
                "p.id_pessoa, p.nome " +
                "FROM coordenador_estagio c " +
                "INNER JOIN docente d ON c.id_docente = d.id_docente " +
                "INNER JOIN pessoa p ON d.id_pessoa = p.id_pessoa";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                // 1. Instancia a boneca maior
                CoordenadorEstagio coord = new CoordenadorEstagio();
                coord.setIdCoordenadorEstagio(rs.getInt("id_coordenador_estagio"));
                coord.setNumeroPortaria(rs.getString("numero_portaria"));

                // 2. Instancia a boneca do meio
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setMatriculaSiape(rs.getString("matricula_siape"));

                // 3. Instancia a boneca menor
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(rs.getInt("id_pessoa"));
                pessoa.setNome(rs.getString("nome"));

                // 4. Guarda as bonecas umas dentro das outras!
                docente.setPessoa(pessoa);
                coord.setDocente(docente);

                // Adiciona na lista final
                lista.add(coord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
