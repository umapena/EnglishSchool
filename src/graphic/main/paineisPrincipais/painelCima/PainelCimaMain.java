package graphic.main.paineisPrincipais.painelCima;

import javax.swing.*;
import java.awt.*;

public class PainelCimaMain extends JPanel {
    public PainelCimaMain() {
        setLayout(null);
        setBackground(new Color(153, 153, 204));
        setBounds(0, 0, 1100, 270);

        JLabel titulo = new JLabel("TW English School");
        titulo.setFont(new Font("Helvetica", Font.BOLD, 44));
        titulo.setBounds(50, 200, 400, 50);
        add(titulo);
//        ImageIcon imagemLogo = new ImageIcon(this.getClass().getResource("/resources/LogoSistema.png"));
//        JLabel labelLogo = new JLabel(imagemLogo);
//        labelLogo.setBounds(130, 30, 164, 199);
//        add(labelLogo);
    }
}
