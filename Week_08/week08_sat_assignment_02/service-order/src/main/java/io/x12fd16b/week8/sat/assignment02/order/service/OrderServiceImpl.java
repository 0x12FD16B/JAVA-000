package io.x12fd16b.week8.sat.assignment02.order.service;

import io.x12fd16b.week8.sat.assignment02.common.api.StockService;
import io.x12fd16b.week8.sat.assignment02.common.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Order Service
 *
 * @author David Liu
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private StockService service;
    
    
    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void createOrder(OrderDTO orderDTO) {
        log.info("create order");
    }
    
    public void confirmOrderStatus(OrderDTO orderDTO) {
        log.info("confirm order status");
    }
    
    public void cancelOrderStatus(OrderDTO orderDTO) {
    
    }
}
