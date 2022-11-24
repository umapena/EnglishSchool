package graphic.entidades.base;

import graphic.main.paineisPrincipais.painelBaixo.PainelBaixoMain;
import graphic.main.paineisPrincipais.painelCima.PainelCimaMain;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

// Painel base para todos os outros. Por padrão virá com o botão voltar, que reiniciará o JFrame Main, recolocando os paineis padrão.
public abstract class EntidadesPanel extends JPanel {
    private JFrame cmpPai;

    public EntidadesPanel(JFrame cmpPai) {
        this.cmpPai = cmpPai;
        setBackground(new Color(255,255,255));
        setLayout(null);
        setBounds(0, 0, 1100, 820);
        criaPainelDeCima();
        criaPainelDeBaixo();
    }

    protected abstract String getTitulo();

    private void criaPainelDeCima() {
        JPanel painelCima = new JPanel();
        painelCima.setBackground(new Color(153, 153, 204));
        painelCima.setLayout(null);
        painelCima.setBounds(0, 0, 1100, 140);
        add(painelCima);

        JButton btnVoltar = criaBotaoVoltar();
        painelCima.add(btnVoltar);

        JLabel titulo = new JLabel(getTitulo());
        titulo.setFont(new Font("Helvetica", Font.BOLD, 36));
        titulo.setBounds(30, 90, 300, 40);
        painelCima.add(titulo);
    }

    private JButton criaBotaoVoltar() {
        ImageIcon homeIcon = new ImageIcon(this.getClass().getResource("/resources/icons/homePage.png"));
        JButton jButton = new JButton(homeIcon);
        jButton.setText("Voltar");
        jButton.setFont(new Font("Helvetica", Font.BOLD, 16));
        jButton.setBackground(Color.WHITE);
        jButton.setBorder(BorderFactory.createEmptyBorder());
        jButton.setBounds(5, 15, 100, 30);
        jButton.setOpaque(false);
        jButton.setRolloverEnabled(false);
        jButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        jButton.addActionListener(e -> {
            PainelCimaMain painelCimaMain = new PainelCimaMain();
            PainelBaixoMain painelBaixoMain = new PainelBaixoMain(cmpPai);

            cmpPai.getContentPane().removeAll();

            cmpPai.getContentPane().add(painelCimaMain);
            cmpPai.getContentPane().add(painelBaixoMain);

            cmpPai.getContentPane().revalidate();
            cmpPai.getContentPane().repaint();
        });

        return jButton;
    }

    private void criaPainelDeBaixo() {
        JPanel painelBaixo = new JPanel();
        painelBaixo.setBackground(new Color(255, 255, 255));
        painelBaixo.setLayout(null);
        painelBaixo.setBounds(0, 140, 1100, 680);
        add(painelBaixo);

        JTable tabela = criaTabela();
        configuraTabela(tabela);

        criarBotoes(painelBaixo, tabela);

        JScrollPane jScrollPane = new JScrollPane(tabela);
        jScrollPane.setBounds(45, 90, 1000, 500);
        painelBaixo.add(jScrollPane);

    }

    protected void criarBotoes(JPanel panel, JTable tabela){
        ImageIcon smbMais = new ImageIcon(this.getClass().getResource("/resources/icons/plusIcon.png"));
        JButton btnCadastrar = new JButton(smbMais);
        btnCadastrar.setBounds(45, 30, 40, 40);
        btnCadastrar.setBackground(Color.WHITE);
        btnCadastrar.setBorder(BorderFactory.createEmptyBorder());
        btnCadastrar.setOpaque(false);
        btnCadastrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCadastrar.setToolTipText("Novo");
        btnCadastrar.addActionListener(e -> onClickNovo());

        ImageIcon smbDelete = new ImageIcon(this.getClass().getResource("/resources/icons/deleteIcon.png"));
        JButton btnDelete = new JButton(smbDelete);
        btnDelete.setBackground(Color.WHITE);
        btnDelete.setOpaque(false);
        btnDelete.setBorder(BorderFactory.createEmptyBorder());
        btnDelete.setBounds(100,30,40,40);
        btnDelete.setCursor(new Cursor((Cursor.HAND_CURSOR)));
        btnDelete.setToolTipText("Excluir");
        btnDelete.addActionListener(e -> {
            try {
                Integer linha = tabela.getSelectedRow();
                String id = tabela.getModel().getValueAt(linha, 0).toString();
                deletar(id);
            }catch (ArrayIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null,"Selecione um registro para deletar!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        panel.add(btnDelete);
        panel.add(btnCadastrar);
    }

    private JTable criaTabela() {
        JTable tabela = new JTable();
        tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabela.setRowHeight(22);

        String[] colunasTabela = getColunasTabela();
        DefaultTableModel tableModel = new DefaultTableModel(colunasTabela, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        montaDadosTabela(tabela, tableModel);
        implementaDoubleClickNasLinhas(tabela);

        return tabela;
    }

    public void recarregaLista() {
        this.removeAll();
        criaPainelDeCima();
        criaPainelDeBaixo();
        this.revalidate();
        this.repaint();
    }

    private void implementaDoubleClickNasLinhas(JTable tabela) {
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    JTable target = (JTable) e.getSource();
                    int linha = target.getSelectedRow();
                    int coluna = 0;
                    String id = tabela.getModel().getValueAt(linha, coluna).toString();

                    onDoubleClickLinha(id);
                }
            }
        });
    }

    private void configuraTabela(JTable tabela){
        TableColumnModel colunas = tabela.getColumnModel();
        colunas.getColumn(0).setMaxWidth(50);

        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(JLabel.CENTER);

        if (tabela.getColumnModel().getColumnCount() == 0) return;

        for (int i = 0; i < tabela.getColumnCount(); i++){
            tabela.getColumnModel().getColumn(i).setCellRenderer(centerRender);
        }

    }

    protected abstract void deletar(String id);

    protected abstract String[] getColunasTabela();

    protected abstract void montaDadosTabela(JTable tabela, DefaultTableModel tableModel);

    protected abstract void onDoubleClickLinha(String id);

    protected abstract void onClickNovo();

}