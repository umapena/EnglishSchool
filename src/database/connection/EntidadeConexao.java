package database.connection;

public class EntidadeConexao {
    private String endereco = "localhost";
    private String porta = "5432"; //3306
    private String nomeBanco = "EnglishSchool";
    private String usuario = "postgres"; //admin
    private String senha = "postgres"; //admin

    public String getEndereco() {
        return endereco;
    }

    public String getPorta() {
        return porta;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getSenha() {
        return senha;
    }

}
