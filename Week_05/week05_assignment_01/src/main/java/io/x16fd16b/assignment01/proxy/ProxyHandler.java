package io.x16fd16b.assignment01.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 动态代理处理
 *
 * @author David Liu
 */
public class ProxyHandler implements InvocationHandler {
    private final Object proxyTarget;

    public ProxyHandler(Object proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.doBefore();
        Object res;
        try {
            res = method.invoke(this.proxyTarget, args);
        } catch (Exception e) {
            this.doAfterExceptionThrown();
            return null;
        }
        this.doAfter();
        return res;
    }

    private void doBefore() {
        System.out.println("In Proxy: before method");
    }

    private void doAfter() {
        System.out.println("In Proxy: after method");
    }

    private void doAfterExceptionThrown() {
        System.out.println("In Proxy: when any Exception has thrown");
    }
}
