package io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.api;

import io.x12fd16b.week09.thurs.assignment03.rpcfx.demo.common.model.Order;

/**
 * OrderService
 *
 * @author David Liu
 */
public interface OrderService {

    Order findById(Integer id);
}
