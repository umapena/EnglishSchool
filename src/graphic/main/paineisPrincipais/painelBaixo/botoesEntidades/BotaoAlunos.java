package graphic.main.paineisPrincipais.painelBaixo.botoesEntidades;

import graphic.entidades.alunos.AlunosPanel;

import javax.swing.*;

public class BotaoAlunos extends BotaoEntidadeBase {
    public BotaoAlunos(int x, int y, String titulo, String caminhoImagem, JFrame cmpPai) {
        super(x, y, titulo, caminhoImagem, cmpPai);
    }

    @Override
    protected void onClick(JFrame cmpPai) {
        AlunosPanel alunosPanel = new AlunosPanel(cmpPai);
        cmpPai.getContentPane().removeAll();

        cmpPai.getContentPane().add(alunosPanel);

        cmpPai.getContentPane().revalidate();
        cmpPai.getContentPane().repaint();
    }
}
