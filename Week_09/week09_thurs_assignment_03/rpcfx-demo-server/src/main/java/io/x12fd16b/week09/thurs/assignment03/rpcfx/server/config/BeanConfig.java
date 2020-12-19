package io.x12fd16b.week09.thurs.assignment03.rpcfx.server.config;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.OrderService;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.UserService;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.server.service.OrderServiceImpl;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.server.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * BeanConfig
 *
 * @author David Liu
 */
@Configuration
public class BeanConfig {
    @Bean("io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.OrderService")
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
