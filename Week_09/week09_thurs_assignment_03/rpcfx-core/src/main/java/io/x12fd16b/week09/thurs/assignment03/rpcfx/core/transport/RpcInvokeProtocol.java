package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api.RpcRequest;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api.RpcResponse;

/**
 * Service Transport Protocol
 *
 * @author David Liu
 */
public interface RpcInvokeProtocol {

    RpcResponse invokeHttpPost(RpcRequest request, String url);
}
