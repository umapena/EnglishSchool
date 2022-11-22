package graphic.entidades.turmas;

import controller.TurmasController;
import graphic.entidades.base.BindingListener;
import graphic.entidades.base.EntidadesCadastro;
import model.TurmasModel;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.Objects;

public class TurmasCadastro extends EntidadesCadastro {
    private TurmasModel turmasModel = new TurmasModel();
    private TurmasPanel turmasPanel;

    private boolean isEditando = false;
    public TurmasCadastro(TurmasPanel turmasPanel) {
        this.turmasPanel = turmasPanel;
        setSize(520, 350);
        criaComponentes(null);
    }

    public TurmasCadastro(TurmasModel dados, TurmasPanel turmasPanel) {
        this.turmasPanel = turmasPanel;
        setSize(520, 350);
        criaComponentes(dados);
    }

    @Override
    public void criarBotoes() {
        JButton btnFechar = new JButton("Cancelar");
        btnFechar.setBounds(80,200,120,30);
        btnFechar.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnFechar.setBackground(new Color(255,92,92));
        btnFechar.setBorder(BorderFactory.createLineBorder(new Color(255, 92, 92)));
        add(btnFechar);

        btnFechar.addActionListener(e -> {
            dispose();
        });

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(290,200,120,30);;
        btnSalvar.addActionListener(e -> onClickSalvar());
        btnSalvar.setFont(new Font("Helvetica", Font.BOLD, 16));
        btnSalvar.setBackground(new Color(87, 241, 87, 210));
        btnSalvar.setBorder(BorderFactory.createLineBorder(new Color(87, 241, 87, 210)));
        add(btnSalvar);
    }

    private void criaComponentes(TurmasModel dados){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panel.setSize(520, 250);

        JLabel nome = new JLabel("Nome: ");
        JTextField nomeTxf = new JTextField(20);
        nomeTxf.getDocument().addDocumentListener(new BindingListener(turmasModel, "nome"));

        JLabel nivel = new JLabel("Nível: ");
        String[] nivelString = { "Iniciante", "Intermediário", "Avançado" };
        JComboBox<String> nivelCmbox = new JComboBox<>(nivelString);
        nivelCmbox.addActionListener(e -> {
            String valor = Objects.requireNonNull(nivelCmbox.getSelectedItem()).toString();
            switch (valor){
                case "Iniciante": turmasModel.setNivel("Iniciante"); break;
                case "Intermediário": turmasModel.setNivel("Intermediário"); break;
                case "Avançado": turmasModel.setNivel("Avançado"); break;
            }
        });
        turmasModel.setNivel("Iniciante");

        JLabel periodo = new JLabel("Período: ");
        String[] periodoString = { "Matutino", "Vespertino", "Noturno" };
        JComboBox<String> periodoCmbox = new JComboBox<>(periodoString);
        periodoCmbox.addActionListener(e -> {
            String valor = Objects.requireNonNull(periodoCmbox.getSelectedItem()).toString();
            switch (valor){
                case "Matutino": turmasModel.setPeriodo("Matutino"); break;
                case "Vespertino": turmasModel.setPeriodo("Vespertino"); break;
                case "Noturno": turmasModel.setPeriodo("Noturno"); break;
            }
        });
        turmasModel.setPeriodo("Matutino");

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
        valorSpinner.addChangeListener(e -> turmasModel.setValor((Double) valorSpinner.getValue()));
        turmasModel.setValor((Double) valorSpinner.getValue());

        c1.insets = new Insets(0, 0, 30, 35);
        c1.gridx = 0; c1.gridy = 0; c1.anchor = GridBagConstraints.NORTHEAST;
        panel.add(nome, c1);
        c1.gridx = 1; c1.gridy = 0;
        panel.add(nomeTxf, c1);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.NORTHEAST;
        panel.add(nivel, c1);
        c1.gridx = 1; c1.gridy = 1;
        panel.add(nivelCmbox, c1);
        c1.gridx = 0; c1.gridy = 2; c1.anchor = GridBagConstraints.NORTHEAST;
        panel.add(periodo, c1);
        c1.gridx = 1; c1.gridy = 2;
        panel.add(periodoCmbox, c1);
        c1.gridx = 0; c1.gridy = 3; c1.anchor = GridBagConstraints.NORTHEAST;
        panel.add(valor, c1);
        c1.gridx = 1; c1.gridy = 3;
        panel.add(valorSpinner, c1);


        if (dados != null) {
            isEditando = true;
            turmasModel.setId(dados.getId());
            nomeTxf.setText(dados.getNome());
            switch (dados.getNivel()){
                case "Iniciante": nivelCmbox.setSelectedIndex(0); break;
                case "Intermediário": nivelCmbox.setSelectedIndex(1); break;
                case "Avançado": nivelCmbox.setSelectedIndex(2); break;
            }
            switch (dados.getPeriodo()){
                case "Matutino": periodoCmbox.setSelectedIndex(0); break;
                case "Vespertino": periodoCmbox.setSelectedIndex(1); break;
                case "Noturno": periodoCmbox.setSelectedIndex(2); break;
            }
        }

        add(panel);
    }

    @Override
    protected void onClickSalvar() {
        TurmasController turmasController = new TurmasController();

        if (!isEditando) {
            turmasController.inserir(turmasModel, this);
        } else {
            turmasController.editar(turmasModel, this);
        }

        turmasPanel.recarregaLista();
    }
}
