package graphic.main.paineisPrincipais.painelBaixo;

import graphic.main.paineisPrincipais.painelBaixo.botoesEntidades.*;

import javax.swing.*;
import java.awt.*;

public class PainelBaixoMain extends JPanel {
    public PainelBaixoMain(JFrame cmpPai) {
        setLayout(null);
        setBackground(new Color(255, 255, 255));
        setBounds(0, 250, 1100, 570);

        BotaoAlunos botaoAlunos = new BotaoAlunos(120, 50, "Alunos", "/resources/icons/alunosIcon.png", cmpPai);
        add(botaoAlunos);

        BotaoTurmas botaoTurmas = new BotaoTurmas(450, 50, "Turmas", "/resources/icons/modalidadesIcon.png", cmpPai);
        add(botaoTurmas);

        BotaoMatriculas botaoMatriculas = new BotaoMatriculas(450, 280, "Matrículas", "/resources/icons/matriculasIcon.png", cmpPai);
        add(botaoMatriculas);

        BotaoFaturas botaoFaturas = new BotaoFaturas(780, 280, "Financeiro", "/resources/icons/faturasIcon.png", cmpPai);
        add(botaoFaturas);
    }
}
