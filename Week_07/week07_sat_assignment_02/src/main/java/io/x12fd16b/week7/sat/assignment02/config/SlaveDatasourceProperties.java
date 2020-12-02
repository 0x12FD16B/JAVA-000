package io.x12fd16b.week7.sat.assignment02.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Slave 数据源配置属性
 *
 * @author David Liu
 */
@ConfigurationProperties(prefix = "spring.datasource.slave")
public class SlaveDatasourceProperties {

    private List<DruidDataSource> peers;

    public List<DruidDataSource> getPeers() {
        return peers;
    }

    public void setPeers(List<DruidDataSource> peers) {
        this.peers = peers;
    }
}
