package io.x16fd16b.assignment01;

import io.x16fd16b.assignment01.proxy.ProxyHandler;
import io.x16fd16b.assignment01.service.ProxyTargetService;
import io.x16fd16b.assignment01.service.ProxyTargetServiceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Objects;

/**
 * 运行入口
 *
 * @author David Liu
 */
public class Runner {

    public static void main(String[] args) {
        ProxyTargetService proxyTargetService = new ProxyTargetServiceImpl();
        InvocationHandler handler = new ProxyHandler(proxyTargetService);
        Class<? extends ProxyTargetService> proxyTargetClass = proxyTargetService.getClass();
        ProxyTargetService serviceProxy = (ProxyTargetService) Proxy.newProxyInstance(proxyTargetClass.getClassLoader(), proxyTargetClass.getInterfaces(), handler);
        System.out.println(serviceProxy.foo());
        System.out.println(serviceProxy.foo(() -> Objects.requireNonNull(null)));
    }
}
