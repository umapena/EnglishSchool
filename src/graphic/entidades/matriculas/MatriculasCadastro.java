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
        setSize(520, 350);
        criaComponentes(null);
    }

    public MatriculasCadastro(MatriculasModel dados, MatriculasPanel matriculasPanel) {
        this.matriculasPanel = matriculasPanel;
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

    private void criaComponentes(MatriculasModel dados){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c1 = new GridBagConstraints();
        panel.setSize(520, 200);

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


        c1.insets = new Insets(0, 0, 30, 35);
        c1.gridx = 0; c1.gridy = 0; c1.anchor = GridBagConstraints.EAST;
        panel.add(labelAlunos, c1);
        c1.gridx = 1; c1.gridy = 0; c1.anchor = GridBagConstraints.WEST;
        panel.add(comboBoxAlunos, c1);
        c1.gridx = 0; c1.gridy = 1; c1.anchor = GridBagConstraints.EAST;
        panel.add(labelTurma, c1);
        c1.gridx = 1; c1.gridy = 1; c1.anchor = GridBagConstraints.WEST;
        panel.add(comboBoxTurmas, c1);
        c1.gridx = 0; c1.gridy = 2; c1.anchor = GridBagConstraints.EAST;
        panel.add(labelDiaVencimento, c1);
        c1.gridx = 1; c1.gridy = 2; c1.anchor = GridBagConstraints.WEST;
        panel.add(diaVencimentoSpn, c1);

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

        add(panel);
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
