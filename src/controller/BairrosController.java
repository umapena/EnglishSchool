package controller;

import database.dao.BairrosDAO;
import model.BairrosModel;

import java.sql.SQLException;
import java.util.ArrayList;

public class BairrosController {
    private BairrosDAO bairrosDAO;

    public BairrosController() {
        bairrosDAO = new BairrosDAO();
    }

    public ArrayList<BairrosModel> recuperarBairrosPorCidade(Integer idCidade) {
        try {
            return new ArrayList<>(bairrosDAO.selectBairrosByCidade(idCidade));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
