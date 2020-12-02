package io.x12fd16b.week7.sat.assignment03.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;

/**
 * 数据源配置
 *
 * @author David Liu
 */
@Configuration
@MapperScan(basePackages = "io.x12fd16b.week7.sat.assignment03.dao.mapper")
@ImportAutoConfiguration(MybatisAutoConfiguration.class)
public class DataSourceConfig {

}
