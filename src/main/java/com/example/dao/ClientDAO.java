package com.example.dao;

import com.example.clients.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Long> {
}
