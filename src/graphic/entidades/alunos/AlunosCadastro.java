package graphic.entidades.alunos;

import controller.AlunosController;
import controller.CidadesController;
import graphic.entidades.base.BindingListener;
import graphic.entidades.base.EntidadesCadastro;
import model.AlunosModel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.*;
import java.util.List;

public class AlunosCadastro extends EntidadesCadastro {
    private AlunosModel alunosModel = new AlunosModel();
    private AlunosPanel alunosPanel;
    private boolean isReadOnly = false;
    private boolean isEditando = false;

    public AlunosCadastro(AlunosPanel alunosPanel) {
        this.alunosPanel = alunosPanel;
        criaComponentes(null);
    }

    public AlunosCadastro(AlunosModel dados, AlunosPanel alunosPanel) {
        this.alunosPanel = alunosPanel;
        criaComponentes(dados);
    }

    public AlunosCadastro(AlunosModel dados){
        isReadOnly = true;
        criaComponentes(dados);
    }

    private void criaComponentes(AlunosModel dados) {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panel.setSize(520,580);

        MaskFormatter mascaraCelular = null;
        MaskFormatter mascaraCep = null;
        MaskFormatter mascaraNumEndereco = null;
        try {
            mascaraCelular = new MaskFormatter("(##) #####-####");
            mascaraCep = new MaskFormatter("#####-###");
            mascaraNumEndereco = new MaskFormatter("#####");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        CidadesController cidadesController = new CidadesController();

        JLabel nome = new JLabel("Nome: ");
        JTextField nomeTxf = new JTextField(20);
        nomeTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "nome"));

        JLabel dataNascimento = new JLabel("Data de Nascimento: ");
        SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner dateSpinner = new JSpinner(dateModel);
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        JComponent editor = dateSpinner.getEditor();
        JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor)editor;
        spinnerEditor.getTextField().setHorizontalAlignment(JTextField.CENTER);
        Dimension d = new Dimension(120,20);
        dateSpinner.setPreferredSize(d);
        dateSpinner.addChangeListener(e -> alunosModel.setDataNascimento((Date) dateSpinner.getValue()));
        alunosModel.setDataNascimento(new Date());

        //todo: add formatação
        JLabel cpf = new JLabel("CPF: ");
        JTextField cpfTxf = new JTextField(20);
        cpfTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "cpf"));

        JLabel sexo = new JLabel("Sexo: ");
        String[] sexoString = { "Masculino", "Feminino" };
        JComboBox<String> sexoCmbox = new JComboBox<>(sexoString);
        sexoCmbox.addActionListener(e -> {
            String valor = Objects.requireNonNull(sexoCmbox.getSelectedItem()).toString();
            if (valor.equals("Masculino")) {
                alunosModel.setSexo("M");
            } else {
                alunosModel.setSexo("F");
            }

        });
        alunosModel.setSexo("M");

        JLabel celular = new JLabel("Celular: ");
        JFormattedTextField celularTxf = new JFormattedTextField();
        mascaraCelular.install(celularTxf);
        celularTxf.setColumns(9);
        celularTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "celular"));

        JLabel email = new JLabel("Email: ");
        JTextField emailTxf = new JTextField(20);
        emailTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "email"));

        JLabel logradouro = new JLabel("Logradouro: ");
        JTextField logradouroTxf = new JTextField(20);
        logradouroTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "logradouro"));

        JLabel numero = new JLabel("Numero: ");
        JFormattedTextField numeroTxf = new JFormattedTextField();
        mascaraNumEndereco.install(numeroTxf);
        numeroTxf.setColumns(4);
        numeroTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "numero"));
//
//        JLabel complemento = new JLabel("Complemento: ");
//        JTextField complementoTxf = new JTextField(20);
//        complementoTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "complemento"));
//
//        JLabel bairro = new JLabel("Bairro: ");
//        JTextField bairroTxf = new JTextField(20);
//        bairroTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "bairro"));
//
//        JLabel pais = new JLabel("Pais: ");
//        JTextField paisTxf = new JTextField(20);
//        paisTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "pais"));
//        paisTxf.setText("Brasil");
//        paisTxf.setEditable(false);
//
//        JLabel estado = new JLabel("Estado: ");
//        JComboBox comboBoxEstados = new JComboBox(cidadesController.recuperarEstadosBrasileiros().toArray(new String[0]));
//        comboBoxEstados.setPreferredSize(new Dimension(224, 20));
//
//        JLabel cidade = new JLabel("Cidade: ");
//        List<String> cidadesRecuperar = cidadesController.recuperarCidadesByEstado((String) comboBoxEstados.getSelectedItem());
//        JComboBox comboBoxCidades = new JComboBox(cidadesRecuperar.toArray(new String[0]));
//        comboBoxCidades.setPreferredSize(new Dimension(224, 20));

