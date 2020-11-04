package course.java.assignment.inbound;

import course.java.assignment.filter.HttpRequestFilter;
import course.java.assignment.outbound.httpclient.HttpOutboundHandler;
import course.java.assignment.router.HttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);

    private final HttpOutboundHandler handler;

    private final List<HttpRequestFilter> filters;

    public HttpInboundHandler(List<String> proxyServers, List<HttpRequestFilter> filters, HttpEndpointRouter httpEndpointRouter) {
        handler = new HttpOutboundHandler(proxyServers, httpEndpointRouter);
        this.filters = filters;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            String uri = fullRequest.uri();
            logger.info("接收到的请求 url: {}", uri);
            if (filters != null && filters.size() > 0) {
                filters.forEach(httpRequestFilter -> httpRequestFilter.filter(fullRequest, ctx));
            }
            handler.handle(fullRequest, ctx);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}