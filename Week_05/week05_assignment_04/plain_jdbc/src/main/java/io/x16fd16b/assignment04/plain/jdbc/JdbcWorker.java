package io.x16fd16b.assignment04.plain.jdbc;

import java.sql.SQLException;

/**
 * JDBC Worker
 *
 * @author David Liu
 */
public interface JdbcWorker {

    void init() throws ClassNotFoundException;

    void executeInsertStatement(String sql) throws SQLException;

    void executeUpdateStatement(String sql) throws SQLException;

    void executeSelectStatement(String sql) throws SQLException;

    void executeDeleteStatement(String sql) throws SQLException;
}
