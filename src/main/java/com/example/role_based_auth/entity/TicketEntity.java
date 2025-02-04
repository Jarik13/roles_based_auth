package com.example.role_based_auth.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tickets")
@Data
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private Long seatNumber;
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    UserEntity user;
}
