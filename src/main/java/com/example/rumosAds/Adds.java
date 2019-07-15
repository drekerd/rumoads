package com.example.rumosAds;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Data
@Service
public class Adds {

    private static final Logger LOGGER = Logger.getLogger("public class Adds");

    private long addId;
    private String addName;
    private String addDescription;
    private double addPrice;
}