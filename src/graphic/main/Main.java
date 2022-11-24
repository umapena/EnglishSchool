package graphic.main;

import graphic.main.paineisPrincipais.painelBaixo.PainelBaixoMain;
import graphic.main.paineisPrincipais.painelCima.PainelCimaMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class Main extends JFrame {

    public Main() {
        setSize(1100,820);
        setTitle("TW English School");
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        criaPainelDeCima();
        criaPainelDeBaixo();
    }

    private void criaPainelDeCima() {
        PainelCimaMain painelCimaMain = new PainelCimaMain();
        getContentPane().add(painelCimaMain);
    }

    private void criaPainelDeBaixo() {
        PainelBaixoMain painelBaixoMain = new PainelBaixoMain(this);
        getContentPane().add(painelBaixoMain);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}
