package com.example.role_based_auth.repository;

import com.example.role_based_auth.entity.FilmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmRepository extends JpaRepository<FilmEntity, Long> {
}
