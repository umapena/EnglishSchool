package graphic.util;

import model.AlunosModel;

import javax.swing.*;
import java.awt.*;

public class AlunosComboRender extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof AlunosModel) {
            AlunosModel alunosModel = (AlunosModel) value;

            setText(alunosModel.getNome());
        } else if (value == null) {
            setText("Selecione um aluno...");
        }

        return this;
    }
}