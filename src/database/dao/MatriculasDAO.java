package database.dao;

import database.connection.ConnectionFactory;
import database.connection.EntidadeConexao;
import database.util.DbUtil;
import model.MatriculasModel;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculasDAO extends SistemaDAO {
    private Connection conexao;
    private DbUtil dbUtil = new DbUtil();

    private final String select = "SELECT M.*, A.nome AS nome_aluno, T.nome AS nome_turma FROM public.matriculas M " +
            "INNER JOIN alunos A ON M.id_aluno = A.id " +
            "INNER JOIN turmas T ON M.id_turma = T.id ORDER BY M.id;";;
    private final String insert = "INSERT INTO public.matriculas(id_aluno,id_turma,data_matricula,dia_vencimento)" +
            "VALUES (?,?,?,?);";
    private final String delete = "DELETE FROM public.matriculas WHERE id = ?;";
    private final String update = "UPDATE public.matriculas SET id_aluno = ?, id_turma = ?, dia_vencimento = ? WHERE id = ?"; //corrigir
    private final String selectById = "SELECT M.*, A.nome AS nome_aluno, T.nome as nome_turma FROM public.matriculas M " +
            "INNER JOIN alunos A ON M.id_aluno = A.id " +
            "INNER JOIN turmas T ON M.id_turma = T.id WHERE M.id = ? ORDER BY M.id;";
    private final String encerrarMatricula = "UPDATE public.matriculas SET data_encerramento = ? WHERE id = ?";

    private final PreparedStatement pstSelect;
    private final PreparedStatement pstInsert;
    private final PreparedStatement pstDelete;
    private final PreparedStatement pstUpdate;
    private final PreparedStatement pstSelectById;
    private final PreparedStatement pstEncerrarMatricula;


    public MatriculasDAO() {
        try {
            this.conexao = ConnectionFactory.getConection(new EntidadeConexao());
            pstSelect = this.conexao.prepareStatement(select);
            pstInsert = this.conexao.prepareStatement(insert);
            pstDelete = this.conexao.prepareStatement(delete);
            pstUpdate = this.conexao.prepareStatement(update);
            pstSelectById = this.conexao.prepareStatement(selectById);
            pstEncerrarMatricula = this.conexao.prepareStatement(encerrarMatricula);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao inicializar os comandos SQL.");
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Object> select() throws SQLException {
        List <Object> arrayListMatriculas = new ArrayList<>();

        try {
            ResultSet resultadoQuery = pstSelect.executeQuery();
            while (resultadoQuery.next()){
                MatriculasModel matriculasModel = new MatriculasModel();

                matriculasModel.setIdMatricula(resultadoQuery.getInt("id"));
                matriculasModel.setIdAluno(resultadoQuery.getInt("id_aluno"));
                matriculasModel.setIdTurma((resultadoQuery.getInt("id_turma")));
                matriculasModel.setDataMatricula(resultadoQuery.getDate("data_matricula"));
                matriculasModel.setDiaVencimento(resultadoQuery.getInt("dia_vencimento"));
                matriculasModel.setDataEncerramento(resultadoQuery.getDate("data_encerramento"));
                matriculasModel.setNomeAluno(resultadoQuery.getString("nome_aluno"));
                matriculasModel.setNomeTurma(resultadoQuery.getString("nome_turma"));

                arrayListMatriculas.add(matriculasModel);
            }
        } catch (SQLException e){
            System.out.println("Houve um erro ao recuperar as matriculas!");
        }
        finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstSelect);
        }

        return arrayListMatriculas;
    }

    @Override
    public MatriculasModel selectById(Object param) throws SQLException {
        MatriculasModel matriculasModel = (MatriculasModel) param;
        List<MatriculasModel> arrayListMatriculas = new ArrayList<>();

        pstSelectById.setInt(1, matriculasModel.getIdMatricula());
        System.out.println(pstSelectById);

        try {
            ResultSet resultadoQuery = pstSelectById.executeQuery();

            while(resultadoQuery.next()){
                matriculasModel.setIdMatricula(resultadoQuery.getInt("id"));
                matriculasModel.setIdAluno(resultadoQuery.getInt("id_aluno"));
                matriculasModel.setIdTurma(resultadoQuery.getInt("id_turma"));
                matriculasModel.setDataMatricula(resultadoQuery.getDate("data_matricula"));
                matriculasModel.setDiaVencimento(resultadoQuery.getInt("dia_vencimento"));
                matriculasModel.setDataEncerramento(resultadoQuery.getDate("data_encerramento"));
                matriculasModel.setNomeAluno(resultadoQuery.getString("nome_aluno"));
                matriculasModel.setNomeTurma(resultadoQuery.getString("nome_turma"));

                arrayListMatriculas.add(matriculasModel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ID não encontrado!");
            e.printStackTrace();
        }finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstSelectById);
        }
        return matriculasModel;
    }

    @Override
    public void insert(Object param) throws SQLException {
        MatriculasModel matriculasModel = (MatriculasModel) param;

        pstInsert.setInt(1, matriculasModel.getIdAluno());
        pstInsert.setInt(2, matriculasModel.getIdTurma());
        pstInsert.setDate(3, (Date) matriculasModel.getDataMatricula());
        pstInsert.setInt(4, matriculasModel.getDiaVencimento());

        try {
            pstInsert.execute();
        } catch (SQLException e) {
            System.out.println("Houve um erro ao inserir a matrícula!");
            e.printStackTrace();
        }finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstInsert);
        }
    }

    @Override
    public void delete(Object param) throws SQLException {
        MatriculasModel matriculasModel = (MatriculasModel) param;

        pstDelete.setInt(1,matriculasModel.getIdMatricula());

        try {
            pstDelete.execute();
        } catch (SQLException e) {
            System.out.println("Houve um erro ao excluir matrícula!");
            e.printStackTrace();
        }finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstDelete);
        }
    }

    @Override
    public void update(Object param) throws SQLException {
        MatriculasModel matriculasModel = (MatriculasModel) param;

        pstUpdate.setInt(1, matriculasModel.getIdAluno());
        pstUpdate.setInt(2, matriculasModel.getIdTurma());
        pstUpdate.setInt(2, matriculasModel.getDiaVencimento());
        pstUpdate.setInt(4, matriculasModel.getIdMatricula());

        try {
            pstUpdate.execute();
        } catch (SQLException e){
            System.out.println("Houve um erro ao atualizar matrícula!");
            e.printStackTrace();
        }finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstUpdate);
        }
    }

    public void encerrarMatricula(Object param) throws SQLException{
        MatriculasModel matriculasModel = (MatriculasModel) param;

        java.util.Date utilDate = matriculasModel.getDataEncerramento();
        Date sqlDate = new Date(utilDate.getTime());

        pstEncerrarMatricula.setDate(1, sqlDate);
        pstEncerrarMatricula.setInt(2, matriculasModel.getIdMatricula());

        try {
            pstEncerrarMatricula.execute();
        } catch (SQLException e){
            System.out.println("Houve um erro ao atualizar matrícula!");
            e.printStackTrace();
        }finally {
            dbUtil.fecharConexaoEPrpdStatement(conexao, pstEncerrarMatricula);
        }
    }
}
