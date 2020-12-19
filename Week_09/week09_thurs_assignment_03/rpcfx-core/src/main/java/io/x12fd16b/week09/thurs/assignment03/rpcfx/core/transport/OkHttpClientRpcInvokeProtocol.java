package io.x12fd16b.week09.thurs.assignment03.rpcfx.core.transport;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api.RpcRequest;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.api.RpcResponse;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.core.exceptions.RpcException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * OkHttpClientRpcInvokeProtocol
 *
 * @author David Liu
 */
public class OkHttpClientRpcInvokeProtocol implements RpcInvokeProtocol {

    private static final OkHttpClient OK_HTTP_CLIENT;

    static {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        ParserConfig.getGlobalInstance().addAccept("io.x12fd16b.week09.thurs.assignment03.rpcfx");
        OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .build();
    }

    @Override
    public RpcResponse invokeHttpPost(RpcRequest request, String url) {
        String reqJson = JSON.toJSONString(request);
        final Request req = new Request.Builder()
                .url(url)
                .post(RequestBody.create(MediaType.get("application/json; charset=utf-8"), reqJson))
                .build();
        String respJson;
        try {
            Response response = OK_HTTP_CLIENT.newCall(req).execute();
            if (response.body() == null) {
                throw new RpcException("response body is null");
            }
            respJson = response.body().string();
        } catch (IOException e) {
            throw new RpcException(e.getMessage());
        }

        return JSON.parseObject(respJson, RpcResponse.class);
    }
}
