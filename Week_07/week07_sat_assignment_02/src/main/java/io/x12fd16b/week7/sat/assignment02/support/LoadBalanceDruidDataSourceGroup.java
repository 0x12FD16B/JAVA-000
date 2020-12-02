package io.x12fd16b.week7.sat.assignment02.support;

import com.alibaba.druid.pool.DruidDataSource;

import javax.sql.DataSource;

/**
 * 负载均衡数据源分组
 *
 * @author David Liu
 */
public interface LoadBalanceDruidDataSourceGroup extends DataSource {

    DruidDataSource acquireDataSource();
}
