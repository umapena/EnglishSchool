package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import model.FaturasModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FaturasDAO extends SistemaDAO {

    private Connection conexao;

    private final String select = "SELECT * from public.faturas;";
    private final String insert = "INSERT INTO public.faturas(id_matricula,data_vencimento,valor,data_pagamento,data_cancelamento)" +
            "VALUES (?,?,?,?,?);";
    private final String delete = "DELETE FROM public.faturas WHERE id_matricula = ? AND data_vencimento = ?;";
    private final String update = "UPDATE public.faturas SET id_matricula = ?, data_vencimento = ?, valor = ?, data_pagamento = ? data_cancelamento = ? " +
            "WHERE id = ?;";
    private final String selectById = "SELECT * from public.faturas WHERE id_matricula = ?, data_vencimento = ?;";
    private final String updateDataCancelamento = "UPDATE public.faturas SET data_cancelamento = ? WHERE id_matricula = ?;";

    private final PreparedStatement pstSelect;
    private final PreparedStatement pstInsert;
    private final PreparedStatement pstDelete;
    private final PreparedStatement pstUpdate;
    private final PreparedStatement pstSelectById;
    private final PreparedStatement pstUpdateDataCancelamento;

    public FaturasDAO() {
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstSelect = this.conexao.prepareStatement(select);
            pstInsert = this.conexao.prepareStatement(insert);
            pstDelete = this.conexao.prepareStatement(delete);
            pstUpdate = this.conexao.prepareStatement(update);
            pstUpdateDataCancelamento = this.conexao.prepareStatement(updateDataCancelamento);
            pstSelectById = this.conexao.prepareStatement(selectById);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> select() throws SQLException {
        List <Object> arrayListFaturasMatriculas = new ArrayList<>();

        try {
            ResultSet resultadoQuery = pstSelect.executeQuery();

            while (resultadoQuery.next()) {
                FaturasModel faturasModel = new FaturasModel();

                faturasModel.setIdMatricula(resultadoQuery.getInt("id_matricula"));
                faturasModel.setDataVencimento(resultadoQuery.getDate("data_vencimento"));
                faturasModel.setValor(resultadoQuery.getDouble("valor"));
                faturasModel.setDataPagamento(resultadoQuery.getTimestamp("data_pagamento"));
                faturasModel.setDataCancelamento(resultadoQuery.getDate("data_cancelamento"));

                arrayListFaturasMatriculas.add(faturasModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arrayListFaturasMatriculas;
    }

    @Override
    public Object selectById(Object param) throws SQLException {
        FaturasModel faturasModel = (FaturasModel) param;
        pstSelectById.setInt(1, faturasModel.getIdMatricula());
        pstSelectById.setDate(2, (Date) faturasModel.getDataVencimento());

        try {
            ResultSet resultadoQuery = pstSelectById.executeQuery();
            faturasModel.setIdMatricula(resultadoQuery.getInt("id_matricula"));
            faturasModel.setDataVencimento(resultadoQuery.getDate("data_vencimento"));
            faturasModel.setValor(resultadoQuery.getDouble("valor"));
            faturasModel.setDataPagamento(resultadoQuery.getTimestamp("data_pagamento"));
            faturasModel.setDataCancelamento(resultadoQuery.getDate("data_cancelamento"));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Matrícula não encontrada!");
            e.printStackTrace();
        }

        return faturasModel;
    }



    @Override
    public void insert(Object param) throws SQLException {
        FaturasModel faturasModel = (FaturasModel) param;

        java.util.Date utilDate = faturasModel.getDataVencimento();
        Date sqlDate = new Date(utilDate.getTime());

        pstInsert.setInt(1, faturasModel.getIdMatricula());
        pstInsert.setDate(2, sqlDate);
        pstInsert.setDouble(3, faturasModel.getValor());
        pstInsert.setTimestamp(4, faturasModel.getDataPagamento());
        pstInsert.setDate(5, (Date) faturasModel.getDataCancelamento());

        try {
            pstInsert.execute();
        } catch (SQLException e) {
            System.out.println("Houve um erro ao inserir fatura!");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Object param) throws SQLException {
        FaturasModel faturasModel = (FaturasModel) param;

        java.util.Date utilDate = faturasModel.getDataVencimento();
        Date sqlDate = new Date(utilDate.getTime());

        pstDelete.setInt(1, faturasModel.getIdMatricula());
        pstDelete.setDate(2, sqlDate);

        try {
            pstDelete.execute();
        } catch (SQLException e) {
            System.out.println("Houve um erro ao excluir fatura!");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object param) throws SQLException {
        FaturasModel faturasModel = (FaturasModel) param;

        pstUpdate.setInt(1, faturasModel.getIdMatricula());
        pstUpdate.setDate(2, (Date) faturasModel.getDataVencimento());
        pstUpdate.setDouble(3, faturasModel.getValor());
        pstUpdate.setTimestamp(4, faturasModel.getDataPagamento());
        pstUpdate.setDate(5, (Date) faturasModel.getDataCancelamento());

        try {
            pstUpdate.execute();
        } catch (SQLException e){
            System.out.println("Houve um erro ao atualizar fatura!");
            e.printStackTrace();
        }
    }

    public void updateDataCancelamento(Object param) throws SQLException {
        FaturasModel faturasModel = (FaturasModel) param;

        pstUpdateDataCancelamento.setDate(1, (Date) faturasModel.getDataCancelamento());
        pstUpdateDataCancelamento.setInt(2, faturasModel.getIdMatricula());

        try {
            pstUpdateDataCancelamento.execute();
        } catch (SQLException e){
            System.out.println("Houve um erro ao atualizar fatura!");
            e.printStackTrace();
        }
    }
}
