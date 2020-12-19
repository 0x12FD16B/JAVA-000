package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport.RpcInvokeProtocol;
import net.sf.cglib.proxy.Enhancer;

import java.util.function.Supplier;

/**
 * @author David Liu
 */
public class RpcClientCglibProxyFactory implements RpcClientProxyFactory {

    /**
     * proxy container
     */
    private final RpcClientProxyContainer rpcClientProxyContainer;

    public RpcClientCglibProxyFactory(RpcClientProxyContainer rpcClientProxyContainer) {
        this.rpcClientProxyContainer = rpcClientProxyContainer;
    }

    @Override
    public <T> T createProxy(final Class<T> serviceClass, final String url, final RpcInvokeProtocol rpcInvokeProtocol) {
        Supplier<T> proxySupplier = () -> {
            Enhancer enhancer = new Enhancer();
            enhancer.setCallback(new RpcInvocationHandler(serviceClass, url, rpcInvokeProtocol));
            enhancer.setSuperclass(serviceClass);
            return (T) enhancer.create();
        };
        return (T) rpcClientProxyContainer.getProxy(serviceClass.getName(), proxySupplier);
    }
}
