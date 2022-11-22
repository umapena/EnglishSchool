package database.connection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConection(EntidadeConexao entidadeConexao) {
        try {
            return DriverManager.getConnection("jdbc:postgresql://" + entidadeConexao.getEndereco() + ":" + entidadeConexao.getPorta() +
                    "/" + entidadeConexao.getNomeBanco(), entidadeConexao.getUsuario(), entidadeConexao.getSenha());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Houve um erro ao conectar ao banco de dados!");
            throw new RuntimeException(e);
        }
    }
}