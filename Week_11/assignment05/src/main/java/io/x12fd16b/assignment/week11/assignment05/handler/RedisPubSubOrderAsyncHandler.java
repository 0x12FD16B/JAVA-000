package io.x12fd16b.assignment.week11.assignment05.handler;

import com.alibaba.fastjson.JSON;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import io.x12fd16b.assignment.week11.assignment05.model.OrderDto;

/**
 * RedisPubSubOrderAsyncHandler.
 *
 * @author David Liu
 */
public class RedisPubSubOrderAsyncHandler extends OrderAsyncHandler {

    private final static String CHANNEL = "order_channel";

    private final RedisClient redisClient;

    public RedisPubSubOrderAsyncHandler(final RedisClient redisClient) {
        this.redisClient = redisClient;
        this.subscribeOrderCreateMessage();
    }

    @Override
    public void publishOrderCreateMessage(OrderDto orderDto) {
        try (StatefulRedisPubSubConnection<String, String> connection = redisClient.connectPubSub()) {
            RedisPubSubCommands<String, String> redisPubSubCommands = connection.sync();
            redisPubSubCommands.publish(CHANNEL, JSON.toJSONString(orderDto));
        }
    }

    @Override
    protected void subscribeOrderCreateMessage() {
        RedisPubSubCommands<String, String> redisPubSubCommands = redisClient.connectPubSub().sync();
        redisPubSubCommands.getStatefulConnection().addListener(new OrderMessageRedisSubscribeListener());
        redisPubSubCommands.subscribe(CHANNEL);
    }
}
