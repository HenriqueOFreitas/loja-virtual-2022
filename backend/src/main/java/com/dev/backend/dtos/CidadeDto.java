package com.dev.backend.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.dev.backend.models.Estado;

import lombok.Data;

@Data
public class CidadeDto {

    @NotBlank
    @Size(max = 40)
    private String nome;

    @NotNull
    private Estado estado;

}
