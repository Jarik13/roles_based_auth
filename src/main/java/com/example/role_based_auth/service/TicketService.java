package com.example.role_based_auth.service;

import com.example.role_based_auth.dto.TicketDto;
import com.example.role_based_auth.entity.TicketEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    TicketEntity addTicket(Long userId, TicketEntity ticket);
    String deleteTicket(Long id);
    List<TicketDto> getAllUserTickets(Long id);
}
