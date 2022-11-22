package graphic.util;

import model.TurmasModel;

import javax.swing.*;
import java.awt.*;

public class TurmasComboRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof TurmasModel) {
            TurmasModel turmasModel = (TurmasModel) value;

            setText(turmasModel.getNome());
        } else if (value == null) {
            setText("Selecione uma turma...");
        }

        return this;
    }
}