//        alunosModel.setEstado((String) comboBoxEstados.getSelectedItem());
//        alunosModel.setCidade((String) comboBoxCidades.getSelectedItem());

//        comboBoxEstados.addItemListener(e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED) {
//                comboBoxCidades.removeAllItems();
//                List<String> cidadesDoEstado = cidadesController.recuperarCidadesByEstado(Objects.requireNonNull(comboBoxEstados.getSelectedItem()).toString());
//
//                cidadesDoEstado.forEach(cidadeDoEstado -> {
//                    comboBoxCidades.addItem(cidadeDoEstado);
//                });
//
//                alunosModel.setEstado((String) e.getItem());
//            }
//        });
//
//        comboBoxCidades.addItemListener(e -> {
//            if (e.getStateChange() == ItemEvent.SELECTED) {
//                alunosModel.setCidade((String) e.getItem());
//            }
//        });

        JLabel cep = new JLabel("CEP: ");
        JFormattedTextField cepTxf = new JFormattedTextField();
        cepTxf.setColumns(20);
        cepTxf.getDocument().addDocumentListener(new BindingListener(alunosModel, "cep"));
        mascaraCep.install(cepTxf);
//
//        JLabel observacao = new JLabel("Observação: ");
//        JTextArea observacaoTxa = new JTextArea(5, 20);
//        JScrollPane scpObservacao = new JScrollPane(observacaoTxa);
//        observacaoTxa.setWrapStyleWord(true);
//        observacaoTxa.setLineWrap(true);
//        observacaoTxa.getDocument().addDocumentListener(new BindingListener(alunosModel, "observacao"));

        c1.insets = new Insets(0, 0, 10, 35);
        c1.gridx = 0; c1.gridy = 0; c1.anchor = GridBagConstraints.EAST;
        panel.add(nome, c1);
        c1.gridx = 1; c1.gridy = 0;
        panel.add(nomeTxf, c1);
        c1.gridx = 0; c1.gridy = 1;
        panel.add(dataNascimento, c1);
        c1.gridx = 1; c1.gridy = 1; c1.anchor = GridBagConstraints.WEST;
        panel.add(dateSpinner, c1);
        c1.gridx = 0; c1.gridy = 2; c1.anchor = GridBagConstraints.EAST;
        panel.add(cpf, c1);
        c1.gridx = 1; c1.gridy = 2;
        panel.add(cpfTxf, c1);
        c1.gridx = 0; c1.gridy = 3; c1.anchor = GridBagConstraints.EAST;
        panel.add(sexo, c1);
        c1.gridx = 1; c1.gridy = 3; c1.anchor = GridBagConstraints.WEST;
        panel.add(sexoCmbox, c1);
        c1.gridx = 0; c1.gridy = 4; c1.anchor = GridBagConstraints.EAST;
        panel.add(celular, c1);
        c1.gridx = 1; c1.gridy = 4; c1.anchor = GridBagConstraints.WEST;
        panel.add(celularTxf, c1);
        c1.gridx = 0; c1.gridy = 5; c1.anchor = GridBagConstraints.EAST;
        panel.add(email, c1);
        c1.gridx = 1; c1.gridy = 5; c1.anchor = GridBagConstraints.WEST;
        panel.add(emailTxf, c1);

