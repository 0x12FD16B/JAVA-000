package io.x12fd16b.week7.sat.assignment02.support;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Druid 轮询负载均衡数据源
 *
 * @author David Liu
 */
@Slf4j
public class DruidRoundRobinLBDataSource extends DruidDataSource {

    private static final long serialVersionUID = 531342099237106220L;

    private final AtomicInteger idx = new AtomicInteger(0);

    private final List<DruidDataSource> dataSources;

    public DruidRoundRobinLBDataSource(List<DruidDataSource> dataSources) {
        if (dataSources == null) {
            throw new RuntimeException("Data sources cannot be null.");
        }
        this.dataSources = dataSources;
    }

    private DruidDataSource acquireDatasource() {
        return dataSources.get((0x7fffffff & idx.getAndIncrement()) % dataSources.size());
    }

    @Override
    public DruidPooledConnection getConnection() throws SQLException {
        DruidDataSource druidDataSource = this.acquireDatasource();
        log.info("数据源负载均衡轮询 >>> 数据源: {} 被选择", druidDataSource.getName());
        return druidDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        DruidDataSource druidDataSource = this.acquireDatasource();
        log.info("数据源负载均衡轮询 >>> 数据源: {} 被选择", druidDataSource.getName());
        return druidDataSource.getConnection(username, password);
    }
}
