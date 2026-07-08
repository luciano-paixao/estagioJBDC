package DAO;

import database.Conexao;
import models.coordenadorEstagio.CoordenadorEstagio; // Ajuste o import conforme o pacote da sua model
import models.docente.Docente;
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
        String sql = "SELECT * FROM coordenador_estagio";
        List<CoordenadorEstagio> coordenadores = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CoordenadorEstagio coordenador = new CoordenadorEstagio();
                coordenador.setIdCoordenadorEstagio(getInt(rs, "id_coordenador_estagio"));
                coordenador.setNumeroPortaria(rs.getString("numero_portaria"));

                java.sql.Date inicio = rs.getDate("data_inicio_vigencia");
                if (inicio != null) {
                    coordenador.setDataInicioVigencia(inicio.toLocalDate());
                }

                java.sql.Date fim = rs.getDate("data_fim_vigencia");
                if (fim != null) {
                    coordenador.setDataFimVigencia(fim.toLocalDate());
                }

                Docente docente = new Docente();
                docente.setIdDocente(getInt(rs, "id_docente"));
                coordenador.setDocente(docente);
                coordenadores.add(coordenador);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return coordenadores;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
