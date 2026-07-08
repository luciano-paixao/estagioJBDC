package DAO;

import database.Conexao;
import models.concedente.Concedente;
import models.coordenadorEstagio.CoordenadorEstagio;
import models.discente.Discente;
import models.estagio.AmbitoEstagio;
import models.estagio.Estagio;
import models.estagio.StatusEstagio;
import models.estagio.TipoEstagio;
import models.periodoLetivo.PeriodoLetivo;
import models.supervisorConcedente.SupervisorConcedente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Estagio> listarTodos() {
        String sql = "SELECT * FROM estagio";
        List<Estagio> estagios = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Estagio estagio = new Estagio();
                estagio.setIdEstagio(getInt(rs, "id_estagio"));

                String tipo = rs.getString("tipo");
                if (tipo != null) {
                    estagio.setTipo(TipoEstagio.valueOf(tipo));
                }

                String ambito = rs.getString("ambito");
                if (ambito != null) {
                    estagio.setAmbito(AmbitoEstagio.valueOf(ambito));
                }

                String status = rs.getString("status");
                if (status != null) {
                    estagio.setStatus(StatusEstagio.valueOf(status));
                }

                estagio.setObservacoes(rs.getString("observacoes"));

                Discente discente = new Discente();
                discente.setIdDiscente(getInt(rs, "id_discente"));
                estagio.setDiscente(discente);

                SupervisorConcedente supervisor = new SupervisorConcedente();
                supervisor.setIdSupervisor(getInt(rs, "id_supervisor", "id_supervisor_concedente"));
                estagio.setSupervisorConcedente(supervisor);

                CoordenadorEstagio coordenador = new CoordenadorEstagio();
                coordenador.setIdCoordenadorEstagio(getInt(rs, "id_coordenador_estagio", "id_coordenador"));
                estagio.setCoordenadorEstagio(coordenador);

                Concedente concedente = new Concedente();
                concedente.setIdConcedente(getInt(rs, "id_concedente"));
                estagio.setConcedente(concedente);

                PeriodoLetivo periodoLetivo = new PeriodoLetivo();
                periodoLetivo.setIdPeriodo(getInt(rs, "id_periodo", "id_periodo_letivo"));
                estagio.setPeriodoLetivo(periodoLetivo);

                estagios.add(estagio);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return estagios;
    }

    private int getInt(ResultSet rs, String... columnNames) throws SQLException {
        for (String columnName : columnNames) {
            try {
                return rs.getInt(columnName);
            } catch (SQLException ignored) {
            }
        }
        return 0;
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
