package graphic.entidades.base;

import javax.swing.*;
import java.awt.*;

public abstract class EntidadesCadastro extends JDialog {
    private boolean isReadOnly = false;

    public EntidadesCadastro(){
        setBackground(new Color(255,255,255));
        setLayout(null);
        setTitle("Cadastro");
        setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("/resources/icons/sistemaIcon.png")));
        setModal(true);
        setSize(520, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        criarBotoes();
    }

    public void criarBotoes(){
        if(isReadOnly){
            return;
        }

        JButton btnFechar = new JButton("Cancelar");
        btnFechar.setBounds(100,580,120,30);
        btnFechar.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnFechar.setBackground(new Color(255,92,92));
        btnFechar.setBorder(BorderFactory.createLineBorder(new Color(255, 92, 92)));
        add(btnFechar);

        btnFechar.addActionListener(e -> {
            dispose();
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(310,580,120,30);
        btnSalvar.addActionListener(e -> onClickSalvar());
        btnSalvar.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnSalvar.setBackground(new Color(87, 241, 87, 210));
        btnSalvar.setBorder(BorderFactory.createLineBorder(new Color(87, 241, 87, 210)));
        add(btnSalvar);
    }

    protected abstract void onClickSalvar();
}
