package com.example.role_based_auth.repository;

import com.example.role_based_auth.entity.LocationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<LocationEntity, Long> {
}
