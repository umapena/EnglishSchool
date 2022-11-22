package controller;

import database.dao.MatriculasDAO;
import model.MatriculasModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatriculasController {
    private MatriculasDAO matriculasDAO;

    public MatriculasController() {
        matriculasDAO = new MatriculasDAO();
    }

    public List<Object> recuperarTodos() {
        try {
            return matriculasDAO.select();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as matriculas!");
            throw new RuntimeException(e);
        }
    }

    public void inserir(Object matricula, JDialog cadastro) {
        try {
            matriculasDAO.insert(matricula);
            cadastro.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inserir a matricula!");
        }
    }

    public void editar(Object matricula, JDialog cadastro) {
        try {
            matriculasDAO.update(matricula);
            cadastro.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao editar a matricula!");
        }
    }

    public MatriculasModel recuperarPorId(MatriculasModel matricula) {
        try {
            return matriculasDAO.selectById(matricula);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar a matricula!");
            throw new RuntimeException(e);
        }
    }

    public void deletar(MatriculasModel matricula){
        try {
            matriculasDAO.delete(matricula);
        }   catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao deletar a matricula!");
        }
    }

    public void encerrar(MatriculasModel matriculasModel){
        try{
            matriculasDAO.encerrarMatricula(matriculasModel);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao encerrar a matricula!");
        }
    }

    public ArrayList<MatriculasModel> recuperaMatriculasParaComboBox() {
        ArrayList<MatriculasModel> listaMatriculas = new ArrayList<>();

        try {
            List<Object> matriculasRecuperar = matriculasDAO.select();
            matriculasRecuperar.forEach(matricula -> listaMatriculas.add((MatriculasModel) matricula));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as matrículas!");
            throw new RuntimeException(e);
        }

        return listaMatriculas;
    }
}