package io.x12fd16b.week09.sat.assignment03.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * ProviderApplication
 *
 * @author David Liu
 */
@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@ImportResource({"classpath:applicationContext.xml"})
@MapperScan("com.wyc.exchange.mapper")
public class ProviderApplication {
}
