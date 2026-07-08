import controller.*;
import view.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // TESTE DISCENTE
//                TelaCadastroDiscente tela = new TelaCadastroDiscente();        // Instancia a tela (carrega o visual e os componentes)
//                DiscenteController controller = new DiscenteController(tela);  // Instancia o Controller para amarrar eventos (cliques nos botões)
//                tela.setVisible(true);                                         // Torna a janela visível para o usuário

                // TESTE PERIODO
//                TelaCadastroPeriodo tela = new TelaCadastroPeriodo();
//                PeriodoController controller = new PeriodoController(tela);
//                tela.setVisible(true);

                // TESTE PLANO ATIVIDADES
//                TelaCadastroPlanoAtividades tela = new TelaCadastroPlanoAtividades();
//                PlanoAtividadesController controller = new PlanoAtividadesController(tela);
//                tela.setVisible(true);

                // TESTE PLANO ATIVIDADES
//                TelaCadastroTermoCompromisso tela = new TelaCadastroTermoCompromisso();
//                TermoCompromissoController controller = new TermoCompromissoController(tela);
//                tela.setVisible(true);

                // TESTE COORDENADOR
//                TelaCadastroCoordenador tela = new TelaCadastroCoordenador();
//                CoordenadorController controller = new CoordenadorController(tela);
//                tela.setVisible(true);

                // TESTE SUPERVISOR
//                TelaCadastroSupervisor tela = new TelaCadastroSupervisor();
//                SupervisorController controller = new SupervisorController(tela);
//                tela.setVisible(true);

                // TESTE CONCEDENTE
//                TelaCadastroConcedente tela = new TelaCadastroConcedente();
//                ConcedenteController controller = new ConcedenteController(tela);
//                tela.setVisible(true);

                // TESTE ESTAGIO
                TelaCadastroEstagio tela = new TelaCadastroEstagio();
                EstagioController controller = new EstagioController(tela);
                tela.setVisible(true);

                // TESTE SUPERVISOR
//                TelaCadastroAproveitamento tela = new TelaCadastroAproveitamento();
//                AproveitamentoController controller = new AproveitamentoController(tela);
//                tela.setVisible(true);
            }
        });
    }
}