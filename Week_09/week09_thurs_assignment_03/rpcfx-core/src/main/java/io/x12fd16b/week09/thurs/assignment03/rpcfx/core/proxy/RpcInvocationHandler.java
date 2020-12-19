package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.proxy;

import com.alibaba.fastjson.JSON;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api.RpcRequest;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api.RpcResponse;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport.RpcInvokeProtocol;
import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * RpcInvocationHandler.
 *
 * @author David Liu
 */
@Slf4j
public class RpcInvocationHandler implements MethodInterceptor {

    private final Class<?> serviceClass;

    private final String url;

    private final RpcInvokeProtocol rpcInvokeProtocol;

    public <T> RpcInvocationHandler(final Class<T> serviceClass, final String url, final RpcInvokeProtocol rpcInvokeProtocol) {
        this.serviceClass = serviceClass;
        this.url = url;
        this.rpcInvokeProtocol = rpcInvokeProtocol;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) {
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceClass(serviceClass.getName());
        rpcRequest.setMethod(method.getName());
        rpcRequest.setArgs(objects);

        RpcResponse rpcResponse = rpcInvokeProtocol.invokeHttpPost(rpcRequest, url);

        return JSON.parse(rpcResponse.getResult().toString());
    }
}
