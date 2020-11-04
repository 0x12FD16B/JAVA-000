package course.java.assignment;

import course.java.assignment.filter.HttpRequestFilter;
import course.java.assignment.filter.HttpRequestHeaderAppendFilter;
import course.java.assignment.filter.HttpRequestStatsLogFilter;
import course.java.assignment.inbound.HttpInboundServer;
import course.java.assignment.router.RoundRobinHttpEndpointRouter;
import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Slf4JLoggerFactory;

import java.util.Arrays;
import java.util.List;

public class NettyServerApplication {

    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "1.0.0";

    public static void main(String[] args) {
        InternalLoggerFactory.setDefaultFactory(Slf4JLoggerFactory.INSTANCE);
        List<String> proxyServers = Arrays.asList("http://localhost:8088", "http://localhost:8089");
        String proxyPort = System.getProperty("proxyPort", "8888");

        int port = Integer.parseInt(proxyPort);
        List<HttpRequestFilter> filters = Arrays.asList(new HttpRequestStatsLogFilter(), new HttpRequestHeaderAppendFilter());
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServers, filters, new RoundRobinHttpEndpointRouter());
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION + " started at http://localhost:" + port + " for servers:" + proxyServers);
        try {
            server.run();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}