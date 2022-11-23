package controller;

import database.dao.EnderecosDAO;
import model.EnderecosModel;

import java.sql.SQLException;

public class EnderecosController {
    private EnderecosDAO enderecosDAO;

    public EnderecosController() {
        enderecosDAO = new EnderecosDAO();
    }

    public EnderecosModel recuperarEnderecoDoAluno(EnderecosModel param) {
        try {
            return enderecosDAO.selectById(param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer salvar(EnderecosModel param) {
        try {
            return enderecosDAO.insertV2(param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editar(EnderecosModel param) {
        try {
            enderecosDAO.update(param);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
