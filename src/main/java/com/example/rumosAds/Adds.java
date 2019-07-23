package com.example.rumosAds;

import lombok.Data;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.logging.Logger;

@Data
@Service
@Entity(name = "course")
public class Adds {

    private static final Logger LOGGER = Logger.getLogger("public class Adds");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long addId;

    @Column(nullable = false)
    private String addName;

    @Column(nullable = false)
    private String addDescription;

    @Column(nullable = false)
    private double addPrice;
}