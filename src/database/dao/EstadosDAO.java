package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import database.util.DbUtil;
import model.EstadosModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstadosDAO extends SistemaDAO {
    private Connection conexao;
    private DbUtil dbUtil = new DbUtil();

    private final String select = "SELECT * from estados order by nome";

    private final PreparedStatement pstSelect;

    public EstadosDAO() {
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstSelect = this.conexao.prepareStatement(select);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> arrayListEstados = new ArrayList<>();

        try {
            ResultSet resultadoQuery = pstSelect.executeQuery();

            while (resultadoQuery.next()) {
                EstadosModel estadosModel = new EstadosModel();

                estadosModel.setId(resultadoQuery.getInt("id"));
                estadosModel.setNome(resultadoQuery.getString("nome"));

                arrayListEstados.add(estadosModel);
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao recuperar os estados!");
            e.printStackTrace();
        }

        return arrayListEstados;
    }

    @Override
    public Object selectById(Object param) throws SQLException {
        return null;
    }

    @Override
    public void insert(Object param) throws SQLException {

    }

    @Override
    public void delete(Object param) throws SQLException {

    }

    @Override
    public void update(Object param) throws SQLException {

    }
}
