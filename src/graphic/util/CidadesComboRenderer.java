package graphic.util;

import model.CidadesModel;

import javax.swing.*;
import java.awt.*;

public class CidadesComboRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof CidadesModel) {
            CidadesModel cidadesModel = (CidadesModel) value;

            setText(cidadesModel.getNome());
        } else if (value == null) {
            setText("Selecione uma cidade...");
        }

        return this;
    }
}