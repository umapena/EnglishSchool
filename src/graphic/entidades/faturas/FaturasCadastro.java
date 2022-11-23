package graphic.entidades.faturas;

import controller.FaturasController;
import controller.MatriculasController;
import graphic.entidades.base.EntidadesCadastro;
import graphic.util.MatriculasComboModel;
import graphic.util.MatriculasComboRenderer;
import model.FaturasModel;
import model.MatriculasModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;

public class FaturasCadastro extends EntidadesCadastro {

    private FaturasModel faturasModel = new FaturasModel();
    private FaturasPanel faturasPanel;

    private boolean isEditando = false;
    public FaturasCadastro(FaturasPanel faturasPanel) {
        this.faturasPanel = faturasPanel;
        criaComponentes(null);
    }
    public FaturasCadastro(FaturasModel dados, FaturasPanel faturasPanel) {
        this.faturasPanel = faturasPanel;
        criaComponentes(dados);
    }

    private void criaComponentes(FaturasModel dados){
        setSize(520, 280);
        JPanel subPanel = new JPanel(new GridBagLayout());
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panelPrincipal.setSize(520,280);

        MatriculasController matriculasController = new MatriculasController();
        ArrayList<MatriculasModel> matriculasRecuperadas = matriculasController.recuperaMatriculasParaComboBox();
        MatriculasComboModel matriculasComboModel = new MatriculasComboModel(matriculasRecuperadas);

        JLabel matriculas = new JLabel("Código de Matrícula: ");
        JComboBox comboBoxMatriculas = new JComboBox(matriculasComboModel);
        comboBoxMatriculas.setRenderer(new MatriculasComboRenderer());
        comboBoxMatriculas.setPreferredSize(new Dimension(224, 20));

        comboBoxMatriculas.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                MatriculasModel item = (MatriculasModel) comboBoxMatriculas.getSelectedItem();

                faturasModel.setIdMatricula(item.getIdMatricula());
            }
        });

        JLabel dataVencimento = new JLabel("Data de Vencimento: ");
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        JComponent editor = dateSpinner.getEditor();
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        Dimension d = new Dimension(120,20);
        dateSpinner.setPreferredSize(d);
        dateSpinner.addChangeListener(e -> faturasModel.setDataVencimento((Date) dateSpinner.getValue()));
        faturasModel.setDataVencimento(new Date());

        JLabel valor = new JLabel("Valor: ");
        double min = 0000.00, value = 50.00, max = 1000.00, stepSize = 1.00;
        SpinnerNumberModel numberModel = new SpinnerNumberModel(value, min, max, stepSize);
        JSpinner valorSpinner = new JSpinner(numberModel);
        JSpinner.NumberEditor editor2 = (JSpinner.NumberEditor)valorSpinner.getEditor();
        DecimalFormat format = editor2.getFormat();
        format.setMinimumFractionDigits(2);
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(new Locale("pt-BR")));
        editor2.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        valorSpinner.setPreferredSize(new Dimension(120, 20));
        valorSpinner.addChangeListener(e -> faturasModel.setValor((Double) valorSpinner.getValue()));
        faturasModel.setValor((Double) valorSpinner.getValue());


        c1.insets = new Insets(0, 0, 20, 35);
        c1.gridx = 0; c1.gridy = 0;
        subPanel.add(matriculas,c1);
        c1.gridx = 1; c1.gridy = 0;
        subPanel.add(comboBoxMatriculas,c1);
        c1.gridx = 0; c1.gridy = 1;
        subPanel.add(dataVencimento,c1);
        c1.gridx = 1; c1.gridy = 1;
        subPanel.add(dateSpinner,c1);
        c1.gridx = 0; c1.gridy = 2;
        subPanel.add(valor,c1);
        c1.gridx = 1; c1.gridy = 2;
        subPanel.add(valorSpinner,c1);


        if (dados != null) {
            isEditando = true;
            faturasModel.setId(dados.getId());
            matriculasRecuperadas.forEach(matriculaRecuperada -> {
                if (Objects.equals(matriculaRecuperada.getIdMatricula(), dados.getIdMatricula())) {
                    comboBoxMatriculas.setSelectedItem(matriculaRecuperada);
                }
            });
        }

        c1.gridx = 0; c1.gridy = 0; c1.insets = new Insets(0,0,15,0);
        panelPrincipal.add(subPanel, c1);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.CENTER; c1.insets = new Insets(0,0,25,0);
        panelPrincipal.add(panelBotoes, c1);

        add(panelPrincipal);
    }

    @Override
    protected void onClickSalvar() {
        FaturasController faturasController = new FaturasController();

        if (!isEditando) {
            faturasController.inserir(faturasModel, this);
        } else {
            faturasController.editar(faturasModel, this);
        }

        faturasPanel.recarregaLista();
    }
}
