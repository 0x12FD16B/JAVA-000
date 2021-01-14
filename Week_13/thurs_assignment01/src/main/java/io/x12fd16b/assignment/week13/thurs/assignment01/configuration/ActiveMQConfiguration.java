package io.x12fd16b.assignment.week13.thurs.assignment01.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * configuration for ActiveMQ.
 *
 * @author David Liu
 */
@Configuration
public class ActiveMQConfiguration {

    /**
     * config for ActiveMQ queue.
     *
     * @return queue instance
     */
    @Bean(name = "assignment.queue")
    public Queue queue() {
        return new ActiveMQQueue("assignment.queue");
    }

    /**
     * config for ActiveMQ topic.
     *
     * @return topic instance
     */
    @Bean(name = "assignment.topic")
    public Topic topic() {
        return new ActiveMQTopic("assignment.topic");
    }
}
