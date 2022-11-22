package database.dao;

import java.sql.SQLException;
import java.util.List;

public abstract class SistemaDAO {
    public abstract List<Object> select() throws SQLException;

    public abstract Object selectById(Object param) throws SQLException;

    public abstract void insert(Object param) throws SQLException;

    public abstract void delete(Object param) throws SQLException;

    public abstract void update(Object param) throws SQLException;
}
