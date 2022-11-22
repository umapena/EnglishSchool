package model;

public class TurmasModel extends EntidadesModel{
    private Integer id;
    private String nome;
    private String nivel;
    private String periodo;

    private double valor;

    public TurmasModel(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNivel() { return nivel; }

    public void setNivel(String nivel) { this.nivel = nivel; }

    public String getPeriodo() { return periodo; }

    public void setPeriodo(String periodo) { this.periodo = periodo; }

    public double getValor() { return valor; }

    public void setValor(double valor) { this.valor = valor; }
}
