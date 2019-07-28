package com.example.clients;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.security.SecureRandom;

@Data
@Entity(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientID;
    private String clientName;
    private long preferedCategory;
    private String CLIENT_QUEUE;
}
