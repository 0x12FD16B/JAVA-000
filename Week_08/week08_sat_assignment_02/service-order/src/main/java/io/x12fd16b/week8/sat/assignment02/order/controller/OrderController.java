package io.x12fd16b.week8.sat.assignment02.order.controller;

import io.x12fd16b.week8.sat.assignment02.common.dto.OrderDTO;
import io.x12fd16b.week8.sat.assignment02.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Order Controller
 *
 * @author David Liu
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @PostMapping("create")
    public void create(@RequestBody OrderDTO orderDTO) {
        orderService.createOrder(orderDTO);
    }
}
