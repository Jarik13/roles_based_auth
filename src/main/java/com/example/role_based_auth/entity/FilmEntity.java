package com.example.role_based_auth.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "films")
@Data
public class FilmEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Long releaseYear;
    private String imageUrl;

    @OneToOne(mappedBy = "film", cascade = CascadeType.ALL)
    private SessionEntity session;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "films_genre",
            joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id")
    )
    private Set<GenreEntity> genres = new HashSet<>();
}
