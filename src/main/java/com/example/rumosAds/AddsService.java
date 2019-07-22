package com.example.rumosAds;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Data
@Service
public class AddsService {

    private static final Logger LOGGER = Logger.getLogger("AddsService");

    private List<Adds> adds;

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    Queue queue;

    AddsService() {

        adds = new ArrayList<>();

        LOGGER.info("Adds Serivice: started");

        if (adds.isEmpty()) {
            LOGGER.info("persistOnInitialize: starts");

            Adds adds1 = new Adds();
            adds1.setAddId(1);
            adds1.setAddName("Java Course");
            adds1.setAddDescription("This us a Full Stack Java Course");
            adds1.setAddPrice(2.575);

            Adds adds2 = new Adds();
            adds2.setAddId(2);
            adds2.setAddName("Ruby Course");
            adds2.setAddDescription("This us a Full Stack Ruby Course");
            adds2.setAddPrice(2.250);

            adds.addAll(Arrays.asList(adds1, adds2));

            LOGGER.info("DATA PERSISTED");

        }else{

            LOGGER.info("LIST WAS ALREADY PERSISTED");
        }
    }


}
