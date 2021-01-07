package io.x12fd16b.assignment.week11.assignment05.handler;

import io.lettuce.core.pubsub.RedisPubSubListener;
import lombok.extern.slf4j.Slf4j;

/**
 * OrderMessageRedisSubscribeListener.
 *
 * @author David Liu
 */
@Slf4j
public class OrderMessageRedisSubscribeListener implements RedisPubSubListener<String, String> {
    @Override
    public void message(String channel, String message) {
        log.info("channel receive order info, channel: {}, message: {}", channel, message);
    }

    @Override
    public void message(String pattern, String channel, String message) {
        
    }

    @Override
    public void subscribed(String channel, long count) {

    }

    @Override
    public void psubscribed(String pattern, long count) {

    }

    @Override
    public void unsubscribed(String channel, long count) {

    }

    @Override
    public void punsubscribed(String pattern, long count) {

    }
}