//        panel.add(pais, c1);
//        c1.gridx = 1; c1.gridy = 6;
//        panel.add(paisTxf, c1);
//        c1.gridx = 0; c1.gridy = 7;
//        panel.add(estado, c1);
//        c1.gridx = 1; c1.gridy = 7;
//        panel.add(comboBoxEstados, c1);
//        c1.gridx = 0; c1.gridy = 8;
//        panel.add(cidade, c1);
//        c1.gridx = 1; c1.gridy = 8;
//        panel.add(comboBoxCidades, c1);
//        c1.gridx = 0; c1.gridy = 10;
//        panel.add(bairro, c1);
//        c1.gridx = 1; c1.gridy = 10;
//        panel.add(bairroTxf, c1);
        c1.gridx = 0; c1.gridy = 11; c1.anchor = GridBagConstraints.EAST;
        panel.add(logradouro, c1);
        c1.gridx = 1; c1.gridy = 11;
        panel.add(logradouroTxf, c1);
        c1.gridx = 0; c1.gridy = 12;
        panel.add(numero, c1);
        c1.gridx = 1; c1.gridy = 12; c1.anchor = GridBagConstraints.WEST;
        panel.add(numeroTxf, c1);
        c1.gridx = 0; c1.gridy = 9; c1.anchor = GridBagConstraints.EAST;
        panel.add(cep, c1);
        c1.gridx = 1; c1.gridy = 9; c1.anchor = GridBagConstraints.WEST;
        panel.add(cepTxf, c1);

        if (dados != null) {
            isEditando = true;
            alunosModel.setId(dados.getId());
            nomeTxf.setText(dados.getNome());
            cpfTxf.setText(dados.getCpf());
            dateSpinner.setValue(dados.getDataNascimento());
            if (dados.getSexo().equals("M")) {
                sexoCmbox.setSelectedIndex(0);
            } else if (dados.getSexo().equals("F")) {
                sexoCmbox.setSelectedIndex(1);
            }
            celularTxf.setText(dados.getCelular());
            emailTxf.setText(dados.getEmail());
            cepTxf.setText(dados.getCep());
            logradouroTxf.setText(dados.getLogradouro());
            numeroTxf.setText(dados.getNumero());
//            complementoTxf.setText(dados.getComplemento());
//            bairroTxf.setText(dados.getBairro());
//            comboBoxEstados.setSelectedItem(dados.getEstado());
//            comboBoxCidades.setSelectedItem(dados.getCidade());
//            paisTxf.setText(dados.getPais());
        }

        if(isReadOnly){
            celularTxf.setEditable(false);
            celularTxf.setBorder(BorderFactory.createEmptyBorder());
            emailTxf.setEditable(false);
            emailTxf.setBorder(BorderFactory.createEmptyBorder());
            logradouroTxf.setEditable(false);
            logradouroTxf.setBorder(BorderFactory.createEmptyBorder());
            numeroTxf.setEditable(false);
            numeroTxf.setBorder(BorderFactory.createEmptyBorder());
            cepTxf.setEditable(false);
            cepTxf.setBorder(BorderFactory.createEmptyBorder());
//            bairroTxf.setEditable(false);
//            bairroTxf.setBorder(BorderFactory.createEmptyBorder());
//            comboBoxEstados.setEnabled(false);
//            comboBoxEstados.setBorder(BorderFactory.createEmptyBorder());
//            comboBoxCidades.setEnabled(false);
//            comboBoxCidades.setBorder(BorderFactory.createEmptyBorder());
            //paisTxf.setBorder(BorderFactory.createEmptyBorder());
            nomeTxf.setEditable(false);
            nomeTxf.setBorder(BorderFactory.createEmptyBorder());
            dateSpinner.setEnabled(false);
            dateSpinner.setBorder(BorderFactory.createEmptyBorder());
            sexoCmbox.setEnabled(false);
            sexoCmbox.setBorder(BorderFactory.createEmptyBorder());
            cpfTxf.setEditable(false);
            cpfTxf.setBorder(BorderFactory.createEmptyBorder());
        }

        add(panel);
    }

    @Override
    protected void onClickSalvar() {
        AlunosController alunosController = new AlunosController();

        if (!validaCamposAntesDeSalvar()) return;

        if (!isEditando) {
            alunosController.inserir(alunosModel, this);
        } else {
            alunosController.editar(alunosModel, this);
        }

        alunosPanel.recarregaLista();
    }

    private boolean validaCamposAntesDeSalvar() {
        if (alunosModel.getNome() == null || alunosModel.getNome().trim().length() == 0) {
            JOptionPane.showMessageDialog(null, "Insira um nome válido.");
            return false;
        }

        if(alunosModel.getCpf() == null || alunosModel.getCpf().trim().length() == 0){
            JOptionPane.showMessageDialog(null, "Insira um CPF válido.");
            return false;
        }

        return true;
    }
}
