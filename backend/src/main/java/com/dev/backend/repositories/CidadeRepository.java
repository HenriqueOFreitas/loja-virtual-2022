package com.dev.backend.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.backend.models.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, UUID>{
    
}
