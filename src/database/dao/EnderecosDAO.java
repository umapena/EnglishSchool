package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import model.EnderecosModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecosDAO extends SistemaDAO {
    private Connection conexao;

    private String insert = "INSERT INTO public.enderecos(logradouro, cep, numero, id_bairro) values (?, ?, ?, ?)";

    private String selectById = "SELECT ES.ID AS ID_ESTADO, C.ID AS ID_CIDADE, ES.NOME AS ESTADO, C.NOME AS CIDADE, B.NOME AS BAIRRO, E.LOGRADOURO, E.NUMERO, E.CEP, E.ID_BAIRRO, E.ID FROM ESTADOS ES " +
            "INNER JOIN CIDADES C ON (ES.ID = C.ID_ESTADO) INNER JOIN BAIRROS B ON (B.ID_CIDADE = C.ID) INNER JOIN ENDERECOS E ON (E.ID_BAIRRO = B.ID) " +
            "WHERE E.ID = ?";

    private String update = "UPDATE enderecos SET LOGRADOURO = ?, CEP = ?, NUMERO = ?, ID_BAIRRO = ? WHERE ID = ?;";

    private final PreparedStatement pstInsert;
    private final PreparedStatement pstSelectById;
    private final PreparedStatement pstUpdate;

    public EnderecosDAO() {
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstInsert = this.conexao.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            pstSelectById = this.conexao.prepareStatement(selectById);
            pstUpdate = this.conexao.prepareStatement(update);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    public Integer insertV2(Object param) throws SQLException {
        EnderecosModel enderecosModel = (EnderecosModel) param;
        Integer idInserido = 0;

        pstInsert.setString(1, enderecosModel.getLogradouro());
        pstInsert.setString(2, enderecosModel.getCep());
        pstInsert.setInt(3, enderecosModel.getNumero());
        pstInsert.setInt(4, enderecosModel.getIdBairro());

        try {
            pstInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ResultSet generatedKeys = pstInsert.getGeneratedKeys();
        if (generatedKeys.next()) {
            idInserido = generatedKeys.getInt("id");
        }

        System.out.println(idInserido);

        return idInserido;
    }

    @Override
    public List<Object> select() throws SQLException {
        return null;
    }

    @Override
    public EnderecosModel selectById(Object param) throws SQLException {
        EnderecosModel enderecoRecuperar = (EnderecosModel) param;
        List<EnderecosModel> arrayListEnderecos = new ArrayList<>();

        pstSelectById.setInt(1, enderecoRecuperar.getId());

        try {
            ResultSet resultadoQuery = pstSelectById.executeQuery();

            while (resultadoQuery.next()) {
                EnderecosModel enderecosModel = new EnderecosModel();

                enderecosModel.setId(resultadoQuery.getInt("id"));
                enderecosModel.setIdBairro(resultadoQuery.getInt("id_bairro"));
                enderecosModel.setIdCidade(resultadoQuery.getInt("id_cidade"));
                enderecosModel.setIdEstado(resultadoQuery.getInt("id_estado"));
                enderecosModel.setLogradouro(resultadoQuery.getString("logradouro"));
                enderecosModel.setNumero(resultadoQuery.getInt("numero"));
                enderecosModel.setCep(resultadoQuery.getString("cep"));
                enderecosModel.setNomeBairro(resultadoQuery.getString("bairro"));
                enderecosModel.setNomeCidade(resultadoQuery.getString("cidade"));
                enderecosModel.setNomeEstado(resultadoQuery.getString("estado"));

                arrayListEnderecos.add(enderecosModel);
            }
        } catch (SQLException e) {
            System.out.println("Houve um erro ao recuperar o endereço!");
            e.printStackTrace();
        }

        return arrayListEnderecos.get(0);
    }

    @Override
    public void insert(Object param) throws SQLException {

    }

    @Override
    public void delete(Object param) throws SQLException {

    }

    @Override
    public void update(Object param) throws SQLException {
        EnderecosModel enderecosModel = (EnderecosModel) param;

        pstUpdate.setString(1, enderecosModel.getLogradouro());
        pstUpdate.setString(2, enderecosModel.getCep());
        pstUpdate.setInt(3, enderecosModel.getNumero());
        pstUpdate.setInt(4, enderecosModel.getIdBairro());
        pstUpdate.setInt(5, enderecosModel.getId());

        try {
            pstUpdate.execute();
        } catch (SQLException e) {
            System.out.println("Houve um erro ao atualizar aluno!");
            e.printStackTrace();
        }
    }
}
