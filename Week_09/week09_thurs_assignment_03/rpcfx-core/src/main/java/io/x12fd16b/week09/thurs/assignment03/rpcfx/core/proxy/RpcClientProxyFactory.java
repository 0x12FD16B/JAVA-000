package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport.RpcInvokeProtocol;

/**
 * Rpc Client Proxy Factory
 *
 * @author David Liu
 */
public interface RpcClientProxyFactory {

    /**
     * create rpc client proxy
     *
     * @param serviceClass      service class
     * @param url               server url
     * @param rpcInvokeProtocol rpcInvokeProtocol
     * @param <T>               T
     * @return proxy class
     */
    <T> T createProxy(final Class<T> serviceClass, final String url, final RpcInvokeProtocol rpcInvokeProtocol);
}
