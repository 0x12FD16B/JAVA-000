package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

/**
 * RPC Client Proxy Factory
 *
 * @author David Liu
 */
public final class RpcClientProxyContainer {
    private final ConcurrentHashMap<String, Object> proxyCache = new ConcurrentHashMap<>();

    public <T> T getProxy(String className, Supplier<T> proxySupplier) {
        if (proxyCache.containsKey(className)) {
            return (T) proxyCache.get(className);
        } else {
            T proxyObject = proxySupplier.get();
            proxyCache.putIfAbsent(className, proxyObject);
            return proxyObject;
        }
    }
}
