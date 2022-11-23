package database.connection;

public class EntidadeConexao {
    private String endereco = "localhost";
    private String porta = "3306";
    private String nomeBanco = "mydb";
    private String usuario = "root";
    private String senha = "admin";

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
