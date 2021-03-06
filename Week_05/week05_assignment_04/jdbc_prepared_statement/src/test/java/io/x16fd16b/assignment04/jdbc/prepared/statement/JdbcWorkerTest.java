package io.x16fd16b.assignment04.jdbc.prepared.statement;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcWorkerTest {

    private final String connectionSegment = "jdbc:mysql://localhost:3306/user?rewriteBatchedStatements=true";

    private final String dbUsername = "root";

    private final String dbPassword = "1q2w3e4r";

    private final JdbcWorker jdbcWorker = new JdbcWorkerImpl(connectionSegment, dbUsername, dbPassword);

    @Before
    public void testBefore() throws ClassNotFoundException, SQLException {
        jdbcWorker.init();
        jdbcWorker.executeUpdateStatement("TRUNCATE `t_user`");
    }

    @Test
    public void testExecuteStatement() throws SQLException {
        jdbcWorker.executeInsertStatement("INSERT INTO `t_user` (`id`, `name`) VALUES (?, ?);", 1, "aaa");
        jdbcWorker.executeSelectStatement("SELECT `id`, `name` FROM `t_user`;");
        jdbcWorker.executeUpdateStatement("UPDATE `t_user` SET `name` = ? WHERE id = ?;", "bbb", 1);
        jdbcWorker.executeSelectStatement("SELECT `id`, `name` FROM `t_user`;");
        jdbcWorker.executeDeleteStatement("DELETE FROM `t_user` WHERE `id` = ?;", 1);
    }

    @Test
    public void testInsertBatch() throws SQLException {
        String sql = "INSERT INTO `t_user` (`id`, `name`) VALUES (?, ?)";
        List<Object[]> args = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            args.add(new Object[]{i, "user" + i});
        }
        jdbcWorker.executeInsertBatch(sql, args);

        args.clear();
        for (int i = 1; i < 100; i++) {
            args.add(new Object[]{50 + i, "user" + 50 + i});
        }
        jdbcWorker.executeInsertBatch(sql, args);
    }
}