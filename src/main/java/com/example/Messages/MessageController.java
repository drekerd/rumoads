package com.example.Messages;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;

import java.util.logging.Logger;

@Controller
public class MessageController{

    private static final Logger LOGGER = Logger.getLogger("MessageController");

    @Autowired private JmsTemplate jmsTemplate;

    public void send(String message) {
        LOGGER.info("sending message='{}'" + message);
        jmsTemplate.convertAndSend("helloworld.q", message);
    }
}