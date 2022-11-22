package graphic.entidades.faturas;

import controller.FaturasController;
import graphic.entidades.base.EntidadesPanel;
import model.FaturasModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FaturasPanel extends EntidadesPanel {

    public FaturasPanel(JFrame cmpPai) { super(cmpPai); }

    @Override
    protected String getTitulo() {
        return "Financeiro";
    }

    @Override
    protected void deletar(String id) {

    }

    protected void deletar(Integer codigoMatricula, Date dataVencimento) {
        FaturasModel faturasModel = new FaturasModel();
        FaturasController faturasController = new FaturasController();

        faturasModel.setIdMatricula(codigoMatricula);
        faturasModel.setDataVencimento(dataVencimento);
        faturasController.deletar(faturasModel);

        this.recarregaLista();
    }

    @Override
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
                Integer codigoMatricula = (Integer) tabela.getModel().getValueAt(linha, 0);
                Date dataVencimento = new Date();
                try {
                    SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
                    dataVencimento = (Date) f.parse((String) tabela.getModel().getValueAt(linha,1));
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }

                deletar(codigoMatricula, dataVencimento);
            }catch (ArrayIndexOutOfBoundsException ex){
                JOptionPane.showMessageDialog(null,"Selecione um registro para deletar!", "Atenção", JOptionPane.INFORMATION_MESSAGE);
            }
        });


        panel.add(btnDelete);
        panel.add(btnCadastrar);
    }

    @Override
    protected String[] getColunasTabela() {
        return new String[]{"Matrícula", "Data de Vencimento", "Valor", "Data de Pagamento", "Data de Cancelamento"};
    }

    @Override
    protected void montaDadosTabela(JTable tabela, DefaultTableModel tableModel) {
        FaturasController faturasController = new FaturasController();
        List<Object> faturasBanco = faturasController.recuperarTodos();
        List<FaturasModel> listaFaturas = new ArrayList<>();

        faturasBanco.forEach(faturas -> listaFaturas.add((FaturasModel) faturas));

        for (int i = 0; i < listaFaturas.size(); i++) {
            Integer idMatricula = listaFaturas.get(i).getIdMatricula();
            String dataVencimento = String.valueOf(listaFaturas.get(i).getDataVencimento());
            Double valor = listaFaturas.get(i).getValor();
            Date dataPagamento = (listaFaturas.get(i).getDataPagamento());
            Date dataCancelamento = (listaFaturas.get(i).getDataCancelamento());

            Object[] linha = { idMatricula, dataVencimento, valor, dataPagamento, dataCancelamento };

            tableModel.addRow(linha);
        }

        tabela.setModel(tableModel);
    }

    @Override
    protected void onDoubleClickLinha(String id) {

    }

    protected void onDoubleClickLinha(Integer codigoMatricula, Date dataVencimento) {
        FaturasController faturasController = new FaturasController();
        FaturasModel faturasMatriculasRecuperar = new FaturasModel();

        faturasMatriculasRecuperar.setIdMatricula(codigoMatricula);
        faturasMatriculasRecuperar.setDataVencimento(dataVencimento);
        faturasMatriculasRecuperar = faturasController.recuperarPorId(faturasMatriculasRecuperar);

        FaturasCadastro faturasCadastro = new FaturasCadastro(faturasMatriculasRecuperar, this);

        faturasCadastro.setVisible(true);
    }

    @Override
    protected void onClickNovo() {
        FaturasCadastro faturasCadastro = new FaturasCadastro(this);
        faturasCadastro.setVisible(true);
    }

}

