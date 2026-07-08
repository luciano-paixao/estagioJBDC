package controller;

import view.*;
import DAO.*;

import models.discente.Discente;
import models.concedente.Concedente;
import models.estagio.Estagio;
import models.coordenadorEstagio.CoordenadorEstagio;

import java.util.List;

public class PrincipalController {

    private PainelPrincipal view;

    // DAOs necessários para as consultas do Dashboard
    private DiscenteDAO discenteDAO;
    private ConcedenteDAO concedenteDAO;
    private EstagioDAO estagioDAO;
    private CoordenadorDAO coordenadorDAO;

    public PrincipalController(PainelPrincipal view) {
        this.view = view;

        this.discenteDAO = new DiscenteDAO();
        this.concedenteDAO = new ConcedenteDAO();
        this.estagioDAO = new EstagioDAO();
        this.coordenadorDAO = new CoordenadorDAO();

        inicializarEventosMenu();
        inicializarEventosDashboard();
    }

    private void inicializarEventosMenu() {
        // --- Cadastros Base ---
        view.getMenuDiscente().addActionListener(e -> {
            TelaCadastroDiscente tela = new TelaCadastroDiscente();
            new DiscenteController(tela);
            tela.setVisible(true);
        });

        view.getMenuDocente().addActionListener(e -> {
            TelaCadastroDocente tela = new TelaCadastroDocente();
            new DocenteController(tela);
            tela.setVisible(true);
        });

        view.getMenuCoordenador().addActionListener(e -> {
            TelaCadastroCoordenador tela = new TelaCadastroCoordenador();
            new CoordenadorController(tela);
            tela.setVisible(true);
        });

        view.getMenuConcedente().addActionListener(e -> {
            TelaCadastroConcedente tela = new TelaCadastroConcedente();
            new ConcedenteController(tela);
            tela.setVisible(true);
        });

        // Adicionando o Supervisor
        view.getMenuSupervisor().addActionListener(e -> {
            TelaCadastroSupervisor tela = new TelaCadastroSupervisor();
            new SupervisorController(tela);
            tela.setVisible(true);
        });

        // --- Processos de Estágio e Documentos ---
        view.getMenuEstagio().addActionListener(e -> {
            TelaCadastroEstagio tela = new TelaCadastroEstagio();
            new EstagioController(tela);
            tela.setVisible(true);
        });

        // AQUI ESTÁ A CORREÇÃO DO APROVEITAMENTO PROFISSIONAL!
        view.getMenuAproveitamento().addActionListener(e -> {
            TelaCadastroAproveitamento tela = new TelaCadastroAproveitamento();
            new AproveitamentoController(tela);
            tela.setVisible(true);
        });

        view.getMenuSair().addActionListener(e -> System.exit(0));
    }

    private void inicializarEventosDashboard() {
        view.getBtnExecutarConsulta().addActionListener(e -> executarConsultaReal());
    }

