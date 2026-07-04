package entity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.concedente.Concedente;

public class concedenteDAO {
    public void cadastrarConcedente(Concedente c) {
        String sql = "INSERT INTO concedente(nome, cnpj, tipo, area_atuacao) VALUES (" +
                "?, ?, ?, ?" +
                ")";

        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {

            insertPs.setString(1, c.getNome());
            insertPs.setString(2, c.getCnpj());
            insertPs.setString(3, c.getTipo());

            // Atenção: se area de atuacao for texto no banco, mude setDate para setString
            insertPs.setDate(4, c.getAreaAtuacao());

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}