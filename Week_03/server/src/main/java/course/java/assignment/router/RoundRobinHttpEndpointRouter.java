package course.java.assignment.router;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 轮询路由
 *
 * @author David Liu
 */
public class RoundRobinHttpEndpointRouter implements HttpEndpointRouter {
    private final AtomicInteger idx = new AtomicInteger(0);

    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null || endpoints.size() == 0) return null;
        return endpoints.get(getNextIdx() % endpoints.size());
    }

    private int getNextIdx() {
        return 0x7fffffff & idx.getAndIncrement();
    }
}
