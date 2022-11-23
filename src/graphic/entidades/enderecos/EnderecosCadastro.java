package graphic.entidades.enderecos;

import controller.BairrosController;
import controller.CidadesController;
import controller.EnderecosController;
import controller.EstadosController;
import graphic.entidades.alunos.AlunosCadastro;
import graphic.entidades.base.BindingListener;
import graphic.entidades.base.EntidadesCadastro;
import graphic.util.*;
import model.*;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Objects;

public class EnderecosCadastro extends EntidadesCadastro {
    private EnderecosModel enderecosModel = new EnderecosModel();
    private AlunosCadastro alunosCadastro;
    private AlunosModel alunosModel;
    private EstadosController estadosController = new EstadosController();
    private CidadesController cidadesController = new CidadesController();
    private BairrosController bairrosController = new BairrosController();

    public EnderecosCadastro(AlunosCadastro alunosCadastro, AlunosModel alunosModel, EnderecosModel dados) {
        this.alunosCadastro = alunosCadastro;
        this.alunosModel = alunosModel;
        criaComponentes(dados);
    }

    private void criaComponentes(EnderecosModel dados) {
        setSize(520,360);
        JPanel subPanel = new JPanel(new GridBagLayout());
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panelPrincipal.setSize(520,360);

        MaskFormatter mascaraCep = null;
        try {
            mascaraCep = new MaskFormatter("########");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        ArrayList<EstadosModel> estadosRecuperados = estadosController.recuperarTodos();
        EstadosComboModel estadosComboModel = new EstadosComboModel(estadosRecuperados);
        JComboBox comboBoxEstados = new JComboBox(estadosComboModel);
        comboBoxEstados.setRenderer(new EstadosComboRenderer());
        comboBoxEstados.setPreferredSize(new Dimension(224, 20));

        ArrayList<CidadesModel> cidadesRecuperadas = new ArrayList<>();
        CidadesComboModel cidadesComboModel = new CidadesComboModel(cidadesRecuperadas);
        JComboBox comboBoxCidades = new JComboBox(cidadesComboModel);
        comboBoxCidades.setRenderer(new CidadesComboRenderer());
        comboBoxCidades.setPreferredSize(new Dimension(224, 20));

        ArrayList<BairrosModel> bairrosRecuperados = new ArrayList<>();
        BairrosComboModel bairrosComboModel = new BairrosComboModel(bairrosRecuperados);
        JComboBox comboBoxBairros = new JComboBox(bairrosComboModel);
        comboBoxBairros.setRenderer(new BairrosComboRenderer());
        comboBoxBairros.setPreferredSize(new Dimension(224, 20));

        comboBoxEstados.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                EstadosModel estadoSelecionado = (EstadosModel) comboBoxEstados.getSelectedItem();
                comboBoxCidades.setSelectedIndex(-1);
                comboBoxCidades.removeAllItems();
                ArrayList<CidadesModel> cidadesDoEstado = cidadesController.recuperarCidadesByEstado(estadoSelecionado.getId());

                cidadesDoEstado.forEach(cidade -> {
                    comboBoxCidades.addItem(cidade);
                });
            }
        });

        comboBoxCidades.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                CidadesModel cidadeSelecionada = (CidadesModel) comboBoxCidades.getSelectedItem();
                comboBoxBairros.setSelectedIndex(-1);
                comboBoxBairros.removeAllItems();
                ArrayList<BairrosModel> bairrosDaCidade = bairrosController.recuperarBairrosPorCidade(cidadeSelecionada.getId());

                bairrosDaCidade.forEach(bairro -> {
                    comboBoxBairros.addItem(bairro);
                });
            }
        });

        comboBoxBairros.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                BairrosModel bairroSelecionado = (BairrosModel) comboBoxBairros.getSelectedItem();
                enderecosModel.setIdBairro(bairroSelecionado.getId());
            }
        });

        JLabel estado = new JLabel("Estado: ");
        JLabel cidade = new JLabel("Cidade: ");
        JLabel bairro = new JLabel("Bairro: ");

        JLabel cep = new JLabel("CEP: ");
        JFormattedTextField cepTxf = new JFormattedTextField();
        cepTxf.setColumns(20);
        cepTxf.getDocument().addDocumentListener(new BindingListener(enderecosModel, "cep"));
        mascaraCep.install(cepTxf);

        JLabel logradouro = new JLabel("Logradouro: ");
        JTextField logradouroTxf = new JTextField(20);
        logradouroTxf.getDocument().addDocumentListener(new BindingListener(enderecosModel, "logradouro"));

        JLabel numero = new JLabel("Número: ");
        Integer min = 0, value = 0, max = 999999, stepSize = 1;
        SpinnerNumberModel numberModel = new SpinnerNumberModel(value, min, max, stepSize);
        JSpinner numeroSpinner = new JSpinner(numberModel);
        JSpinner.NumberEditor editor2 = (JSpinner.NumberEditor)numeroSpinner.getEditor();
        editor2.getTextField().setHorizontalAlignment(SwingConstants.CENTER);
        numeroSpinner.setPreferredSize(new Dimension(120, 20));
        numeroSpinner.addChangeListener(e -> enderecosModel.setNumero((Integer) numeroSpinner.getValue()));
        enderecosModel.setNumero((Integer) numeroSpinner.getValue());

        c1.insets = new Insets(0, 0, 15, 35);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(estado, c1);
        c1.gridx = 1; c1.gridy = 1;
        subPanel.add(comboBoxEstados, c1);
        c1.gridx = 0; c1.gridy = 2;
        subPanel.add(cidade, c1);
        c1.gridx = 1; c1.gridy = 2;
        subPanel.add(comboBoxCidades, c1);
        c1.gridx = 0; c1.gridy = 3;
        subPanel.add(bairro, c1);
        c1.gridx = 1; c1.gridy = 3;
        subPanel.add(comboBoxBairros, c1);
        c1.gridx = 0; c1.gridy = 4;
        subPanel.add(cep, c1);
        c1.gridx = 1; c1.gridy = 4;
        subPanel.add(cepTxf, c1);
        c1.gridx = 0; c1.gridy = 5;
        subPanel.add(logradouro, c1);
        c1.gridx = 1; c1.gridy = 5;
        subPanel.add(logradouroTxf, c1);
        c1.gridx = 0; c1.gridy = 6;
        subPanel.add(numero, c1);
        c1.gridx = 1; c1.gridy = 6;  c1.anchor = GridBagConstraints.WEST;;
        subPanel.add(numeroSpinner, c1);

        if (dados.getId() != null) {
            enderecosModel.setId(dados.getId());
            enderecosModel.setIdBairro(dados.getIdBairro());
            cepTxf.setText(dados.getCep());
            logradouroTxf.setText(dados.getLogradouro());
            numeroSpinner.setValue(dados.getNumero());

            for (int i = 0; i < comboBoxEstados.getItemCount(); i++) {
                EstadosModel es = (EstadosModel) comboBoxEstados.getItemAt(i);

                if (Objects.equals(es.getId(), dados.getIdEstado())) {
                    comboBoxEstados.setSelectedItem(es);
                    break;
                }
            }

            for (int i = 0; i < comboBoxCidades.getItemCount(); i++) {
                CidadesModel cs = (CidadesModel) comboBoxCidades.getItemAt(i);

                if (Objects.equals(cs.getId(), dados.getIdCidade())) {
                    comboBoxCidades.setSelectedItem(cs);
                    break;
                }
            }

            for (int i = 0; i < comboBoxBairros.getItemCount(); i++) {
                    BairrosModel bs = (BairrosModel) comboBoxBairros.getItemAt(i);

                if (Objects.equals(bs.getId(), dados.getIdBairro())) {
                    comboBoxBairros.setSelectedItem(bs);
                    break;
                }
            }
        }

        c1.gridx = 0; c1.gridy = 0; c1.insets = new Insets(0,0,25,0);
        panelPrincipal.add(subPanel, c1);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.CENTER;
        panelPrincipal.add(panelBotoes, c1);

        add(panelPrincipal);
    }

    @Override
    protected void onClickSalvar() {
        EnderecosController enderecosController = new EnderecosController();

        if (enderecosModel.getId() != null) {
            enderecosController.editar(enderecosModel);
        } else {
            Integer idSalvo = enderecosController.salvar(enderecosModel);
            alunosModel.setIdEndereco(idSalvo);
        }

        alunosCadastro.limpaTela();
        alunosCadastro.criaComponentes(alunosModel);
        dispose();
    }

}
