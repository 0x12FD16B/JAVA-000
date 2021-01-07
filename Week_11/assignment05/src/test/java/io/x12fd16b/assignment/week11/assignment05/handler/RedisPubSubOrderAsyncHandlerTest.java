package io.x12fd16b.assignment.week11.assignment05.handler;

import io.lettuce.core.RedisClient;
import io.x12fd16b.assignment.week11.assignment05.model.OrderDto;
import io.x12fd16b.assignment.week11.assignment05.model.OrderItemDto;
import org.junit.Test;

import java.util.Collections;

public class RedisPubSubOrderAsyncHandlerTest {

    @Test
    public void testPublishMessage() {
        RedisClient redisClient = RedisClient.create("redis://127.0.0.1:6379");
        OrderAsyncHandler orderAsyncHandler = new RedisPubSubOrderAsyncHandler(redisClient);
        OrderDto orderDto = new OrderDto();
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setSkuCode("1000068");
        orderItemDto.setQuantity(1);
        orderDto.setOrderItems(Collections.singletonList(orderItemDto));
        orderAsyncHandler.publishOrderCreateMessage(orderDto);
    }
}