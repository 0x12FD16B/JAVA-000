package io.x12fd16b.week8.thurs.assignment02;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动入口
 *
 * @author David Liu
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "io.x12fd16b.week8.thurs.assignment02.dao.mapper")
public class Application {
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
