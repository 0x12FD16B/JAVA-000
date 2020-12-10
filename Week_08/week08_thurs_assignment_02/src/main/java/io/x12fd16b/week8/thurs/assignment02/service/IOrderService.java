package io.x12fd16b.week8.thurs.assignment02.service;

import io.x12fd16b.week8.thurs.assignment02.common.OrderDTO;
import io.x12fd16b.week8.thurs.assignment02.common.enums.OrderStatus;

import java.util.List;

/**
 * 订单 Service
 *
 * @author David Liu
 */
public interface IOrderService {
    
    /**
     * 创建订单
     *
     * @param orderDTO 订单信息
     */
    void createOrder(OrderDTO orderDTO);
    
    /**
     * 更改订单状态
     *
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(Long userId, Long orderId, OrderStatus orderStatus);
    
    /**
     * 创建 DAO 资源
     */
    void prepareDAOResource();
    
    /**
     * 销毁 DAO 资源
     */
    void destroyDAOResource();
    
    /**
     * 删除订单
     *
     * @param userId  用户 ID
     * @param orderId 订单 ID
     */
    void deleteOrder(Long userId, Long orderId);
    
    /**
     * 获取用户订单列表
     * @param userId 用户 ID
     * @return 用户订单信息列表
     */
    List<OrderDTO> getUserOrders(Long userId);
}
