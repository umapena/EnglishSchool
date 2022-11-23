package graphic.util;

import model.BairrosModel;

import javax.swing.*;
import java.util.ArrayList;

public class BairrosComboModel extends AbstractListModel implements MutableComboBoxModel {
    private ArrayList<BairrosModel> lista;
    private BairrosModel selecionado;

    public BairrosComboModel(ArrayList<BairrosModel> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selecionado = (BairrosModel) anItem;
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
        lista.add((BairrosModel) item);
    }

    @Override
    public void removeElement(Object obj) {
        lista.remove(obj);
    }

    @Override
    public void insertElementAt(Object item, int index) {
        lista.add(index, (BairrosModel) item);
    }

    @Override
    public void removeElementAt(int index) {
        lista.remove(index);
    }
}