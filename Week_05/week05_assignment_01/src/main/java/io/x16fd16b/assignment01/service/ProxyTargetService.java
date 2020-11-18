package io.x16fd16b.assignment01.service;

/**
 * 代理目标 Service
 *
 * @author David Liu
 */
public interface ProxyTargetService {
    /**
     * foo
     *
     * @return string: bar
     */
    String foo();

    /**
     * foo, 入参: 执行单元
     *
     * @param runnable 执行单元
     * @return string: bar
     */
    String foo(Runnable runnable);
}
