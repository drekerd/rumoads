package com.example.messages;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.JMSException;
import javax.jms.Queue;
import java.util.logging.Logger;

@Configuration
public class MessageController {

    private static final Logger LOGGER = Logger.getLogger("MessageController");

    @Value("${activemq.broker-url}")
    private String brokerUrl;

    @Autowired
    private JmsTemplate jmsTemplate;

    @Bean
    public Queue queue() {
        LOGGER.info("Queue : started");
        return new ActiveMQQueue("standalone.queue");
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        LOGGER.info("ActiveMQConnectionFactory : started");
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
        factory.setBrokerURL(brokerUrl);
        LOGGER.info("ActiveMQConnectionFactory : ended, with brokerURL at=" + factory.getBrokerURL());
        return factory;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        LOGGER.info("JmsTemplate : started");
        return new JmsTemplate(activeMQConnectionFactory());
    }
}