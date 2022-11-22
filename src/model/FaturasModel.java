package model;

import java.sql.Timestamp;
import java.util.Date;

public class FaturasModel {

    private Integer id;
    private Integer idMatricula;
    private Date dataVencimento;
    private Double valor;
    private Timestamp dataPagamento;
    private Date dataCancelamento;

    public FaturasModel() {

    }

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id;  }

    public Integer getIdMatricula() { return idMatricula; }

    public void setIdMatricula(Integer idMatricula) { this.idMatricula = idMatricula; }

    public Date getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Date dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Timestamp getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Timestamp dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public Date getDataCancelamento() {
        return dataCancelamento;
    }

    public void setDataCancelamento(Date dataCancelamento) {
        this.dataCancelamento = dataCancelamento;
    }
}
