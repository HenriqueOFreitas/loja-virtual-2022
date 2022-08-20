package com.dev.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.backend.models.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, UUID> {
    
}
