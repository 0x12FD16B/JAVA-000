package io.x12fd16b.week09.thurs.assignment03.rpcfx.client;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy.RpcClientCglibProxyFactory;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy.RpcClientProxyContainer;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy.RpcClientProxyFactory;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport.OkHttpClientRpcInvokeProtocol;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport.RpcInvokeProtocol;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.OrderService;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * DemoClientApplication
 *
 * @author David Liu
 */
@Slf4j
public class DemoClientApplication {

    public static void main(String[] args) {
        final String serverAddress = "http://127.0.0.1:8080";
        RpcInvokeProtocol rpcInvokeProtocol = new OkHttpClientRpcInvokeProtocol();
        RpcClientProxyContainer rpcClientProxyContainer = new RpcClientProxyContainer();
        RpcClientProxyFactory rpcClientProxyFactory = new RpcClientCglibProxyFactory(rpcClientProxyContainer);
        UserService userService = rpcClientProxyFactory.createProxy(UserService.class, serverAddress, rpcInvokeProtocol);
        log.info("user info: {}", userService.findById(1));
        OrderService orderService = rpcClientProxyFactory.createProxy(OrderService.class, serverAddress, rpcInvokeProtocol);
        log.info("order info: {}", orderService.findById(1));
    }

}
