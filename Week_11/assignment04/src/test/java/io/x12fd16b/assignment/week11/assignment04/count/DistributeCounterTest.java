package io.x12fd16b.assignment.week11.assignment04.count;

import io.lettuce.core.RedisClient;
import org.junit.Assert;
import org.junit.Test;

public class DistributeCounterTest {

    @Test
    public void testCounter() {
        RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379");
        DistributeCounter distributeCounter = new RedisDistributeCounter(redisClient);
        String resourceKey = "resourceKey";
        Assert.assertEquals(Integer.valueOf(0), distributeCounter.get(resourceKey));
        Integer quantity = 10;
        Integer expectAfterIncrement = distributeCounter.get(resourceKey) + quantity;
        distributeCounter.increment(resourceKey, quantity);
        Assert.assertEquals(expectAfterIncrement, distributeCounter.get(resourceKey));

        Integer expectAfterDecrement = distributeCounter.get(resourceKey) - quantity;
        distributeCounter.decrement(resourceKey, quantity);
        Assert.assertEquals(expectAfterDecrement, distributeCounter.get(resourceKey));
    }

}