package com.dev.backend.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "estado")
@Data
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String nome;

    @Column
    private String sigla;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAtualizacao;
}