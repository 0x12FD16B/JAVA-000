package course.java.assignment.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

public class HttpRequestHeaderAppendFilter implements HttpRequestFilter {
    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        HttpHeaders httpHeaders = new DefaultHttpHeaders();
        httpHeaders.add("X-NIO", System.getProperty("extHeadValue", "Java-Nio"));
        fullRequest.headers().add(httpHeaders);
    }
}
