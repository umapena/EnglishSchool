package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import database.util.DbUtil;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CidadesDAO extends SistemaDAO {
    private Connection conexao;
    private DbUtil dbUtil = new DbUtil();

    private final String selectCidadesByEstado = "SELECT nome FROM mydb.cidades WHERE estado = ? ORDER BY nome ASC";

    private final PreparedStatement pstSelectCidadesByEstado;

    public CidadesDAO() {
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstSelectCidadesByEstado = this.conexao.prepareStatement(selectCidadesByEstado);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    public List<String> selectCidadesByEstado(String estado) throws SQLException {
        List<String> cidadesRecuperar = new ArrayList<>();

        pstSelectCidadesByEstado.setString(1, estado);

        try {
            ResultSet resultadoQuery = pstSelectCidadesByEstado.executeQuery();

            while (resultadoQuery.next()) {
                String cidade = "";

                cidade = resultadoQuery.getString("nome");

                cidadesRecuperar.add(cidade);
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao recuperar as cidades do Estado!");
            e.printStackTrace();
        }

        return cidadesRecuperar;
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
