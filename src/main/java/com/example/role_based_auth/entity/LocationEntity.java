package com.example.role_based_auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "locations")
@Data
public class LocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    @OneToMany(mappedBy = "location")
    private List<HallEntity> halls;
}
