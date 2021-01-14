package io.x12fd16b.assignment.week13.sat.kafka;

import io.x12fd16b.assignment.week13.sat.message.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

/**
 * AssignmentTopicConsumer.
 *
 * @author David Liu
 */
@Component
@Slf4j
@KafkaListener(id = "handler", topics = "order")
public class OrderTopicConsumer {

    @KafkaHandler
    public void consume(@Payload Order order, @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key) {
        log.info("receive message from topic: order, key: {}, message: {}", key, order);
    }
}
