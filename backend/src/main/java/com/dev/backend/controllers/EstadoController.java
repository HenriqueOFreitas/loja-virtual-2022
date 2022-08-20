package com.dev.backend.controllers;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.backend.dtos.EstadoDto;
import com.dev.backend.models.Estado;
import com.dev.backend.services.EstadoService;

@Controller
@RequestMapping("/api/estado")
public class EstadoController {
    
    final EstadoService estadoService;

    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public ResponseEntity<Page<Estado>> buscarTodos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.findAll(pageable));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> buscar(@PathVariable(value = "id") UUID id) {
        Optional<Estado> estadoOptional = estadoService.findById(id);
        if(!estadoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não foi encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(estadoOptional.get());
    }

    @PostMapping
    public ResponseEntity<Estado> inserir(@RequestBody @Valid EstadoDto estadoDto) {
        var estadoModel = new Estado();
        BeanUtils.copyProperties(estadoDto, estadoModel);
        estadoModel.setDataCriacao(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoService.save(estadoModel));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> alterar(@PathVariable(value = "id") UUID id, @RequestBody @Valid EstadoDto estadoDto) {
        Optional<Estado> estadoOptional = estadoService.findById(id);
        if(!estadoOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não existe!");
        }
        Estado estadoModel = new Estado();
        BeanUtils.copyProperties(estadoDto, estadoModel);
        estadoModel.setId(estadoOptional.get().getId());
        estadoModel.setDataCriacao(estadoOptional.get().getDataCriacao());
        estadoModel.setDataAtualizacao(new Date());

        return ResponseEntity.status(HttpStatus.OK).body(estadoService.save(estadoModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value="id") UUID id) {
        Optional<Estado> estadooOptional = estadoService.findById(id);
        if(!estadooOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Estado não existe!");
        }

        estadoService.delete(estadooOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Estado deletado com sucesso");
    }

}
