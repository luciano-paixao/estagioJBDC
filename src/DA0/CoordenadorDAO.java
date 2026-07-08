package DAO;

import conexao.Conexao;
import models.coordenadorEstagio.CoordenadorEstagio; // Ajuste o import conforme o pacote da sua model
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CoordenadorDAO {

    public void cadastrarCoordenador(CoordenadorEstagio coor) {

        String sql = "INSERT INTO coordenador_estagio (numero_portaria, data_inicio_vigencia, data_fim_vigencia, id_docente) VALUES (?, ?, ?, ?)";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setString(1, coor.getNumeroPortaria());

            insertPs.setDate(2, java.sql.Date.valueOf(coor.getDataInicioVigencia()));
            insertPs.setDate(3, java.sql.Date.valueOf(coor.getDataFimVigencia()));

            insertPs.setInt(4, coor.getIdDocente());

            int insertCount = insertPs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}