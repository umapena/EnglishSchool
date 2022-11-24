package graphic.entidades.base;

import javax.swing.*;
import java.awt.*;

public abstract class EntidadesCadastro extends JDialog {
    private boolean isReadOnly = false;

    public EntidadesCadastro(){
        setBackground(new Color(255,255,255));
        setLayout(null);
        setTitle("Cadastro");
        setModal(true);
        setSize(520, 430);
        setResizable(false);
        setLocationRelativeTo(null);
        criarBotoes();
    }

    public void criarBotoes(){
        if(isReadOnly){
            return;
        }

        GridBagConstraints gbc = new GridBagConstraints();

        JButton btnFechar = new JButton("Cancelar");
        btnFechar.setPreferredSize(new Dimension(100,30));
        btnFechar.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnFechar.setBackground(new Color(255,92,92));
        btnFechar.setBorder(BorderFactory.createLineBorder(new Color(255, 92, 92)));

        btnFechar.addActionListener(e -> {
            dispose();
        });

        gbc.gridx = 0; gbc.gridy = 0;gbc.insets = new Insets(0,0,0,100);
        panelBotoes.add(btnFechar, gbc);


        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setPreferredSize(new Dimension(100,30));
        btnSalvar.addActionListener(e -> onClickSalvar());
        btnSalvar.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnSalvar.setBackground(new Color(87, 241, 87, 210));
        btnSalvar.setBorder(BorderFactory.createLineBorder(new Color(87, 241, 87, 210)));
        add(btnSalvar);

        gbc.gridx = 1; gbc.gridy = 0;gbc.insets = new Insets(0,0,0,10);
        panelBotoes.add(btnSalvar, gbc);
    }

    protected abstract void onClickSalvar();
    protected JPanel panelBotoes = new JPanel(new GridBagLayout());
}