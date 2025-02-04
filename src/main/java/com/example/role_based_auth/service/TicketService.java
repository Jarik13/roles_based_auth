package com.example.role_based_auth.service;

import com.example.role_based_auth.entity.TicketEntity;
import org.springframework.stereotype.Service;

@Service
public interface TicketService {
    TicketEntity addTicket(Long userId, TicketEntity ticket);
    String deleteTicket(Long id);
}
