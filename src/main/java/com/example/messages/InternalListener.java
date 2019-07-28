package com.example.messages;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.time.LocalTime;
import java.util.logging.Logger;

@Component
public class InternalListener {

    /**
     * this class is just for debugging and initial stagings of developments
     * ex: new message queue URL
     * */

    private static final Logger LOGGER = Logger.getLogger("MessageProducer");



    @JmsListener(destination = "flag.queue")
    public void consumeFromFlag(String message) {
        LOGGER.info("Consumes FLAG: started with message " + message);
    }

    @JmsListener(destination = "galileu.queue")
    public void consumeFromGalileu(String message) {
        LOGGER.info("Consumes GALILEU : started with message " + message);
    }
}
