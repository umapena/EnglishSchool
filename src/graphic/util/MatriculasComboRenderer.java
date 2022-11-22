package graphic.util;

import model.MatriculasModel;

import javax.swing.*;
import java.awt.*;

public class MatriculasComboRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof MatriculasModel) {
            MatriculasModel matriculasModel = (MatriculasModel) value;

            setText(String.valueOf(matriculasModel.getIdMatricula()));
        } else if (value == null) {
            setText("Selecione uma matrícula...");
        }

        return this;
    }
}
