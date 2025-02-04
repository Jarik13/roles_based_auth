package com.example.role_based_auth.repository;

import com.example.role_based_auth.entity.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HallRepository extends JpaRepository<HallEntity, Long> {
}
