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
}
