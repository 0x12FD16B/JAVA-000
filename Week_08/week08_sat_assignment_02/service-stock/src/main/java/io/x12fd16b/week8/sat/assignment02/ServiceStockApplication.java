package io.x12fd16b.week8.sat.assignment02;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * 启动入口
 *
 * @author David Liu
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class ServiceStockApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServiceStockApplication.class, args);
    }
}
