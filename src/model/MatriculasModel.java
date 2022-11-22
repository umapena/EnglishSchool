package model;

import java.util.Date;

public class MatriculasModel extends EntidadesModel{
    private Integer idMatricula;
    private Integer idAluno;
    private Integer idTurma;
    private Date dataMatricula;
    private Integer diaVencimento;
    private Date dataEncerramento;
    private String nomeAluno;
    private String nomeTurma;

    public MatriculasModel(){

    }

    public String getNomeAluno() { return nomeAluno; }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getNomeTurma() { return nomeTurma; }

    public void setNomeTurma(String nomeTurma) { this.nomeTurma = nomeTurma; }

    public Integer getIdMatricula() { return idMatricula; }

    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }

    public Integer getIdAluno() { return idAluno; }

    public void setIdAluno(Integer idAluno) { this.idAluno = idAluno; }

    public Integer getIdTurma() { return idTurma; }

    public void setIdTurma(Integer idTurma) { this.idTurma = idTurma; }

    public Date getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(Date dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public Integer getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(Integer diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public Date getDataEncerramento() {
        return dataEncerramento;
    }

    public void setDataEncerramento(Date dataEncerramento) {
        this.dataEncerramento = dataEncerramento;
    }
}
