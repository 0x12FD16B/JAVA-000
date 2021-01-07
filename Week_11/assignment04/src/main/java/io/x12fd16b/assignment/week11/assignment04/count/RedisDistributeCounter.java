package io.x12fd16b.assignment.week11.assignment04.count;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.Objects;

/**
 * @author David Liu
 */
public class RedisDistributeCounter implements DistributeCounter {

    private final RedisClient redisClient;

    public RedisDistributeCounter(final RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    @Override
    public Integer get(String resourceKey) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            RedisCommands<String, String> redisCommands = connect.sync();
            String s = redisCommands.get(resourceKey);
            if (Objects.isNull(s) || "".equals(s)) {
                return 0;
            } else {
                return Integer.valueOf(s);
            }
        }
    }

    @Override
    public void decrement(String resourceKey, Integer quantity) {
        try (StatefulRedisConnection<String, String> connect = redisClient.connect()) {
            connect.sync().decrby(resourceKey, quantity);
        }
    }

    @Override
    public void increment(String resourceKey, Integer quantity) {
        this.decrement(resourceKey, -quantity);
    }
}
