package com.example.role_based_auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "sessions")
@Data
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private LocalDate data;

    @OneToOne
    @JoinColumn(name = "film_id", nullable = false)
    private FilmEntity film;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private HallEntity hall;
}
