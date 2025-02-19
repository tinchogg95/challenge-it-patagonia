package com.itPatagonia.challenge.domain.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class Empresa {
    private String cuit;
    private String razonSocial;
    private LocalDate fechaAdhesion;
}
