package DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.Conexao;
import models.concedente.Concedente;
import models.concedente.TipoConcedente;
import models.endereco.Endereco;
import models.pessoa.Pessoa;

public class ConcedenteDAO {
    public void salvar(Concedente c, Endereco en, Pessoa p) {
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

    public List<Concedente> listarTodos() {
        String sql = "SELECT * FROM concedente";
        List<Concedente> concedentes = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Concedente concedente = new Concedente();
                concedente.setIdConcedente(getInt(rs, "id_concedente"));
                concedente.setNome(rs.getString("nome"));
                concedente.setCnpj(rs.getString("cnpj"));

                String tipo = rs.getString("tipo");
                if (tipo != null) {
                    concedente.setTipo(TipoConcedente.valueOf(tipo));
                }

                concedente.setAreaAtuacao(rs.getString("area_atuacao"));
                concedente.setEndereco(getEnderecoRef(rs));
                concedente.setRepresentante(getPessoaRef(rs));
                concedentes.add(concedente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return concedentes;
    }

    private Pessoa getPessoaRef(ResultSet rs) throws SQLException {
        Pessoa pessoa = new Pessoa();
        pessoa.setIdPessoa(getInt(rs, "id_representante"));
        return pessoa;
    }

    private Endereco getEnderecoRef(ResultSet rs) throws SQLException {
        Endereco endereco = new Endereco();
        endereco.setIdEndereco(getInt(rs, "id_endereco"));
        return endereco;
    }

    private int getInt(ResultSet rs, String columnName) throws SQLException {
        try {
            return rs.getInt(columnName);
        } catch (SQLException e) {
            return 0;
        }
    }
}
