package graphic.entidades.matriculas;

import controller.AlunosController;
import controller.MatriculasController;
import controller.TurmasController;
import graphic.entidades.base.EntidadesCadastro;
import graphic.util.*;
import model.AlunosModel;
import model.MatriculasModel;
import model.TurmasModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class MatriculasCadastro extends EntidadesCadastro {

    private MatriculasModel matriculasModel = new MatriculasModel();
    private MatriculasPanel matriculasPanel;
    private AlunosController alunosController = new AlunosController();

    private TurmasController turmasController = new TurmasController();


    private boolean isEditando = false;
    public MatriculasCadastro(MatriculasPanel matriculasPanel) {
        this.matriculasPanel = matriculasPanel;
        criaComponentes(null);
    }

    public MatriculasCadastro(MatriculasModel dados, MatriculasPanel matriculasPanel) {
        this.matriculasPanel = matriculasPanel;
        criaComponentes(dados);
    }

    private void criaComponentes(MatriculasModel dados){
        setSize(520, 280);
        JPanel subPanel = new JPanel(new GridBagLayout());
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panelPrincipal.setSize(520,280);

        ArrayList<AlunosModel> alunosRecuperados = alunosController.recuperaAlunosParaComboBox();
        AlunosComboModel alunosComboModel = new AlunosComboModel(alunosRecuperados);

        JLabel labelAlunos = new JLabel("Aluno(a): ");
        JComboBox comboBoxAlunos = new JComboBox<>(alunosComboModel);
        comboBoxAlunos.setRenderer(new AlunosComboRender());
        comboBoxAlunos.setPreferredSize(new Dimension(224,20));

        if(isEditando && this.matriculasModel.getIdAluno() != null){
            alunosRecuperados.forEach(alunoRecuperado -> {
                if (Objects.equals(alunoRecuperado.getId(), this.matriculasModel.getIdAluno())) {
                    comboBoxAlunos.setSelectedItem(alunoRecuperado);
                }
            });
            comboBoxAlunos.setEnabled(false);
        }

        comboBoxAlunos.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                AlunosModel item = (AlunosModel) comboBoxAlunos.getSelectedItem();
                matriculasModel.setIdAluno(item.getId());
            }
        });

        ArrayList<TurmasModel> turmasRecuperadas = turmasController.recuperaTurmasParaComboBox();
        TurmasComboModel turmasComboModel = new TurmasComboModel(turmasRecuperadas);

        JLabel labelTurma = new JLabel("Turma: ");
        JComboBox comboBoxTurmas = new JComboBox(turmasComboModel);
        comboBoxTurmas.setRenderer(new TurmasComboRenderer());
        comboBoxTurmas.setPreferredSize(new Dimension(224, 20));

        if(isEditando && this.matriculasModel.getIdTurma() != null){
            turmasRecuperadas.forEach(turmaRecuperada -> {
                if (Objects.equals(turmaRecuperada.getId(), this.matriculasModel.getIdTurma())) {
                    comboBoxTurmas.setSelectedItem(turmaRecuperada);
                }
            });
            comboBoxTurmas.setEnabled(false);
        }

        comboBoxTurmas.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                TurmasModel item = (TurmasModel) comboBoxTurmas.getSelectedItem();
                matriculasModel.setIdTurma(item.getId());
            }
        });

        JLabel labelDiaVencimento = new JLabel("Dia de Vencimento: ");
        int min = 1, value = 1, max = 20, stepSize = 1;
        SpinnerNumberModel numberModel = new SpinnerNumberModel(value, min, max, stepSize);
        JSpinner diaVencimentoSpn = new JSpinner(numberModel);
        diaVencimentoSpn.setPreferredSize(new Dimension(50,20));

        if(isEditando && this.matriculasModel.getDiaVencimento() != null) {
            diaVencimentoSpn.setValue(this.matriculasModel.getDiaVencimento());
        } else {
            matriculasModel.setDiaVencimento((Integer) diaVencimentoSpn.getValue());
        }

        diaVencimentoSpn.addChangeListener(e ->
                matriculasModel.setDiaVencimento((Integer) diaVencimentoSpn.getValue())
        );

//        matriculasModel.setDataMatricula(new java.sql.Date(System.currentTimeMillis()));
//        System.out.println(matriculasModel.getDataMatricula());
//
//        matriculasModel.setDataMatricula(new Date());
//        System.out.println(matriculasModel.getDataMatricula());

        java.util.Date utilDate = new Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        matriculasModel.setDataMatricula(sqlDate);


        c1.insets = new Insets(0, 0, 20, 35);
        c1.gridx = 0; c1.gridy = 0; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(labelAlunos, c1);
        c1.gridx = 1; c1.gridy = 0; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(comboBoxAlunos, c1);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(labelTurma, c1);
        c1.gridx = 1; c1.gridy = 1; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(comboBoxTurmas, c1);
        c1.gridx = 0; c1.gridy = 2; c1.anchor = GridBagConstraints.EAST;
        subPanel.add(labelDiaVencimento, c1);
        c1.gridx = 1; c1.gridy = 2; c1.anchor = GridBagConstraints.WEST;
        subPanel.add(diaVencimentoSpn, c1);

        if (dados != null) {
            isEditando = true;
            matriculasModel.setIdMatricula(dados.getIdMatricula());
            alunosRecuperados.forEach(alunoRecuperado -> {
                if (Objects.equals(alunoRecuperado.getId(), dados.getIdAluno())) {
                    comboBoxAlunos.setSelectedItem(alunoRecuperado);
                }
            });
            comboBoxAlunos.setEnabled(false);
            turmasRecuperadas.forEach(turmaRecuperada -> {
                if (Objects.equals(turmaRecuperada.getId(), dados.getIdTurma())) {
                    comboBoxTurmas.setSelectedItem(turmaRecuperada);
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
        MatriculasController matriculasController = new MatriculasController();

        if (!isEditando) {
            matriculasController.inserir(matriculasModel, this);
        } else {
            matriculasController.editar(matriculasModel, this);
        }

        matriculasPanel.recarregaLista();
    }
}
