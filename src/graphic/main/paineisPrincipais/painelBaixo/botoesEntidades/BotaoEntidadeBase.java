package graphic.main.paineisPrincipais.painelBaixo.botoesEntidades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class BotaoEntidadeBase extends JPanel {
    private ImageIcon imagemPainel;
    private JLabel labelImagem;
    private JLabel tituloPainel;

    public BotaoEntidadeBase(int x, int y, String titulo, String caminhoImagem, JFrame cmpPai) {
        setBounds(x, y, 170, 170);
        setBackground(new Color(234, 229, 229));

        // O GridBagLayout centraliza os elementos vertical e horizontalmente automaticamente.
        setLayout(new GridBagLayout());

        // As constraints servem para personalizarmos o comportamento do layout.
        GridBagConstraints gbc = new GridBagConstraints();

        imagemPainel = new ImageIcon(this.getClass().getResource(caminhoImagem));
        labelImagem = new JLabel(imagemPainel);
        add(labelImagem);

        // Aqui definimos essa prop como 0 para criar uma nova linha no layout. Semelhante ao flex-direction: column; do CSS.
        gbc.gridx = 0;

        tituloPainel = new JLabel(titulo);
        tituloPainel.setFont(new Font("Arial", Font.BOLD, 18));
        add(tituloPainel, gbc);

        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onClick(cmpPai);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(194, 184, 184));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(234, 229, 229));
            }
        });
    }

    protected abstract void onClick(JFrame cmpPai);
}
