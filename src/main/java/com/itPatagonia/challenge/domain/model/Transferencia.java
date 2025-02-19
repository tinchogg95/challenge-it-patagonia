package com.itPatagonia.challenge.domain.model;

import lombok.Data;

@Data
public class Transferencia {
    private Long id;
    private Double importe;
    private String idEmpresa;
    private String cuentaDebito;
    private String cuentaCredito;
}