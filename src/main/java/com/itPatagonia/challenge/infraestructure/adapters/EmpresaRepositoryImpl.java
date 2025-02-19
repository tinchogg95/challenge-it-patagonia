package com.itPatagonia.challenge.infraestructure.adapters;


import org.springframework.stereotype.Repository;

import com.itPatagonia.challenge.domain.model.Empresa;
import com.itPatagonia.challenge.domain.ports.EmpresaRepository;

import java.util.List;

@Repository
public class EmpresaRepositoryImpl implements EmpresaRepository {
    // Aquí inyectas el repositorio de Spring Data JPA o MongoDB
    // y mapeas las consultas necesarias.

    @Override
    public List<Empresa> findEmpresasConTransferenciasUltimoMes() {
    }

    @Override
    public List<Empresa> findEmpresasAdheridasUltimoMes() {
        
    }

    @Override
    public void adherirEmpresa(Empresa empresa) {
    }
}