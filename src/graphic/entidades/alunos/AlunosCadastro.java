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
        JPanel subPanel = new JPanel(new GridBagLayout());
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panelPrincipal.setSize(520,430);

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

        JLabel endereco = new JLabel("Endereço: ");
        JComboBox <String> enderecoCmbox = new JComboBox<>(new String[]{"Ameixa", "Rua Bacana na Casa do seu José Primo do"});
        enderecoCmbox.setPreferredSize(new Dimension(185, 25));
        ImageIcon smbMais = new ImageIcon(this.getClass().getResource("/resources/icons/plusIconEndereco.png"));
        JButton addEnderecoBtn = new JButton(smbMais);
        addEnderecoBtn.setBackground(Color.WHITE);
        addEnderecoBtn.setBorder(BorderFactory.createEmptyBorder());
        addEnderecoBtn.setOpaque(false);
        addEnderecoBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addEnderecoBtn.setToolTipText("Novo Endereço");
        addEnderecoBtn.addActionListener(e ->{
            JOptionPane.showMessageDialog(null, "Tela Cadasto Endereço Aqui");
        });

        c1.insets = new Insets(0, 0, 10, 35);
        c1.gridx = 0; c1.gridy = 0; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(nome, c1);
        c1.gridx = 1; c1.gridy = 0;
        subPanel.add(nomeTxf, c1);
        c1.gridx = 0; c1.gridy = 1;
        subPanel.add(dataNascimento, c1);
        c1.gridx = 1; c1.gridy = 1; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(dateSpinner, c1);
        c1.gridx = 0; c1.gridy = 2; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(cpf, c1);
        c1.gridx = 1; c1.gridy = 2;
        subPanel.add(cpfTxf, c1);
        c1.gridx = 0; c1.gridy = 3; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(sexo, c1);
        c1.gridx = 1; c1.gridy = 3; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(sexoCmbox, c1);
        c1.gridx = 0; c1.gridy = 4; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(celular, c1);
        c1.gridx = 1; c1.gridy = 4; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(celularTxf, c1);
        c1.gridx = 0; c1.gridy = 5; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(email, c1);
        c1.gridx = 1; c1.gridy = 5; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(emailTxf, c1);
        c1.gridx = 0; c1.gridy = 6; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(endereco, c1);
        c1.gridx = 1; c1.gridy = 6; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(enderecoCmbox, c1);
        c1.gridx = 2; c1.gridy = 6; c1.insets = new Insets(-10,-65,0,0);
        subPanel.add(addEnderecoBtn, c1);

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
        }

        if(isReadOnly){
            celularTxf.setEditable(false);
            celularTxf.setBorder(BorderFactory.createEmptyBorder());
            emailTxf.setEditable(false);
            emailTxf.setBorder(BorderFactory.createEmptyBorder());
            enderecoCmbox.setEnabled(false);
            enderecoCmbox.setBorder(BorderFactory.createEmptyBorder());
            nomeTxf.setEditable(false);
            nomeTxf.setBorder(BorderFactory.createEmptyBorder());
            dateSpinner.setEnabled(false);
            dateSpinner.setBorder(BorderFactory.createEmptyBorder());
            sexoCmbox.setEnabled(false);
            sexoCmbox.setBorder(BorderFactory.createEmptyBorder());
            cpfTxf.setEditable(false);
            cpfTxf.setBorder(BorderFactory.createEmptyBorder());
        }

        c1.gridx = 0; c1.gridy = 0; c1.insets = new Insets(0,0,25,0);
        panelPrincipal.add(subPanel, c1);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelBotoes, c1);

        add(panelPrincipal);
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
