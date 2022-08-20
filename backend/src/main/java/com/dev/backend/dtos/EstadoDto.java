package com.dev.backend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class EstadoDto {

    @NotBlank
    private String nome;

    @NotBlank
    @Size(max = 2)
    private String sigla;
    
}
