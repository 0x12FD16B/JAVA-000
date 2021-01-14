package io.x12fd16b.assignment.week13.sat.controller;

import io.x12fd16b.assignment.week13.sat.message.Order;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * order message controller.
 *
 * @author David Liu
 */
@RestController
@RequestMapping("message")
public class OrderMessageController {

    private final KafkaTemplate kafkaTemplate;

    public OrderMessageController(final KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("order")
    public void orderMessage() {
        kafkaTemplate.send("order", new Order(UUID.randomUUID().toString()));
    }
}
