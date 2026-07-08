package DAO;

import conexao.Conexao;
import models.endereco.Endereco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDAO {

    public void cadastrarEndereco(Endereco endereco) {
        String sql = "INSERT INTO endereco (logradouro, estado, cidade, bairro, numero, cep, complemento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, endereco.getLogradouro());
            ps.setString(2, endereco.getEstado());
            ps.setString(3, endereco.getCidade());
            ps.setString(4, endereco.getBairro());
            ps.setInt(5, endereco.getNumero());
            ps.setString(6, endereco.getCep());
            ps.setString(7, endereco.getComplemento());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Endereco> listarTodos() {
        String sql = "SELECT * FROM endereco";
        List<Endereco> enderecos = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(getInt(rs, "id_endereco"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setEstado(rs.getString("estado"));
                endereco.setCidade(rs.getString("cidade"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setNumero(getInt(rs, "numero"));
                endereco.setCep(rs.getString("cep"));
                endereco.setComplemento(rs.getString("complemento"));
                enderecos.add(endereco);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enderecos;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
