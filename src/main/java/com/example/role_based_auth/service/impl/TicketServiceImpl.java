package com.example.role_based_auth.service.impl;

import com.example.role_based_auth.dto.TicketDto;
import com.example.role_based_auth.entity.TicketEntity;
import com.example.role_based_auth.entity.UserEntity;
import com.example.role_based_auth.mapper.TicketMapper;
import com.example.role_based_auth.repository.TicketRepository;
import com.example.role_based_auth.service.DefaultUserService;
import com.example.role_based_auth.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    DefaultUserService userService;

    @Override
    public TicketDto addTicket(Long userId, TicketEntity ticket) {
        UserEntity user = userService.getUserById(userId);
        if (user == null) throw new UsernameNotFoundException("User not found!");

        ticket.setUser(user);
        return TicketMapper.toModel(ticketRepository.save(ticket));
    }

    @Override
    public String deleteTicket(Long id) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found!"));
        ticketRepository.delete(ticket);
        return "Ticket deleted successfully!";
    }

    @Override
    public List<TicketDto> getAllUserTickets(Long id) {
        UserEntity user = userService.getUserById(id);
        if (user == null) throw new UsernameNotFoundException("User not found!");

        return user.getTickets().stream().map(TicketMapper::toModel).toList();
    }

    @Override
    public TicketDto getTicketById(Long userId, Long ticketId) {
        UserEntity user = userService.getUserById(userId);
        if (user == null) throw new UsernameNotFoundException("User not found!");

        TicketEntity ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        if (!ticket.getUser().getId().equals(userId)) {
            throw new RuntimeException("Ticket does not belong to user with id: " + userId);
        }

        return TicketMapper.toModel(ticket);
    }
}
