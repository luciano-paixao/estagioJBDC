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

    private int getIdDiscente(Estagio estagio) {
        return estagio.getDiscente() != null ? estagio.getDiscente().getIdDiscente() : 0;
    }

    private int getIdSupervisor(Estagio estagio) {
        return estagio.getSupervisorConcedente() != null ? estagio.getSupervisorConcedente().getIdSupervisor() : 0;
    }

    private int getIdCoordenador(Estagio estagio) {
        return estagio.getCoordenadorEstagio() != null ? estagio.getCoordenadorEstagio().getIdCoordenadorEstagio() : 0;
    }

    private int getIdConcedente(Estagio estagio) {
        return estagio.getConcedente() != null ? estagio.getConcedente().getIdConcedente() : 0;
    }

    private int getIdPeriodo(Estagio estagio) {
        return estagio.getPeriodoLetivo() != null ? estagio.getPeriodoLetivo().getIdPeriodo() : 0;
    }

    // =========================================================================
    // MÉTODOS DE CONSULTAS E RELATÓRIOS (DASHBOARD)
    // =========================================================================

    public List<Object[]> discentesComEstagioAtivo() {
        String sql = "SELECT p.nome AS discente, d.matricula, d.curso, " +
                "c.nome AS empresa, pl.ano, pl.periodo " +
                "FROM discente d " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN estagio e ON e.id_discente = d.id_discente " +
                "JOIN concedente c ON c.idconcedente = e.idconcedente " +
                "JOIN periodo_letivo pl ON pl.id_periodo = e.id_periodo " +
                "WHERE e.status = 'EM_ANDAMENTO' " +
                "ORDER BY p.nome";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> quantidadeEstagiosPorEmpresa() {
        String sql = "SELECT c.nome AS empresa, c.area_atuacao AS area, COUNT(e.id_estagio) AS totalEstagios " +
                "FROM concedente c " +
                "JOIN estagio e ON e.idconcedente = c.idconcedente " +
                "GROUP BY c.idconcedente, c.nome, c.area_atuacao " +
                "ORDER BY totalEstagios DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> remuneracaoEAuxilioPorDiscente() {
        String sql = "SELECT p.nome AS discente, d.matricula, d.curso, " +
                "SUM(tc.remuneracao) AS totalRemuneracao, SUM(tc.aux_transporte) AS totalAuxTransporte, " +
                "ROUND(AVG(tc.horas_semanais), 1) AS mediaHorasSemanais " +
                "FROM discente d " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN estagio e ON e.id_discente = d.id_discente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY d.id_discente, p.nome, d.matricula, d.curso " +
                "ORDER BY totalRemuneracao DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> cargaHorariaPorCurso() {
        String sql = "SELECT d.curso AS curso, MAX(tc.horas_totais) AS maiorCargaHoraria, " +
                "MIN(tc.horas_totais) AS menorCargaHoraria, ROUND(AVG(tc.horas_totais), 0) AS mediaCargaHoraria, " +
                "COUNT(e.id_estagio) AS totalEstagios " +
                "FROM discente d " +
                "JOIN estagio e ON e.id_discente = d.id_discente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY d.curso " +
                "ORDER BY d.curso";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> estagiosAtivosComMaisDeUmDiscentePorPeriodo() {
        String sql = "SELECT d.curso, pl.ano, pl.periodo, " +
                "COUNT(e.id_estagio) AS discentesEmEstagio " +
                "FROM estagio e " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "JOIN periodo_letivo pl ON pl.id_periodo = e.id_periodo " +
                "WHERE e.status = 'EM_ANDAMENTO' " +
                "GROUP BY d.curso, pl.id_periodo, pl.ano, pl.periodo " +
                "HAVING COUNT(e.id_estagio) > 1 " +
                "ORDER BY discentesEmEstagio DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> estagiosComPlanoAprovado() {
        // Correção de FK: o plano é atrelado ao termo_compromisso, não diretamente ao estágio.
        // Adicionados campos textuais genéricos para não quebrar a tela de apresentação.
        String sql = "SELECT p.nome AS discente, d.matricula, d.curso, " +
                "c.nome AS empresa, 'Aprovado' AS statusPlano, 'Sim' AS assinadoDiscente, " +
                "tc.data_inicio AS dataElaboracao " +
                "FROM estagio e " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN concedente c ON c.idconcedente = e.idconcedente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "JOIN plano_atividade pa ON pa.id_termo_compromisso = tc.id_termo " +
                "ORDER BY p.nome";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> discentesSemEstagio() {
        String sql = "SELECT p.nome AS discente, d.matricula, d.curso, " +
                "d.situacao AS situacaoAcademica, p.email " +
                "FROM discente d " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "LEFT JOIN estagio e ON e.id_discente = d.id_discente " +
                "WHERE e.id_estagio IS NULL " +
                "ORDER BY d.curso, p.nome";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> cursosComMaisDeDoisDiscentesPorPeriodo() {
        String sql = "SELECT d.curso, pl.ano, pl.periodo, " +
                "COUNT(e.id_estagio) AS discentesEmEstagio " +
                "FROM estagio e " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "JOIN periodo_letivo pl ON pl.id_periodo = e.id_periodo " +
                "WHERE e.status = 'EM_ANDAMENTO' " +
                "GROUP BY d.curso, pl.id_periodo, pl.ano, pl.periodo " +
                "HAVING COUNT(e.id_estagio) > 2 " +
                "ORDER BY discentesEmEstagio DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> estagiosCursosTI() {
        String sql = "SELECT p.nome AS discente, d.matricula, d.curso, " +
                "c.nome AS empresa, e.tipo AS tipoEstagio, e.status AS situacao, " +
                "pl.ano, pl.periodo " +
                "FROM estagio e " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN concedente c ON c.idconcedente = e.idconcedente " +
                "JOIN periodo_letivo pl ON pl.id_periodo = e.id_periodo " +
                "WHERE d.curso IN ('Sistemas de Informação', 'Engenharia de Software', 'Ciencia da Computacao') " +
                "ORDER BY d.curso, p.nome";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> discentesComRemuneracaoAcimaDaMedia() {
        String sql = "SELECT p.nome AS discente, d.curso, c.nome AS empresa, " +
                "tc.remuneracao, tc.horas_semanais " +
                "FROM discente d " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN estagio e ON e.id_discente = d.id_discente " +
                "JOIN concedente c ON c.idconcedente = e.idconcedente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "WHERE tc.remuneracao > (SELECT AVG(remuneracao) FROM termo_compromisso) " +
                "ORDER BY tc.remuneracao DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> supervisoresComMaisDeUmEstagiario() {
        String sql = "SELECT p.nome AS supervisor, c.nome AS empresa, COUNT(e.id_estagio) AS totalEstagiarios " +
                "FROM supervisor_concedente sc " +
                "JOIN pessoa p ON p.id_pessoa = sc.id_pessoa " +
                "JOIN concedente c ON c.idconcedente = sc.id_concedente " +
                "JOIN estagio e ON e.id_supervisor = sc.id_supervisor " +
                "GROUP BY sc.id_supervisor, p.nome, c.nome " +
                "HAVING COUNT(e.id_estagio) > 1 " +
                "ORDER BY totalEstagiarios DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> empresasComMaisDe500Horas() {
        String sql = "SELECT c.nome AS empresa, c.area_atuacao AS area, COUNT(e.id_estagio) AS totalEstagios, " +
                "SUM(tc.horas_totais) AS horasContratadas " +
                "FROM concedente c " +
                "JOIN estagio e ON e.idconcedente = c.idconcedente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY c.idconcedente, c.nome, c.area_atuacao " +
                "HAVING SUM(tc.horas_totais) > 500 " +
                "ORDER BY horasContratadas DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> cursosComRemuneracaoMediaAcimaDe600() {
        String sql = "SELECT d.curso, COUNT(e.id_estagio) AS totalEstagios, " +
                "ROUND(AVG(tc.remuneracao), 2) AS remuneracaoMedia, " +
                "ROUND(AVG(tc.horas_semanais), 1) AS mediaHorasSemanais " +
                "FROM discente d " +
                "JOIN estagio e ON e.id_discente = d.id_discente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY d.curso " +
                "HAVING AVG(tc.remuneracao) > 600 " +
                "ORDER BY remuneracaoMedia DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> periodosComMaisDeDoisEstagios() {
        String sql = "SELECT pl.ano, pl.periodo, COUNT(e.id_estagio) AS totalEstagios, " +
                "COUNT(DISTINCT d.curso) AS cursosEnvolvidos " +
                "FROM periodo_letivo pl " +
                "JOIN estagio e ON e.id_periodo = pl.id_periodo " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "GROUP BY pl.id_periodo, pl.ano, pl.periodo " +
                "HAVING COUNT(e.id_estagio) > 2 " +
                "ORDER BY pl.ano DESC, pl.periodo DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> somaCargaHorariaPorEmpresa() {
        String sql = "SELECT c.nome AS empresa, SUM(tc.horas_totais) AS totalHorasEstagio " +
                "FROM concedente c " +
                "JOIN estagio e ON e.idconcedente = c.idconcedente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY c.idconcedente, c.nome " +
                "ORDER BY totalHorasEstagio DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> maiorCargaHorariaContratada() {
        String sql = "SELECT p.nome AS discente, d.matricula, c.nome AS empresa, " +
                "e.tipo AS tipoEstagio, MAX(tc.horas_totais) AS maiorCargaHoraria " +
                "FROM estagio e " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN concedente c ON c.idconcedente = e.idconcedente " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY p.nome, d.matricula, c.nome, e.tipo " +
                "ORDER BY maiorCargaHoraria DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> menorRemuneracaoPorTermo() {
        String sql = "SELECT p.nome AS discente, d.matricula, c.nome AS empresa, " +
                "tc.id_termo AS numeroTermo, MIN(tc.remuneracao) AS menorRemuneracao " +
                "FROM termo_compromisso tc " +
                "JOIN estagio e ON e.id_estagio = tc.id_estagio " +
                "JOIN discente d ON d.id_discente = e.id_discente " +
                "JOIN pessoa p ON p.id_pessoa = d.id_pessoa " +
                "JOIN concedente c ON c.idconcedente = e.idconcedente " +
                "GROUP BY p.nome, d.matricula, c.nome, tc.id_termo " +
                "ORDER BY menorRemuneracao ASC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> somaAuxilioTransportePorPeriodo() {
        String sql = "SELECT pl.ano, pl.periodo, " +
                "SUM(tc.aux_transporte) AS totalAuxTransporte, SUM(tc.remuneracao) AS totalRemuneracao " +
                "FROM termo_compromisso tc " +
                "JOIN estagio e ON e.id_estagio = tc.id_estagio " +
                "JOIN periodo_letivo pl ON pl.id_periodo = e.id_periodo " +
                "GROUP BY pl.id_periodo, pl.ano, pl.periodo " +
                "ORDER BY pl.ano DESC, pl.periodo DESC";

        return executarConsultaParaTabela(sql);
    }

    public List<Object[]> cargaHorariaPorTipoEstagio() {
        String sql = "SELECT e.tipo AS tipoEstagio, MAX(tc.horas_totais) AS maiorCargaTotal, " +
                "MIN(tc.horas_totais) AS menorCargaTotal, ROUND(AVG(tc.horas_totais), 0) AS mediaCargaTotal " +
                "FROM estagio e " +
                "JOIN termo_compromisso tc ON tc.id_estagio = e.id_estagio " +
                "GROUP BY e.tipo " +
                "ORDER BY e.tipo";

        return executarConsultaParaTabela(sql);
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

    public List<Object[]> executarConsultaParaTabela(String sql) {
        List<Object[]> resultados = new ArrayList<>();

        try (Connection con = Conexao.getConexao();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            // Descobre quantas colunas o seu SELECT retornou dinamicamente!
            int colunas = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                // Cria um array do tamanho exato de colunas
                Object[] linha = new Object[colunas];
                for (int i = 0; i < colunas; i++) {
                    linha[i] = rs.getObject(i + 1); // Pega o dado do banco
                }
                resultados.add(linha); // Adiciona a linha na lista final
            }
        } catch (SQLException e) {
            System.out.println("Erro ao executar consulta para a tabela: " + e.getMessage());
        }
        return resultados;
    }

}
