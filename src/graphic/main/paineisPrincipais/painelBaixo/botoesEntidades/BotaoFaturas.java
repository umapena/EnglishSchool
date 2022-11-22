package graphic.main.paineisPrincipais.painelBaixo.botoesEntidades;

import graphic.entidades.faturas.FaturasPanel;

import javax.swing.*;

public class BotaoFaturas extends BotaoEntidadeBase {
    public BotaoFaturas(int x, int y, String titulo, String caminhoImagem, JFrame cmpPai) {
        super(x, y, titulo, caminhoImagem, cmpPai);
    }

    @Override
    protected void onClick(JFrame cmpPai) {
        FaturasPanel faturasPanel = new FaturasPanel(cmpPai);
        cmpPai.getContentPane().removeAll();

        cmpPai.getContentPane().add(faturasPanel);

        cmpPai.getContentPane().revalidate();
        cmpPai.getContentPane().repaint();
    }
}
