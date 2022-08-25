package com.dev.backend.services;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dev.backend.models.Cidade;
import com.dev.backend.repositories.CidadeRepository;

@Service
public class CidadeService {
    private CidadeRepository cidadeRepository;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
    }

    @Transactional
    public Cidade save(Cidade cidadeModel) {
        return cidadeRepository.save(cidadeModel);
    }

    @Transactional
    public void delete(Cidade cidadeModel) {
        cidadeRepository.delete(cidadeModel);
    }

    public Optional<Cidade> findById(UUID id) {
        return cidadeRepository.findById(id);
    }

    public Page<Cidade> findAll(Pageable pageable) {
        return cidadeRepository.findAll(pageable);
    }

    
}
