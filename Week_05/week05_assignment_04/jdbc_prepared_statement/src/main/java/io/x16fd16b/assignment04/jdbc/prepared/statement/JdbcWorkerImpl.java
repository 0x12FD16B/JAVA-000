package io.x16fd16b.assignment04.jdbc.prepared.statement;

import java.sql.*;
import java.util.List;

/**
 * JdbcWorker
 *
 * @author David Liu
 */
public class JdbcWorkerImpl implements JdbcWorker {
    private final String connectionSegment;

    private final String dbUsername;

    private final String dbPassword;

    public JdbcWorkerImpl(String connectionSegment, String dbUsername, String dbPassword) {
        this.connectionSegment = connectionSegment;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }

    @Override
    public void init() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    @Override
    public void executeInsertStatement(String sql, Object... args) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 PreparedStatement
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            // 执行sql并接收返回结果
            int count = preparedStatement.executeUpdate();

            // 打印处理结果
            System.out.println("插入成功" + count + "条记录");
        }
    }

    @Override
    public void executeUpdateStatement(String sql, Object... args) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 PreparedStatement
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            // 执行sql并接收返回结果
            int count = preparedStatement.executeUpdate();

            // 打印处理结果
            System.out.println("更新了" + count + "条记录");
        }
    }

    @Override
    public void executeSelectStatement(String sql, Object... args) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 PreparedStatement
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            // 执行sql并接收返回结果
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                System.out.println("id: " + rs.getString("id"));
                System.out.println("name: " + rs.getString("name"));
            }
        }
    }

    @Override
    public void executeDeleteStatement(String sql, Object... args) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 PreparedStatement
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            if (args != null) {
                for (int i = 0; i < args.length; i++) {
                    preparedStatement.setObject(i + 1, args[i]);
                }
            }
            // 执行sql并接收返回结果
            int count = preparedStatement.executeUpdate();

            // 打印处理结果
            System.out.println("删除了" + count + "条记录");
        }
    }

    @Override
    public void executeInsertBatch(String sql, List<Object[]> args) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 PreparedStatement
                PreparedStatement preparedStatement = con.prepareStatement(sql);
        ) {
            con.setAutoCommit(false);
            if (args != null) {
                for (Object[] arg : args) {
                    for (int i = 0; i < arg.length; i++) {
                        preparedStatement.setObject(i + 1, arg[i]);
                    }
                    preparedStatement.addBatch();
                }
            }
            preparedStatement.executeBatch();
            con.commit();
        }
    }
}
