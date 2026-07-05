import controller.DiscenteController;
import view.TelaCadastroDiscente;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Instancia a tela (carrega o visual e os componentes)
                TelaCadastroDiscente tela = new TelaCadastroDiscente();

                // Instancia o Controller passando a tela para ele amarrar os eventos (cliques nos botões)
                DiscenteController controller = new DiscenteController(tela);

                // Torna a janela visível para o usuário
                tela.setVisible(true);
            }
        });
    }
}