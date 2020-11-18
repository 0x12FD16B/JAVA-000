package io.x16fd16b.assignment04.jdbc.hikari;

import java.sql.SQLException;
import java.util.List;

/**
 * Jdbc Worker
 *
 * @author David Liu
 */
public interface JdbcWorker {
    void init() throws ClassNotFoundException;

    void executeInsertStatement(String sql, Object... args) throws SQLException;

    void executeUpdateStatement(String sql, Object... args) throws SQLException;

    void executeSelectStatement(String sql, Object... args) throws SQLException;

    void executeDeleteStatement(String sql, Object... args) throws SQLException;

    void executeInsertBatch(String sql, List<Object[]> args) throws SQLException;
}
