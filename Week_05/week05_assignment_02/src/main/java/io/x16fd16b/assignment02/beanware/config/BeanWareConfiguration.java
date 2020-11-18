package io.x16fd16b.assignment02.beanware.config;

import io.x16fd16b.assignment02.beanware.config.decorator.FooServiceDecorator;
import org.springframework.context.annotation.Bean;

/**
 * BeanWareConfiguration
 *
 * @author David Liu
 */
public class BeanWareConfiguration {

    @Bean
    public FooService fooService() {
        return new FooServiceImpl();
    }

    @Bean
    public FooServiceDecorator fooServiceDecorator() {
        return new FooServiceDecorator(fooService());
    }
}
