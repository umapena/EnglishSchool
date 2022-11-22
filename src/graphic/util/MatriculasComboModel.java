package graphic.util;

import model.MatriculasModel;

import javax.swing.*;
import java.util.ArrayList;

public class MatriculasComboModel extends AbstractListModel implements ComboBoxModel {
    private ArrayList<MatriculasModel> lista;
    private MatriculasModel selecionado;

    public MatriculasComboModel(ArrayList<MatriculasModel> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selecionado = (MatriculasModel) anItem;
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
