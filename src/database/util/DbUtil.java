package database.util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbUtil {
    public DbUtil() {

    }

    public void fecharConexaoEPrpdStatement(Connection conexao, PreparedStatement pst) throws SQLException {
        pst.close();
        conexao.close();
    }

    public void trataExcecoesDeAcordoComState(String sqlState) {
        if (sqlState.equals("23503")) {
            JOptionPane.showMessageDialog(null, "Não é possível deletar o registro pois ele é referenciado por outra tabela.");
        }
    }
}
