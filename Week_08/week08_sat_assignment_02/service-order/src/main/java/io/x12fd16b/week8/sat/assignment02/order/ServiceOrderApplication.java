package io.x12fd16b.week8.sat.assignment02.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Service Order Application
 *
 * @author David Liu
 */
@SpringBootApplication
@ImportResource({"classpath:applicationContext.xml"})
public class ServiceOrderApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(ServiceOrderApplication.class, args);
    }
}
