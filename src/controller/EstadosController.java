package controller;

import database.dao.EstadosDAO;
import model.EstadosModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadosController {
    private EstadosDAO estadosDAO;

    public EstadosController() {
        estadosDAO = new EstadosDAO();
    }

    public ArrayList<EstadosModel> recuperarTodos() {
        List<Object> estadosBanco;
        ArrayList<EstadosModel> arrayListEstados = new ArrayList<>();

        try {
            estadosBanco = estadosDAO.select();

            estadosBanco.forEach(estado -> {
                arrayListEstados.add((EstadosModel) estado);
            });

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return arrayListEstados;
    }
}
