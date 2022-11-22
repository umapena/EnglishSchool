package controller;

import database.dao.TurmasDAO;
import model.TurmasModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TurmasController {
    private TurmasDAO turmasDAO;

    public TurmasController(){
        turmasDAO = new TurmasDAO();
    }

    public List<Object> recuperarTodos() {
        try {
            return turmasDAO.select();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as turmas!");
            throw new RuntimeException(e);
        }
    }

    public TurmasModel recuperarPorID (TurmasModel turma){
        try {
            return turmasDAO.selectById(turma);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar a turma!");
            throw new RuntimeException(e);
        }
    }

    public void deletar(TurmasModel turma){
        try {
            turmasDAO.delete(turma);
        }   catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao deletar a turma!");
        }
    }

    public void inserir(Object turmas, JDialog cadastro) {
        try {
            turmasDAO.insert(turmas);
            cadastro.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inserir a turma!");
        }
    }

    public void editar(Object modalidades, JDialog cadastro) {
        try {
            turmasDAO.update(modalidades);
            cadastro.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao editar a turma!");
        }
    }

    public ArrayList<TurmasModel> recuperaTurmasParaComboBox() {
        ArrayList<TurmasModel> listaTurmas = new ArrayList<>();

        try {
            List<Object> turmasRecuperar = turmasDAO.select();
            turmasRecuperar.forEach(turma -> listaTurmas.add((TurmasModel) turma));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as turmas!");
            throw new RuntimeException(e);
        }

        return listaTurmas;
    }
}
