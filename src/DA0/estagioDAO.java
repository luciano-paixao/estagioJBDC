package DA0;

import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class estagioDAO {

//    exemplo de metodo de consuDAlta que recebe um valor
//    public void alunosPorEmpresa(String razaoSocial) {
//        String sql = "SELECT p.nome, p.sobrenome, e.statusEstagio " +
//                "FROM Estagio e " +
//                "JOIN Discente d ON e.idDiscente = d.idDiscente " +
//                "JOIN Pessoa p ON d.idPessoa = p.idPessoa " +
//                "JOIN Concedente c ON e.idConcedente = c.idConcedente " +
//                "WHERE c.razaoSocial LIKE ?";
//
//        try (Connection con = Conexao.getConexao();
//             PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setString(1, "%" + razaoSocial + "%");
//            try (ResultSet rs = ps.executeQuery()) {
//                System.out.println("\n--- Alunos que estagiam em: " + razaoSocial + " ---");
//                while (rs.next()) {
//                    System.out.printf("%s %s - Status: %s%n",
//                            rs.getString("nome"), rs.getString("sobrenome"), rs.getString("statusEstagio"));
//                }
//            }
//        } catch (SQLException e) {
//            System.out.println("Erro ao consultar alunos por empresa: " + e.getMessage());
//        }
//    }

    public void discentesComEstagioAtivo() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, d.curso AS curso, " +
                "c.nome AS empresa, pl.ano AS ano, pl.periodo AS periodo " +
                "FROM Discente d " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Estagio e ON e.idDiscente = d.idDiscente " +
                "JOIN Concedente c ON c.idConcedente = e.idConcedente " +
                "JOIN PeriodoLetivo pl ON pl.idPeriodo = e.idPeriodo " +
                "WHERE e.status = 'EM_ANDAMENTO' " +
                "ORDER BY p.nome";

        executarEImprimir(sql, "Discentes com estágio ativo");
    }


    public void quantidadeEstagiosPorEmpresa() {
        String sql = "SELECT c.nome AS empresa, c.areaAtuacao AS area, COUNT(e.idEstagio) AS totalEstagios " +
                "FROM Concedente c " +
                "JOIN Estagio e ON e.idConcedente = c.idConcedente " +
                "GROUP BY c.idConcedente, c.nome, c.areaAtuacao " +
                "ORDER BY totalEstagios DESC";

        executarEImprimir(sql, "Quantidade de estágios por empresa concedente");
    }


    public void remuneracaoEAuxilioPorDiscente() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, d.curso AS curso, " +
                "SUM(tc.remuneracao) AS totalRemuneracao, SUM(tc.auxTransporte) AS totalAuxTransporte, " +
                "ROUND(AVG(tc.horasSemanais), 1) AS mediaHorasSemanais " +
                "FROM Discente d " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Estagio e ON e.idDiscente = d.idDiscente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY d.idDiscente, p.nome, d.matricula, d.curso " +
                "ORDER BY totalRemuneracao DESC";

        executarEImprimir(sql, "Remuneração e auxílio-transporte total por discente");
    }


    public void cargaHorariaPorCurso() {
        String sql = "SELECT d.curso AS curso, MAX(tc.horasTotais) AS maiorCargaHoraria, " +
                "MIN(tc.horasTotais) AS menorCargaHoraria, ROUND(AVG(tc.horasTotais), 0) AS mediaCargaHoraria, " +
                "COUNT(e.idEstagio) AS totalEstagios " +
                "FROM Discente d " +
                "JOIN Estagio e ON e.idDiscente = d.idDiscente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY d.curso " +
                "ORDER BY d.curso";

        executarEImprimir(sql, "Maior e menor carga horária de estágio por curso");
    }


    public void estagiosAtivosComMaisDeUmDiscentePorPeriodo() {
        String sql = "SELECT d.curso AS curso, pl.ano AS ano, pl.periodo AS periodo, " +
                "COUNT(e.idEstagio) AS discentesEmEstagio " +
                "FROM Estagio e " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "JOIN PeriodoLetivo pl ON pl.idPeriodo = e.idPeriodo " +
                "WHERE e.status = 'EM_ANDAMENTO' " +
                "GROUP BY d.curso, pl.idPeriodo, pl.ano, pl.periodo " +
                "HAVING COUNT(e.idEstagio) > 1 " +
                "ORDER BY discentesEmEstagio DESC";

        executarEImprimir(sql, "Estágios em andamento com mais de um discente por período");
    }

    public void estagiosComPlanoAprovado() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, d.curso AS curso, " +
                "c.nome AS empresa, pa.status AS statusPlano, pa.assinadoDiscente AS assinadoDiscente, " +
                "pa.dataElaboracao AS dataElaboracao " +
                "FROM Estagio e " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Concedente c ON c.idConcedente = e.idConcedente " +
                "JOIN PlanoAtividades pa ON pa.idEstagio = e.idEstagio " +
                "WHERE pa.status = 'Aprovado' " +
                "ORDER BY pa.dataElaboracao DESC";

        executarEImprimir(sql, "Estágios com plano de atividades aprovado");
    }

    public void discentesSemEstagio() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, d.curso AS curso, " +
                "d.situacao AS situacaoAcademica, p.email AS email " +
                "FROM Discente d " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "LEFT JOIN Estagio e ON e.idDiscente = d.idDiscente " +
                "WHERE e.idEstagio IS NULL " +
                "ORDER BY d.curso, p.nome";

        executarEImprimir(sql, "Discentes que nunca tiveram estágio registrado");
    }

    public void cursosComMaisDeDoisDiscentesPorPeriodo() {
        String sql = "SELECT d.curso AS curso, pl.ano AS ano, pl.periodo AS periodo, " +
                "COUNT(e.idEstagio) AS discentesEmEstagio " +
                "FROM Estagio e " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "JOIN PeriodoLetivo pl ON pl.idPeriodo = e.idPeriodo " +
                "WHERE e.status = 'EM_ANDAMENTO' " +
                "GROUP BY d.curso, pl.idPeriodo, pl.ano, pl.periodo " +
                "HAVING COUNT(e.idEstagio) > 2 " +
                "ORDER BY discentesEmEstagio DESC";

        executarEImprimir(sql, "Cursos com mais de 2 discentes em estágio no mesmo período");
    }

    public void estagiosCursosTI() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, d.curso AS curso, " +
                "c.nome AS empresa, e.tipo AS tipoEstagio, e.status AS situacao, " +
                "pl.ano AS ano, pl.periodo AS periodo " +
                "FROM Estagio e " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Concedente c ON c.idConcedente = e.idConcedente " +
                "JOIN PeriodoLetivo pl ON pl.idPeriodo = e.idPeriodo " +
                "WHERE d.curso IN ('Sistemas de Informação', 'Engenharia de Software', 'Ciencia da Computacao') " +
                "ORDER BY d.curso, p.nome";

        executarEImprimir(sql, "Estágios de discentes dos cursos de TI");
    }


    public void discentesComRemuneracaoAcimaDaMedia() {
        String sql = "SELECT p.nome AS discente, d.curso AS curso, c.nome AS empresa, " +
                "tc.remuneracao AS remuneracao, tc.horasSemanais AS horasSemanais " +
                "FROM Discente d " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Estagio e ON e.idDiscente = d.idDiscente " +
                "JOIN Concedente c ON c.idConcedente = e.idConcedente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "WHERE tc.remuneracao > (SELECT AVG(remuneracao) FROM TermoCompromisso) " +
                "ORDER BY tc.remuneracao DESC";

        executarEImprimir(sql, "Discentes com remuneração acima da média geral dos estágios");
    }


    public void supervisoresComMaisDeUmEstagiario() {
        String sql = "SELECT p.nome AS supervisor, c.nome AS empresa, COUNT(e.idEstagio) AS totalEstagiarios " +
                "FROM SupervisorConcedente sc " +
                "JOIN Pessoa p ON p.idPessoa = sc.idPessoa " +
                "JOIN Concedente c ON c.idConcedente = sc.idConcedente " +
                "JOIN Estagio e ON e.idSupervisor = sc.idSupervisor " +
                "GROUP BY sc.idSupervisor, p.nome, c.nome " +
                "HAVING COUNT(e.idEstagio) > 1 " +
                "ORDER BY totalEstagiarios DESC";

        executarEImprimir(sql, "Supervisores que acompanham mais de um estagiário");
    }

    public void empresasComMaisDe500Horas() {
        String sql = "SELECT c.nome AS empresa, c.areaAtuacao AS area, COUNT(e.idEstagio) AS totalEstagios, " +
                "SUM(tc.horasTotais) AS horasContratadas " +
                "FROM Concedente c " +
                "JOIN Estagio e ON e.idConcedente = c.idConcedente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY c.idConcedente, c.nome, c.areaAtuacao " +
                "HAVING SUM(tc.horasTotais) > 500 " +
                "ORDER BY horasContratadas DESC";

        executarEImprimir(sql, "Empresas cuja soma de horas contratadas ultrapassa 500 horas");
    }

    public void cursosComRemuneracaoMediaAcimaDe600() {
        String sql = "SELECT d.curso AS curso, COUNT(e.idEstagio) AS totalEstagios, " +
                "ROUND(AVG(tc.remuneracao), 2) AS remuneracaoMedia, " +
                "ROUND(AVG(tc.horasSemanais), 1) AS mediaHorasSemanais " +
                "FROM Discente d " +
                "JOIN Estagio e ON e.idDiscente = d.idDiscente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY d.curso " +
                "HAVING AVG(tc.remuneracao) > 600 " +
                "ORDER BY remuneracaoMedia DESC";

        executarEImprimir(sql, "Cursos cuja remuneração média dos estágios é maior que R$ 600");
    }

    public void periodosComMaisDeDoisEstagios() {
        String sql = "SELECT pl.ano AS ano, pl.periodo AS periodo, COUNT(e.idEstagio) AS totalEstagios, " +
                "COUNT(DISTINCT d.curso) AS cursosEnvolvidos " +
                "FROM PeriodoLetivo pl " +
                "JOIN Estagio e ON e.idPeriodo = pl.idPeriodo " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "GROUP BY pl.idPeriodo, pl.ano, pl.periodo " +
                "HAVING COUNT(e.idEstagio) > 2 " +
                "ORDER BY pl.ano DESC, pl.periodo DESC";

        executarEImprimir(sql, "Períodos letivos com mais de 2 estágios registrados");
    }

    public void somaCargaHorariaPorEmpresa() {
        String sql = "SELECT c.nome AS empresa, SUM(tc.horasTotais) AS totalHorasEstagio " +
                "FROM Concedente c " +
                "JOIN Estagio e ON e.idConcedente = c.idConcedente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY c.idConcedente, c.nome " +
                "ORDER BY totalHorasEstagio DESC";

        executarEImprimir(sql, "Soma da carga horária total por empresa");
    }

    public void maiorCargaHorariaContratada() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, c.nome AS empresa, " +
                "e.tipo AS tipoEstagio, MAX(tc.horasTotais) AS maiorCargaHoraria " +
                "FROM Estagio e " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Concedente c ON c.idConcedente = e.idConcedente " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY p.nome, d.matricula, c.nome, e.tipo " +
                "ORDER BY maiorCargaHoraria DESC";

        executarEImprimir(sql, "Maior carga horária contratada em um estágio");
    }

    public void menorRemuneracaoPorTermo() {
        String sql = "SELECT p.nome AS discente, d.matricula AS matricula, c.nome AS empresa, " +
                "tc.idTermo AS numeroTermo, MIN(tc.remuneracao) AS menorRemuneracao " +
                "FROM TermoCompromisso tc " +
                "JOIN Estagio e ON e.idEstagio = tc.idEstagio " +
                "JOIN Discente d ON d.idDiscente = e.idDiscente " +
                "JOIN Pessoa p ON p.idPessoa = d.idPessoa " +
                "JOIN Concedente c ON c.idConcedente = e.idConcedente " +
                "GROUP BY p.nome, d.matricula, c.nome, tc.idTermo " +
                "ORDER BY menorRemuneracao ASC";

        executarEImprimir(sql, "Menor remuneração paga entre todos os termos de compromisso");
    }

    public void somaAuxilioTransportePorPeriodo() {
        String sql = "SELECT pl.ano AS ano, pl.periodo AS periodo, " +
                "SUM(tc.auxTransporte) AS totalAuxTransporte, SUM(tc.remuneracao) AS totalRemuneracao " +
                "FROM TermoCompromisso tc " +
                "JOIN Estagio e ON e.idEstagio = tc.idEstagio " +
                "JOIN PeriodoLetivo pl ON pl.idPeriodo = e.idPeriodo " +
                "GROUP BY pl.idPeriodo, pl.ano, pl.periodo " +
                "ORDER BY pl.ano DESC, pl.periodo DESC";

        executarEImprimir(sql, "Soma do auxílio-transporte por período letivo");
    }

    public void cargaHorariaPorTipoEstagio() {
        String sql = "SELECT e.tipo AS tipoEstagio, MAX(tc.horasTotais) AS maiorCargaTotal, " +
                "MIN(tc.horasTotais) AS menorCargaTotal, ROUND(AVG(tc.horasTotais), 0) AS mediaCargaTotal " +
                "FROM Estagio e " +
                "JOIN TermoCompromisso tc ON tc.idEstagio = e.idEstagio " +
                "GROUP BY e.tipo " +
                "ORDER BY e.tipo";

        executarEImprimir(sql, "Maior e menor carga horária contratada por tipo de estágio");
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
