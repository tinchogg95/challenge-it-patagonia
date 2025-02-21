package com.itPatagonia.challenge.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "transferencias")
public class Transferencia {
    @Id
    private String id;
    private Double importe;
    private String idEmpresa;
    private String cuentaDebito;
    private String cuentaCredito;private LocalDate fechaAdhesion;

    
}