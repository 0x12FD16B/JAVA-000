package course.java.assignment.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 请求统计日志 Filter
 *
 * @author David Liu
 */
public class HttpRequestStatsLogFilter implements HttpRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(HttpRequestStatsLogFilter.class);

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        logger.info("接收到的请求 url: {}, 时间: {}", fullRequest.uri(), dateTimeFormatter.format(LocalDateTime.now()));
    }
}
