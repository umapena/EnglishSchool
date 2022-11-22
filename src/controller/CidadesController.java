package controller;

import database.dao.CidadesDAO;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadesController {
    private CidadesDAO cidadesDAO;

    public CidadesController() {
        cidadesDAO = new CidadesDAO();
    }

    public List<String> recuperarEstadosBrasileiros() {
        List<String> arrayListEstados = new ArrayList<>();

        arrayListEstados.add("AC");
        arrayListEstados.add("AL");
        arrayListEstados.add("AM");
        arrayListEstados.add("AP");
        arrayListEstados.add("BA");
        arrayListEstados.add("CE");
        arrayListEstados.add("DF");
        arrayListEstados.add("ES");
        arrayListEstados.add("GO");
        arrayListEstados.add("MA");
        arrayListEstados.add("MG");
        arrayListEstados.add("MS");
        arrayListEstados.add("MT");
        arrayListEstados.add("PA");
        arrayListEstados.add("PB");
        arrayListEstados.add("PE");
        arrayListEstados.add("PI");
        arrayListEstados.add("PR");
        arrayListEstados.add("RJ");
        arrayListEstados.add("RN");
        arrayListEstados.add("RO");
        arrayListEstados.add("RR");
        arrayListEstados.add("RS");
        arrayListEstados.add("SC");
        arrayListEstados.add("SE");
        arrayListEstados.add("SP");
        arrayListEstados.add("TO");

        return arrayListEstados;
    }

    public List<String> recuperarCidadesByEstado(String estado) {
        try {
            return cidadesDAO.selectCidadesByEstado(estado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao recuperar as cidades do Estado!");
            throw new RuntimeException(e);
        }
    }
}
