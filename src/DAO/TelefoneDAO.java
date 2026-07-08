package DAO;

import conexao.Conexao;
import models.concedente.Concedente;
import models.pessoa.Pessoa;
import models.telefone.Telefone;
import models.telefone.TipoTelefone;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TelefoneDAO {

    public void cadastrarTelefone(Telefone telefone) {
        String sql = "INSERT INTO telefone (ddd, numero, tipo, id_concedente, id_pessoa) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, telefone.getDdd());
            ps.setString(2, telefone.getNumero());
            ps.setString(3, telefone.getTipo().toString());
            ps.setInt(4, telefone.getConcedente().getIdConcedente());
            ps.setInt(5, telefone.getPessoa().getIdPessoa());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Telefone> listarTodos() {
        String sql = "SELECT * FROM telefone";
        List<Telefone> telefones = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Telefone telefone = new Telefone();
                telefone.setIdTelefone(getInt(rs, "id_telefone"));
                telefone.setDdd(rs.getString("ddd"));
                telefone.setNumero(rs.getString("numero"));

                String tipo = rs.getString("tipo");
                if (tipo != null) {
                    telefone.setTipo(TipoTelefone.valueOf(tipo));
                }

                Concedente concedente = new Concedente();
                concedente.setIdConcedente(getInt(rs, "id_concedente"));
                telefone.setConcedente(concedente);

                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(getInt(rs, "id_pessoa"));
                telefone.setPessoa(pessoa);

                telefones.add(telefone);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return telefones;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
