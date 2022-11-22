package graphic.util;

import model.TurmasModel;

import javax.swing.*;
import java.util.ArrayList;

public class TurmasComboModel extends AbstractListModel implements ComboBoxModel {
    private ArrayList<TurmasModel> lista;
    private TurmasModel selecionado;

    public TurmasComboModel(ArrayList<TurmasModel> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selecionado = (TurmasModel) anItem;
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
}
