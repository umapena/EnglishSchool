package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import model.BairrosModel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BairrosDAO extends SistemaDAO {
    private Connection conexao;

    private String selectBairrosByCidade = "SELECT * FROM bairros WHERE id_cidade = ?";

    private PreparedStatement pstSelectBairrosByCidade;

    public BairrosDAO() {
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstSelectBairrosByCidade = this.conexao.prepareStatement(selectBairrosByCidade);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    public List<BairrosModel> selectBairrosByCidade(Integer idCidade) throws SQLException {
        List<BairrosModel> arrayListBairros = new ArrayList<>();

        pstSelectBairrosByCidade.setInt(1, idCidade);

        try {
            ResultSet resultadoQuery = pstSelectBairrosByCidade.executeQuery();

            while (resultadoQuery.next()) {
                BairrosModel bairrosModel = new BairrosModel();

                bairrosModel.setId(resultadoQuery.getInt("id"));
                bairrosModel.setNome(resultadoQuery.getString("nome"));
                bairrosModel.setIdCidade(resultadoQuery.getInt("id_cidade"));

                arrayListBairros.add(bairrosModel);
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao recuperar os bairros!");
            e.printStackTrace();
        }

        return arrayListBairros;
    }

    @Override
    public List<Object> select() throws SQLException {
        return null;
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
