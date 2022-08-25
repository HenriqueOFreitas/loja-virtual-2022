package com.dev.backend.controllers;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import com.dev.backend.dtos.CidadeDto;
import com.dev.backend.models.Cidade;
import com.dev.backend.models.Estado;
import com.dev.backend.services.CidadeService;
import com.dev.backend.services.EstadoService;

@Controller
@RequestMapping("/api/cidade")
public class CidadeController {

    private CidadeService cidadeService;
    private EstadoService estadoService;

    public CidadeController(CidadeService cidadeService, EstadoService estadoService) {
        this.cidadeService = cidadeService;
        this.estadoService = estadoService;
    }

    @GetMapping(value = "/")
    public ResponseEntity<Page<Cidade>> buscarTodos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.findAll(pageable));
    }
    
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> buscar(@PathVariable(value = "id") UUID id) {
        Optional<Cidade> cidadeOptional = cidadeService.findById(id);
        if(!cidadeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A cidade informada não existe!");
        }

        return ResponseEntity.status(HttpStatus.OK).body(cidadeOptional.get());
    }

    @PostMapping(value = "/")
    public ResponseEntity<Cidade> inserir(@RequestBody @Valid CidadeDto cidadeDto) {
        Optional<Estado> estadoOptional = estadoService.findById(cidadeDto.getEstado().getId());
        Cidade cidadeModel = new Cidade();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setEstado(estadoOptional.get());
        cidadeModel.setDataCriacao(new Date());
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeService.save(cidadeModel));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> alterar(@PathVariable(value = "id") UUID id, @RequestBody @Valid CidadeDto cidadeDto) {
        Optional<Cidade> cidadeOptional = cidadeService.findById(id);
        if(!cidadeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A cidade informada não existe!");
        }
        Cidade cidadeModel = new Cidade();
        BeanUtils.copyProperties(cidadeDto, cidadeModel);
        cidadeModel.setId(cidadeOptional.get().getId());
        cidadeModel.setDataCriacao(cidadeOptional.get().getDataCriacao());
        cidadeModel.setDataAtualizacao(new Date());
        cidadeModel.setEstado(cidadeOptional.get().getEstado());
        
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.save(cidadeModel));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deletar(@PathVariable(value = "id") UUID id) {
        Optional<Cidade> cidadeOptional = cidadeService.findById(id);
        if(!cidadeOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("A cidade informada não existe!");
        }
        cidadeService.delete(cidadeOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cidade deletada com sucesso!");
    }


}
