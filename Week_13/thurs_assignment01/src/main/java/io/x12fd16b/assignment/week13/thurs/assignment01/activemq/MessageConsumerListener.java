package io.x12fd16b.assignment.week13.thurs.assignment01.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * message consumer listener.
 *
 * @author David Liu
 */
@Component
@Slf4j
public class MessageConsumerListener {

    @JmsListener(destination = "assignment.queue")
    public void receiveFromQueue(final String message) {
        log.info("receive message from queue: {}, message: {}", "assignment.queue", message);
    }

    @JmsListener(destination = "assignment.topic")
    public void receiveFromTopic(final String message) {
        log.info("receive message from topic: {}, message: {}", "assignment.topic", message);
    }
}
