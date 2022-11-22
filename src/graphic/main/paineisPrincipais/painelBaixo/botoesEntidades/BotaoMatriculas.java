package graphic.main.paineisPrincipais.painelBaixo.botoesEntidades;

import graphic.entidades.matriculas.MatriculasPanel;

import javax.swing.*;

public class BotaoMatriculas extends BotaoEntidadeBase {
    public BotaoMatriculas(int x, int y, String titulo, String caminhoImagem, JFrame cmpPai) {
        super(x, y, titulo, caminhoImagem, cmpPai);
    }

    @Override
    protected void onClick(JFrame cmpPai) {
        MatriculasPanel matriculasPanel = new MatriculasPanel(cmpPai);
        cmpPai.getContentPane().removeAll();

        cmpPai.getContentPane().add(matriculasPanel);

        cmpPai.getContentPane().revalidate();
        cmpPai.getContentPane().repaint();
    }
}
