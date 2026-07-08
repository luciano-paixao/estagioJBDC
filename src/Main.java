import controller.*;
import view.*;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                PainelPrincipal tela = new PainelPrincipal();
                PrincipalController controller = new PrincipalController(tela);
                tela.setVisible(true);
            }
        });
    }
}