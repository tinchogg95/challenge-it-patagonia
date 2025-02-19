package com.itPatagonia.challenge.domain.ports;

import java.util.List;

import com.itPatagonia.challenge.domain.model.Empresa;

public interface EmpresaRepository {
    List<Empresa> findEmpresasConTransferenciasUltimoMes();
    List<Empresa> findEmpresasAdheridasUltimoMes();
    void adherirEmpresa(Empresa empresa);
}