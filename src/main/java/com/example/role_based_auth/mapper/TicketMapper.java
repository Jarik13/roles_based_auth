package com.example.role_based_auth.mapper;

import com.example.role_based_auth.dto.TicketDto;
import com.example.role_based_auth.entity.TicketEntity;

public class TicketMapper {
    public static TicketDto toModel(TicketEntity ticketDto) {
        return new TicketDto(ticketDto.getSeatNumber(), ticketDto.getPrice());
    }
}
