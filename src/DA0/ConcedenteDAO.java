package DA0;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexao.Conexao;
import models.concedente.Concedente;
import models.endereco.Endereco;
import models.pessoa.Pessoa;

public class ConcedenteDAO {
    public void cadastrarConcedente(Concedente c, Endereco en, Pessoa p) {
        String sql = "INSERT INTO concedente(nome, cnpj, tipo, area_atuacao, id_endereco, id_representante) VALUES (" +
                "?, ?, ?, ?, ?, ?" +
                ")";

        try(Connection con = Conexao.getConexao();
            PreparedStatement insertPs = con.prepareStatement(sql)) {

            insertPs.setString(1, c.getNome());
            insertPs.setString(2, c.getCnpj());
            insertPs.setString(3, c.getTipo().toString());

            // Atenção: se area de atuacao for texto no banco, mude setDate para setString
            insertPs.setString(4, c.getAreaAtuacao());
            insertPs.setInt(5, en.getIdEndereco());
            insertPs.setInt(6, p.getIdPessoa());

            int insertCount = insertPs.executeUpdate();
        }

        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}