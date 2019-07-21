package com.example.messages;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.logging.Logger;

@Component
public class InternalListener {

    private static final Logger LOGGER = Logger.getLogger("MessageProducer");

    //Just for Debugging
    @JmsListener(destination = "standalone.queue")
    public void consume(String message) {

        LOGGER.info("Consumes : started with message " + message);

    }
}
