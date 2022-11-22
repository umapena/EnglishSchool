package graphic.util;

import model.AlunosModel;

import javax.swing.*;
import java.util.ArrayList;

public class AlunosComboModel extends AbstractListModel implements ComboBoxModel {
    private ArrayList<AlunosModel> lista;
    private AlunosModel selecionado;

    public AlunosComboModel(ArrayList<AlunosModel> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selecionado = (AlunosModel) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return this.selecionado;
    }

    @Override
    public int getSize() {
        return this.lista.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.lista.get(index);
    }

    @Override
    public String toString() {
        return selecionado.getNome();
    }
}