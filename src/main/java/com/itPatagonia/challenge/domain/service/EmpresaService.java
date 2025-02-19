package com.itPatagonia.challenge.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.itPatagonia.challenge.domain.model.Empresa;
import com.itPatagonia.challenge.domain.ports.EmpresaRepository;

@Service
public class EmpresaService {
    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public List<Empresa> obtenerEmpresasConTransferenciasUltimoMes() {
        return empresaRepository.findEmpresasConTransferenciasUltimoMes();
    }

    public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
        return empresaRepository.findEmpresasAdheridasUltimoMes();
    }

    public void adherirEmpresa(Empresa empresa) {
        empresaRepository.adherirEmpresa(empresa);
    }
}