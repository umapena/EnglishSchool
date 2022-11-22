package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import database.util.DbUtil;
import model.TurmasModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurmasDAO extends SistemaDAO {

    private Connection conexao;
    private DbUtil dbUtil = new DbUtil();

    private final String select = "SELECT * from public.turmas ORDER BY id;";
    private final String insert = "INSERT INTO public.turmas(nome, nivel, periodo, valor) VALUES (?, ?, ?, ?);";
    private final String delete = "DELETE FROM public.turmas WHERE id = ?;";
    private final String update = "UPDATE public.turmas SET nome = ?, nivel = ?, periodo = ?, valor = ?WHERE id = ?;";
    private final String selectById = "SELECT * from public.turmas WHERE id = ?;";

    private final PreparedStatement pstSelect;
    private final PreparedStatement pstInsert;
    private final PreparedStatement pstDelete;
    private final PreparedStatement pstUpdate;
    private final PreparedStatement pstSelectById;


    public TurmasDAO(){
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstSelect = this.conexao.prepareStatement(select);
            pstInsert = this.conexao.prepareStatement(insert);
            pstDelete = this.conexao.prepareStatement(delete);
            pstUpdate = this.conexao.prepareStatement(update);
            pstSelectById = this.conexao.prepareStatement(selectById);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> select() throws SQLException {
        List<Object> arrayListModalidades = new ArrayList<>();

        try {
            ResultSet resultadoQuery = pstSelect.executeQuery();
            while (resultadoQuery.next()){
                TurmasModel turmasModel = new TurmasModel();

                turmasModel.setId(resultadoQuery.getInt("id"));
                turmasModel.setNome(resultadoQuery.getString("nome"));
                turmasModel.setNivel(resultadoQuery.getString("nivel"));
                turmasModel.setPeriodo(resultadoQuery.getString("periodo"));
                turmasModel.setValor(resultadoQuery.getDouble("valor"));

                arrayListModalidades.add(turmasModel);
            }


        } catch (SQLException e) {
            System.out.println("Houve um erro ao recuperar as turmas!");
            e.printStackTrace();
        } finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstSelect);
        }

        return arrayListModalidades;
    }

    @Override
    public TurmasModel selectById(Object param) throws SQLException {
        TurmasModel modalidadesRecuperar = (TurmasModel) param;
        List<TurmasModel> arrayModalidades = new ArrayList<>();

        pstSelectById.setInt(1, modalidadesRecuperar.getId());

        try {
            ResultSet resultadoQuery = pstSelectById.executeQuery();

            while (resultadoQuery.next()){
                TurmasModel turmasModel = new TurmasModel();

                turmasModel.setId(resultadoQuery.getInt("id"));
                turmasModel.setNome(resultadoQuery.getString("nome"));
                turmasModel.setNivel(resultadoQuery.getString("nivel"));
                turmasModel.setPeriodo(resultadoQuery.getString("periodo"));
                turmasModel.setValor(resultadoQuery.getDouble("valor"));

                arrayModalidades.add(turmasModel);
            }

        } catch (SQLException e) {
            System.out.println("Houve um erro ao selecionar o id!");
            e.printStackTrace();
        } finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstSelectById);
        }

        return arrayModalidades.get(0);
    }

    @Override
    public void insert(Object param) throws SQLException {
        TurmasModel turmasModel = (TurmasModel) param;

        pstInsert.setString(1, turmasModel.getNome());
        pstInsert.setString(2, turmasModel.getNivel());
        pstInsert.setString(3, turmasModel.getPeriodo());
        pstInsert.setDouble(4, turmasModel.getValor());

        try {
            pstInsert.execute();
        } catch (SQLException e){
            System.out.println("Houve um erro ao inserir turma!");
            e.printStackTrace();
        } finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstSelectById);
        }
    }

    @Override
    public void delete(Object param) throws SQLException {
        TurmasModel turmasModel = (TurmasModel) param;

        pstDelete.setInt(1, turmasModel.getId());

        try {
            pstDelete.execute();
        } catch (SQLException e) {
            dbUtil.trataExcecoesDeAcordoComState(e.getSQLState());
        } finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstDelete);
        }

    }

    @Override
    public void update(Object param) throws SQLException {
        TurmasModel turmasModel = (TurmasModel) param;

        pstUpdate.setString(1, turmasModel.getNome());
        pstUpdate.setString(2, turmasModel.getNivel());
        pstUpdate.setString(3, turmasModel.getPeriodo());
        pstUpdate.setDouble(4, turmasModel.getValor());
        pstUpdate.setInt(5, turmasModel.getId());


        try {
            pstUpdate.execute();
        } catch (SQLException e) {
            System.out.println("Houve um erro ao atualizar turma!");
            e.printStackTrace();
        } finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstSelectById);
        }
    }

}
