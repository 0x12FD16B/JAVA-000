package io.x12fd16b.week8.sat.assignment02.order.service;

import io.x12fd16b.week8.sat.assignment02.common.dto.OrderDTO;

/**
 * Order Service
 *
 * @author David Liu
 */
public interface OrderService {
    
    /**
     * 创建订单
     *
     * @param orderDTO 订单信息
     */
    void createOrder(OrderDTO orderDTO);
}
