package com.example.messages;

import com.example.rumosAds.Adds;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.jms.Queue;
import java.util.logging.Logger;


@Controller
public class MessageProducer {

    private static final Logger LOGGER = Logger.getLogger("MessageProducer");

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    //@TODO find away to replace this method
    @GetMapping("/publish/{message}")
    public String postMessage(@PathVariable("message") String message) {

        LOGGER.info("message : PUBLISHED, with message " + message);
        jmsTemplate.convertAndSend(queue, message);
        return "adminCrud";
    }

    //@TODO Should surpress postMessage and start using this method
    public String publishMessage(String add) {
        LOGGER.info("message : PUBLISHED, with message " + add.toString());
        jmsTemplate.convertAndSend(queue, add);

        return "Published Successfully";
    }

    private String serializationToJson(Adds add) {
        Gson gson = new Gson();
        return gson.toJson(add);
    }

}
