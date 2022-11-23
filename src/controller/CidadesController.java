package controller;

import database.dao.CidadesDAO;
import model.CidadesModel;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadesController {
    private CidadesDAO cidadesDAO;

    public CidadesController() {
        cidadesDAO = new CidadesDAO();
    }

    public ArrayList<CidadesModel> recuperarCidadesByEstado(Integer idEstado) {
        try {
            return cidadesDAO.selectCidadesByEstado(idEstado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as cidades do Estado!");
            throw new RuntimeException(e);
        }
    }
}
