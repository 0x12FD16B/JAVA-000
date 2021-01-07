package io.x12fd16b.assignment.week11.assignment05.handler;

import io.x12fd16b.assignment.week11.assignment05.model.OrderDto;

/**
 * order async handler.
 *
 * @author David Liu
 */
public abstract class OrderAsyncHandler {

    /**
     * publish order create message.
     *
     * @param orderDto order dto
     */
    public abstract void publishOrderCreateMessage(OrderDto orderDto);

    protected abstract void subscribeOrderCreateMessage();
}
