package graphic.entidades.turmas;

import controller.TurmasController;
import graphic.entidades.base.EntidadesPanel;
import model.TurmasModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

public class TurmasPanel extends EntidadesPanel {
    public TurmasPanel(JFrame cmpPai) {
        super(cmpPai);
    }

    @Override
    protected String getTitulo() {
        return "Turmas";
    }

    @Override
    protected void deletar(String id) {
        TurmasModel turmasModel = new TurmasModel();
        TurmasController turmasController = new TurmasController();

        Integer idDeletar = Integer.parseInt(id);

        turmasModel.setId(idDeletar);
        turmasController.deletar(turmasModel);

        this.recarregaLista();
    }

    @Override
    protected String[] getColunasTabela() {
        return new String[]{"Id", "Nome", "Nível", "Período", "Valor"};
    }

    @Override
    protected void montaDadosTabela(JTable tabela, DefaultTableModel tableModel) {
        TurmasController turmasController = new TurmasController();
        List<Object> turmasBanco = turmasController.recuperarTodos();
        List<TurmasModel> listaTurmas = new ArrayList<>();

        turmasBanco.forEach(modalidade -> listaTurmas.add((TurmasModel) modalidade ));

        for(int i = 0; i < listaTurmas.size(); i++){
            Integer id = listaTurmas.get(i).getId();
            String nome = listaTurmas.get(i).getNome();
            String nivel = listaTurmas.get(i).getNivel();
            String periodo = listaTurmas.get(i).getPeriodo();
            Double valor = listaTurmas.get(i).getValor();

            Object[] linha = {id, nome, nivel, periodo, valor};

            tableModel.addRow(linha);
        }

        tabela.setModel(tableModel);
    }

    @Override
    protected void onDoubleClickLinha(String id) {
        TurmasController turmasController = new TurmasController();
        TurmasModel turmasRecuperar = new TurmasModel();

        turmasRecuperar.setId(Integer.parseInt(id));
        turmasRecuperar = turmasController.recuperarPorID(turmasRecuperar);

        TurmasCadastro turmasCadastro = new TurmasCadastro(turmasRecuperar, this);
        turmasCadastro.setVisible(true);
    }

    @Override
    protected void onClickNovo() {
        TurmasCadastro turmasCadastro = new TurmasCadastro(this);

        turmasCadastro.setVisible(true);
    }

}
