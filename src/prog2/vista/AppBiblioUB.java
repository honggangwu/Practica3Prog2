package prog2.vista;

import javax.swing.*;

public class AppBiblioUB extends JFrame {
    private JPanel panelMain;
    private JButton btnGestioUsuaris;
    private JButton btnGestioExemplars;
    private JButton tnGestioPrestecs;
    private JButton btnGuardar;
    private JButton btnCarregar;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppBiblioUB biblioUB = new AppBiblioUB();
            biblioUB.setVisible(true);
        });
    }
    public AppBiblioUB(){
        setTitle("AppBiblioUB GUI");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setContentPane(panelMain);
        setSize(500,400);
        setLocationRelativeTo(null);
    }
}
