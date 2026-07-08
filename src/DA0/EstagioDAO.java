package DA0;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EstagioDAO {

    //exemplo de metodo de consulta
    public void empresasComMaisEstagiarios() {
        String sql = "SELECT c.razaoSocial, COUNT(*) AS totalEstagiarios " +
                "FROM Estagio e " +
                "JOIN Concedente c ON e.idConcedente = c.idConcedente " +
                "GROUP BY c.razaoSocial " +
                "ORDER BY totalEstagiarios DESC";

        executarEImprimir(sql, "Empresas com mais estagiários vinculados");
    }
    // exemplo de metodo de consuDAlta que recebe um valor /CLAUDE PASSOU POR AQUI...
    public void alunosPorEmpresa(String razaoSocial) {
        String sql = "SELECT p.nome, p.sobrenome, e.statusEstagio " +
                "FROM Estagio e " +
                "JOIN Discente d ON e.idDiscente = d.idDiscente " +
                "JOIN Pessoa p ON d.idPessoa = p.idPessoa " +
                "JOIN Concedente c ON e.idConcedente = c.idConcedente " +
                "WHERE c.razaoSocial LIKE ?";

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + razaoSocial + "%");
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\n--- Alunos que estagiam em: " + razaoSocial + " ---");
                while (rs.next()) {
                    System.out.printf("%s %s - Status: %s%n",
                            rs.getString("nome"), rs.getString("sobrenome"), rs.getString("statusEstagio"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao consultar alunos por empresa: " + e.getMessage());
        }
    }


    private void executarEImprimir(String sql, String titulo) {
        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n--- " + titulo + " ---");
            int colunas = rs.getMetaData().getColumnCount();
            boolean vazio = true;

            while (rs.next()) {
                vazio = false;
                StringBuilder linha = new StringBuilder();
                for (int i = 1; i <= colunas; i++) {
                    linha.append(rs.getMetaData().getColumnLabel(i))
                            .append(": ").append(rs.getString(i));
                    if (i < colunas) linha.append(" | ");
                }
                System.out.println(linha);
            }
            if (vazio) {
                System.out.println("(nenhum registro encontrado)");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta [" + titulo + "]: " + e.getMessage());
        }
    }

}
