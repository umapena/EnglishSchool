package graphic.util;

import model.EstadosModel;

import javax.swing.*;
import java.awt.*;

public class EstadosComboRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof EstadosModel) {
            EstadosModel estadosModel = (EstadosModel) value;

            setText(estadosModel.getNome());
        } else if (value == null) {
            setText("Selecione um estado...");
        }

        return this;
    }
}