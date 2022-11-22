package graphic.main.paineisPrincipais.painelBaixo.botoesEntidades;

import graphic.entidades.turmas.TurmasPanel;

import javax.swing.*;

public class BotaoTurmas extends BotaoEntidadeBase {
    public BotaoTurmas(int x, int y, String titulo, String caminhoImagem, JFrame cmpPai) {
        super(x, y, titulo, caminhoImagem, cmpPai);
    }

    @Override
    protected void onClick(JFrame cmpPai) {
        TurmasPanel turmasPanel = new TurmasPanel(cmpPai);
        cmpPai.getContentPane().removeAll();

        cmpPai.getContentPane().add(turmasPanel);

        cmpPai.getContentPane().revalidate();
        cmpPai.getContentPane().repaint();
    }
}
