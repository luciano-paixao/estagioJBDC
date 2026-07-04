package entity;

import java.sql.Date;

public class concedenteDAO {
      public void cadastrarConcedente(Concedente c) {
        String sql = "INSERT INTO concedente(nome, cnpj, tipo, area_atuacao) VALUES (" +
                "?, ?, ?, ?" +
                ")";

        try(PreparedStatement insertPs = conexao.prepareStatement(sql)) {

            insertPs.setString(1, c.nome);
            insertPs.setString(2, c.cnpj);
            insertPs.setString(3, c.tipo);
            insertPs.setDate(4, c.area_atuacao);

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
