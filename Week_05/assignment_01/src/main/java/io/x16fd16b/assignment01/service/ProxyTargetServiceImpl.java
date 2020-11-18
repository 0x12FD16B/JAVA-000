package io.x16fd16b.assignment01.service;

/**
 * 代理目标 Service
 *
 * @author David Liu
 */
public class ProxyTargetServiceImpl implements ProxyTargetService {
    @Override
    public String foo() {
        return "bar";
    }

    @Override
    public String foo(Runnable runnable) {
        runnable.run();
        return "bar";
    }
}
