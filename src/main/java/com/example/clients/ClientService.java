package com.example.clients;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ClientService {

    List<Client> clientList;
}
