package graphic.util;

import model.EstadosModel;

import javax.swing.*;
import java.util.ArrayList;

public class EstadosComboModel extends AbstractListModel implements MutableComboBoxModel {
    private ArrayList<EstadosModel> lista;
    private EstadosModel selecionado;

    public EstadosComboModel(ArrayList<EstadosModel> lista) {
        this.lista = lista;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        this.selecionado = (EstadosModel) anItem;
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
        lista.add((EstadosModel) item);
    }

    @Override
    public void removeElement(Object obj) {
        lista.remove(obj);
    }

    @Override
    public void insertElementAt(Object item, int index) {
        lista.add(index, (EstadosModel) item);
    }

    @Override
    public void removeElementAt(int index) {
        lista.remove(index);
    }
}