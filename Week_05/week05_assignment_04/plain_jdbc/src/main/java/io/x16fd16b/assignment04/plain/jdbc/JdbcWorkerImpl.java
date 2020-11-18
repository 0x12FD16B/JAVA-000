package io.x16fd16b.assignment04.plain.jdbc;

import java.sql.*;

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
    public void executeInsertStatement(String sql) throws SQLException {

        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 statement
                Statement stat = con.createStatement();
        ) {
            // 执行 sql 并接收返回结果
            int count = stat.executeUpdate(sql);

            // 打印处理结果
            System.out.println("插入成功" + count + "条记录");
        }
    }

    @Override
    public void executeUpdateStatement(String sql) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 statement
                Statement stat = con.createStatement();
        ) {
            // 执行sql并接收返回结果
            int count = stat.executeUpdate(sql);

            // 打印处理结果
            System.out.println("更新了" + count + "条记录");
        }
    }

    @Override
    public void executeSelectStatement(String sql) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 statement
                Statement stat = con.createStatement();
        ) {
            // 执行sql并接收返回结果
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                System.out.println("id: " + rs.getString("id"));
                System.out.println("name: " + rs.getString("name"));
            }
        }
    }

    @Override
    public void executeDeleteStatement(String sql) throws SQLException {
        try (
                // 获取数据库的连接对象
                Connection con = DriverManager.getConnection(connectionSegment, dbUsername, dbPassword);
                // 获取 statement
                Statement stat = con.createStatement();
        ) {
            // 执行sql并接收返回结果
            int count = stat.executeUpdate(sql);

            // 打印处理结果
            System.out.println("删除了" + count + "条记录");
        }

    }
}
