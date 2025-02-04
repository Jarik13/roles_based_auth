package com.example.role_based_auth.service.impl;

import com.example.role_based_auth.entity.TicketEntity;
import com.example.role_based_auth.entity.UserEntity;
import com.example.role_based_auth.repository.TicketRepository;
import com.example.role_based_auth.service.DefaultUserService;
import com.example.role_based_auth.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    DefaultUserService userService;

    @Override
    public TicketEntity addTicket(Long userId, TicketEntity ticket) {
        UserEntity user = userService.getUserById(userId);
        if (user == null) throw new UsernameNotFoundException("User not found!");

        ticket.setUser(user);
        return ticketRepository.save(ticket);
    }

    @Override
    public String deleteTicket(Long id) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found!"));
        ticketRepository.delete(ticket);
        return "Ticket deleted successfully!";
    }
}
