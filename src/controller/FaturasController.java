package controller;

import database.dao.FaturasDAO;
import model.FaturasModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class FaturasController {

    private FaturasDAO faturasDAO;

    public FaturasController() {
        faturasDAO = new FaturasDAO();}

    public List<Object> recuperarTodos() {
        try {
            return faturasDAO.select();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as faturas!");
            throw new RuntimeException(e);
        }
    }

    public void inserir(Object faturasMatriculas, JDialog cadastro) {
        try {
            faturasDAO.insert(faturasMatriculas);
            cadastro.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inserir a fatura!");
        }
    }

    public void editar(Object faturasMatriculas, JDialog cadastro) {
        try {
            faturasDAO.update(faturasMatriculas);
            cadastro.dispose();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao editar a fatura!");
        }
    }

    public FaturasModel recuperarPorId(FaturasModel faturasMatriculas) {
        try {
            return (FaturasModel) faturasDAO.selectById(faturasMatriculas);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar a fatura!");
            throw new RuntimeException(e);
        }
    }

    public void deletar(FaturasModel faturasMatriculas){
        try {
            faturasDAO.delete(faturasMatriculas);
        }   catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao deletar a fatura!");
        }
    }

    public void encerrarPorMatricula(FaturasModel faturasMatriculas){
        try {
            faturasDAO.updateDataCancelamento(faturasMatriculas);
        }   catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao deletar a fatura!");
        }
    }

}
