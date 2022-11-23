package graphic.util;

import model.CidadesModel;

import javax.swing.*;
import java.util.ArrayList;

public class CidadesComboModel extends AbstractListModel implements MutableComboBoxModel {
    private ArrayList<CidadesModel> lista;
    private CidadesModel selecionado;

    public CidadesComboModel(ArrayList<CidadesModel> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selecionado = (CidadesModel) anItem;
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

    @Override
    public void addElement(Object item) {
        lista.add((CidadesModel) item);
    }

    @Override
    public void removeElement(Object obj) {
        lista.remove(obj);
    }

    @Override
    public void insertElementAt(Object item, int index) {
        lista.add(index, (CidadesModel) item);
    }

    @Override
    public void removeElementAt(int index) {
        lista.remove(index);
    }

}