    private void executarConsultaReal() {
        int index = view.getCbConsultas().getSelectedIndex();

        if (index == 0) {
            view.exibirMensagem("Selecione uma consulta na lista.");
            return;
        }

        String[] colunas = null;
        Object[][] dados = null;

        try {
            switch (index) {
                case 1: // 1. Listar todos os Discentes
                    List<Discente> discentes = discenteDAO.listarTodos();
                    colunas = new String[]{"ID", "Matrícula", "Nome", "Curso", "Situação"};
                    dados = new Object[discentes.size()][5];
                    for (int i = 0; i < discentes.size(); i++) {
                        dados[i][0] = discentes.get(i).getIdDiscente();
                        dados[i][1] = discentes.get(i).getMatricula();
                        dados[i][2] = (discentes.get(i).getPessoa() != null) ? discentes.get(i).getPessoa().getNome() : "-";
                        dados[i][3] = discentes.get(i).getCurso();
                        dados[i][4] = discentes.get(i).getSituacao();
                    }
                    break;

                case 2: // 2. Listar todas as Concedentes
                    List<Concedente> concedentes = concedenteDAO.listarTodos();
                    colunas = new String[]{"ID", "Razão Social", "CNPJ", "Tipo", "Área de Atuação"};
                    dados = new Object[concedentes.size()][5];
                    for (int i = 0; i < concedentes.size(); i++) {
                        dados[i][0] = concedentes.get(i).getIdConcedente();
                        dados[i][1] = concedentes.get(i).getNome();
                        dados[i][2] = concedentes.get(i).getCnpj();
                        dados[i][3] = concedentes.get(i).getTipo();
                        dados[i][4] = concedentes.get(i).getAreaAtuacao();
                    }
                    break;

                case 3: // 3. Listar todos os Estágios
                    List<Estagio> estagios = estagioDAO.listarTodos();
                    colunas = new String[]{"ID", "Tipo", "Status", "Matrícula Discente", "CNPJ Empresa"};
                    dados = new Object[estagios.size()][5];
                    for (int i = 0; i < estagios.size(); i++) {
                        dados[i][0] = estagios.get(i).getIdEstagio();
                        dados[i][1] = estagios.get(i).getTipo();
                        dados[i][2] = estagios.get(i).getStatus();
                        dados[i][3] = (estagios.get(i).getDiscente() != null) ? estagios.get(i).getDiscente().getMatricula() : "-";
                        dados[i][4] = (estagios.get(i).getConcedente() != null) ? estagios.get(i).getConcedente().getCnpj() : "-";
                    }
                    break;

                case 4: // 4. Listar Coordenadores de Estágio
                    List<CoordenadorEstagio> coord = coordenadorDAO.listarTodos();
                    colunas = new String[]{"ID", "Nº Portaria", "Nome do Docente", "SIAPE"};
                    dados = new Object[coord.size()][4];
                    for (int i = 0; i < coord.size(); i++) {
                        dados[i][0] = coord.get(i).getIdCoordenadorEstagio();
                        dados[i][1] = coord.get(i).getNumeroPortaria();
                        if (coord.get(i).getDocente() != null && coord.get(i).getDocente().getPessoa() != null) {
                            dados[i][2] = coord.get(i).getDocente().getPessoa().getNome();
                            dados[i][3] = coord.get(i).getDocente().getMatriculaSiape();
                        } else {
                            dados[i][2] = "-";
                            dados[i][3] = "-";
                        }
                    }
                    break;

                case 5: // Carga Horária por Tipo de Estágio
                    colunas = new String[]{"Tipo de Estágio", "Maior Carga", "Menor Carga", "Média Carga"};
                    dados = converterListaParaMatriz(estagioDAO.cargaHorariaPorTipoEstagio());
                    break;

                case 6: // Soma de Auxílio Transporte por Período
                    colunas = new String[]{"Ano", "Período", "Soma Auxílio", "Soma Remuneração"};
                    dados = converterListaParaMatriz(estagioDAO.somaAuxilioTransportePorPeriodo());
                    break;

                case 7: // Menor Remuneração por Termo
                    colunas = new String[]{"Discente", "Matrícula", "Empresa", "Nº Termo", "Menor Remuneração"};
                    dados = converterListaParaMatriz(estagioDAO.menorRemuneracaoPorTermo());
                    break;

                case 8: // Maior Carga Horária Contratada
                    colunas = new String[]{"Discente", "Matrícula", "Empresa", "Tipo Estágio", "Maior Carga"};
                    dados = converterListaParaMatriz(estagioDAO.maiorCargaHorariaContratada());
                    break;

                case 9: // Soma da Carga Horária por Empresa
                    colunas = new String[]{"Empresa", "Carga Horária Somada"};
                    dados = converterListaParaMatriz(estagioDAO.somaCargaHorariaPorEmpresa());
                    break;

                case 10: // Períodos com mais de Dois Estágios
                    colunas = new String[]{"Ano", "Período", "Total Estágios", "Cursos Envolvidos"};
                    dados = converterListaParaMatriz(estagioDAO.periodosComMaisDeDoisEstagios());
                    break;

                case 11: // Cursos com Remuneração Média Acima de 600
                    colunas = new String[]{"Curso", "Total Estágios", "Remuneração Média", "Média Horas Semanais"};
                    dados = converterListaParaMatriz(estagioDAO.cursosComRemuneracaoMediaAcimaDe600());
                    break;

                case 12: // Empresas com mais de 500 Horas
                    colunas = new String[]{"Empresa", "Área", "Total Estágios", "Horas Contratadas"};
                    dados = converterListaParaMatriz(estagioDAO.empresasComMaisDe500Horas());
                    break;

                case 13: // Supervisores com mais de um Estagiário
                    colunas = new String[]{"Supervisor", "Empresa", "Total Estagiários"};
                    dados = converterListaParaMatriz(estagioDAO.supervisoresComMaisDeUmEstagiario());
                    break;

                case 14: // Discentes com Remuneração Acima da Média
                    colunas = new String[]{"Discente", "Curso", "Empresa", "Remuneração", "Horas Semanais"};
                    dados = converterListaParaMatriz(estagioDAO.discentesComRemuneracaoAcimaDaMedia());
                    break;

                case 15: // Estágios em Cursos de TI
                    colunas = new String[]{"Discente", "Matrícula", "Curso", "Empresa", "Tipo", "Situação", "Ano", "Período"};
                    dados = converterListaParaMatriz(estagioDAO.estagiosCursosTI());
                    break;

                case 16: // Cursos com mais de Dois Discentes por Período
                    colunas = new String[]{"Curso", "Ano", "Período", "Discentes em Estágio"};
                    dados = converterListaParaMatriz(estagioDAO.cursosComMaisDeDoisDiscentesPorPeriodo());
                    break;

                case 17: // Discentes sem Estágio
                    colunas = new String[]{"Discente", "Matrícula", "Curso", "Situação Acadêmica", "Email"};
                    dados = converterListaParaMatriz(estagioDAO.discentesSemEstagio());
                    break;

                case 18: // Estágios com Plano Aprovado
                    colunas = new String[]{"Discente", "Matrícula", "Curso", "Empresa", "Status Plano", "Assinado", "Data Elaboração"};
                    dados = converterListaParaMatriz(estagioDAO.estagiosComPlanoAprovado());
                    break;

                case 19: // Estágios Ativos com mais de um Discente por Período
                    colunas = new String[]{"Curso", "Ano", "Período", "Discentes em Estágio"};
                    dados = converterListaParaMatriz(estagioDAO.estagiosAtivosComMaisDeUmDiscentePorPeriodo());
                    break;

                case 20: // Carga Horária por Curso
                    colunas = new String[]{"Curso", "Maior Carga", "Menor Carga", "Média Carga", "Total Estágios"};
                    dados = converterListaParaMatriz(estagioDAO.cargaHorariaPorCurso());
                    break;

                case 21: // Remuneração e Auxílio por Discente
                    colunas = new String[]{"Discente", "Matrícula", "Curso", "Remuneração Total", "Auxílio Total", "Média Horas"};
                    dados = converterListaParaMatriz(estagioDAO.remuneracaoEAuxilioPorDiscente());
                    break;

                case 22: // Quantidade de Estágios por Empresa
                    colunas = new String[]{"Empresa", "Área de Atuação", "Total de Estágios"};
                    dados = converterListaParaMatriz(estagioDAO.quantidadeEstagiosPorEmpresa());
                    break;

                case 23: // Discentes com Estágio Ativo
                    colunas = new String[]{"Discente", "Matrícula", "Curso", "Empresa", "Ano", "Período"};
                    dados = converterListaParaMatriz(estagioDAO.discentesComEstagioAtivo());
                    break;
            }

            // Injeta os dados mapeados na tabela
            view.atualizarTabela(colunas, dados);



        } catch (Exception ex) {
            view.exibirMensagem("Erro ao executar consulta no banco: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private Object[][] converterListaParaMatriz(List<Object[]> lista) {
        if (lista == null || lista.isEmpty()) return new Object[0][0];

        Object[][] matriz = new Object[lista.size()][lista.get(0).length];
        for (int i = 0; i < lista.size(); i++) {
            matriz[i] = lista.get(i);
        }
        return matriz;
    }
}