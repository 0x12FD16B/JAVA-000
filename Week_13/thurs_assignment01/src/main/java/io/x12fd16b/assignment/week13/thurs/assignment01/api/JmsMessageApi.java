package io.x12fd16b.assignment.week13.thurs.assignment01.api;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.Map;

/**
 * message api.
 *
 * @author David Liu
 */
@RestController
@RequestMapping("/jms")
public class JmsMessageApi implements ApplicationContextAware {

    private final JmsTemplate jmsTemplate;

    private ApplicationContext applicationContext;

    public JmsMessageApi(final JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @PostMapping("sendMessage2Queue")
    public void sendMessage2Queue(@RequestBody MessageDestination messageDestination) {
        Map<String, Queue> beansOfQueueType = applicationContext.getBeansOfType(Queue.class);
        if (!beansOfQueueType.containsKey(messageDestination.getDestination())) {
            throw new RuntimeException("发送目标不存在");
        }
        jmsTemplate.convertAndSend(beansOfQueueType.get(messageDestination.getDestination()), messageDestination.getMessage());
    }

    @PostMapping("sendMessage2Topic")
    public void sendMessage2Topic(@RequestBody MessageDestination messageDestination) {
        Map<String, Topic> beansOfTopicType = applicationContext.getBeansOfType(Topic.class);
        if (!beansOfTopicType.containsKey(messageDestination.getDestination())) {
            throw new RuntimeException("发送目标不存在");
        }
        jmsTemplate.convertAndSend(beansOfTopicType.get(messageDestination.getDestination()), messageDestination.getMessage());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
