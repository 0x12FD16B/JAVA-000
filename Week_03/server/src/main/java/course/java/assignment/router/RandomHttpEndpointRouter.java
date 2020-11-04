package course.java.assignment.router;

import io.netty.util.internal.ThreadLocalRandom;

import java.util.List;

/**
 * 随机路由算法
 *
 * @author David Liu
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter {
    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null || endpoints.size() == 0) return null;
        int idx = (int) (ThreadLocalRandom.current().nextDouble() * endpoints.size());
        return endpoints.get(idx % endpoints.size());
    }
}
