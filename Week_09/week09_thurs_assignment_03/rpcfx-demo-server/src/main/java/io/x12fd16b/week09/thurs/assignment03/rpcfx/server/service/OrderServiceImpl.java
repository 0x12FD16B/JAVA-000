package io.x12fd16b.week09.thurs.assignment03.rpcfx.server.service;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api.OrderService;
import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.model.Order;

/**
 * Order Service
 *
 * @author David Liu
 */
public class OrderServiceImpl implements OrderService {
    @Override
    public Order findById(Integer id) {
        return new Order(1, "order", 1);
    }
}
