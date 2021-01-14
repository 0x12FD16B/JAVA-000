package io.x12fd16b.assignment.week13.sat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * BootStrap Application.
 *
 * @author David Liu
 */
@SpringBootApplication
@EnableKafka
public class BootStrapApplication {

    /**
     * bootstrap main method.
     *
     * @param args command line args
     */
    public static void main(final String[] args) {
        SpringApplication.run(BootStrapApplication.class, args);
    }
}
