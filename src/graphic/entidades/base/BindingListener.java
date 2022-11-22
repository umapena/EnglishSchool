package graphic.entidades.base;

import model.EntidadesModel;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BindingListener implements DocumentListener {
    private EntidadesModel model;
    private String nomeCampo;

    public BindingListener(EntidadesModel model, String nomeCampo) {
        this.model = model;

        String firstChar = String.valueOf(nomeCampo.charAt(0));
        if (firstChar.equals(firstChar.toLowerCase())) {
            nomeCampo = firstChar.toUpperCase() + nomeCampo.substring(1);
        }

        this.nomeCampo = nomeCampo;
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        dataUpdated(e);
    }

    private void dataUpdated(DocumentEvent e) {
        try {
            String text = e.getDocument().getText(
                    e.getDocument().getStartPosition().getOffset(),
                    e.getDocument().getEndPosition().getOffset() - 1);

            Method method = model.getClass().getDeclaredMethod("set" + nomeCampo, String.class);
            method.invoke(model, text);

        } catch (BadLocationException | InvocationTargetException | NoSuchMethodException | SecurityException |
                 IllegalAccessException | IllegalArgumentException e1) {
            e1.printStackTrace();
        }
    }
}