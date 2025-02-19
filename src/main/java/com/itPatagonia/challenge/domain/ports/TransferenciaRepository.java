package com.itPatagonia.challenge.domain.ports;

import java.time.LocalDate;
import java.util.List;

import com.itPatagonia.challenge.domain.model.Transferencia;

public interface TransferenciaRepository {
    List<Transferencia> findByEmpresaIdAndFecha(String idEmpresa, LocalDate fecha);
}