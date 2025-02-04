package com.example.role_based_auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "halls")
@Data
public class HallEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long number;
    private Long seatsCount;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL)
    private List<SessionEntity> sessions;

    @ManyToOne
    @JoinColumn(name = "hall_id", nullable = false)
    private LocationEntity location;
}
