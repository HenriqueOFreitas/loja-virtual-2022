package com.dev.backend.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.backend.models.Estado;
import com.dev.backend.repositories.EstadoRepository;

@Service
public class EstadoService {
    
    private EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    @Transactional
    public Estado save(Estado estadoModel) {
        return estadoRepository.save(estadoModel);
    }

    public Page<Estado> findAll(Pageable pageable) {
        return estadoRepository.findAll(pageable);
    }

    public Optional<Estado> findById(UUID id) {
        return estadoRepository.findById(id);
    }

    public void delete(Estado estadoModel) {
        estadoRepository.delete(estadoModel);
    }
    
}
