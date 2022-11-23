package database.connection;

public class EntidadeConexao {
    private String endereco = "localhost";
    private String nomeBanco = "public";
    private String usuario = "root";
    private String senha = "root";
    private String url = "jdbc:mysql://" + getEndereco() + "/" + getNomeBanco();

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

    public String getUrl() {
        return url;
    }
}
