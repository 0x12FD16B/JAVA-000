package io.x16fd16b.assignment04.plain.jdbc;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

public class JdbcWorkerTest {

    private final String connectionSegment = "jdbc:mysql://localhost:3306/user";

    private final String dbUsername = "root";

    private final String dbPassword = "1q2w3e4r";

    private final JdbcWorker jdbcWorker = new JdbcWorkerImpl(connectionSegment, dbUsername, dbPassword);

    @Before
    public void testBefore() throws ClassNotFoundException {
        jdbcWorker.init();
    }

    @Test
    public void testExecuteStatement() throws SQLException {
        jdbcWorker.executeInsertStatement("INSERT INTO `t_user` (`id`, `name`) VALUES (1, 'aaa');");
        jdbcWorker.executeSelectStatement("SELECT `id`, `name` FROM `t_user`;");
        jdbcWorker.executeUpdateStatement("UPDATE `t_user` SET `name` = 'bb' WHERE id = 1;");
        jdbcWorker.executeSelectStatement("SELECT `id`, `name` FROM `t_user`;");
        jdbcWorker.executeDeleteStatement("DELETE FROM `t_user` WHERE `id` = 1;");
    }
}