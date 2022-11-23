package graphic.util;

import model.BairrosModel;

import javax.swing.*;
import java.awt.*;

public class BairrosComboRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof BairrosModel) {
            BairrosModel bairrosModel = (BairrosModel) value;

            setText(bairrosModel.getNome());
        } else if (value == null) {
            setText("Selecione um bairro...");
        }

        return this;
    }
}