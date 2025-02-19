package com.itPatagonia.challenge.application.controllers;


import org.springframework.web.bind.annotation.*;

import com.itPatagonia.challenge.domain.model.Empresa;
import com.itPatagonia.challenge.domain.service.EmpresaService;
import com.itPatagonia.challenge.infraestructure.EmpresaDTO;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/transferencias-ultimo-mes")
    public List<Empresa> obtenerEmpresasConTransferenciasUltimoMes() {
        return empresaService.obtenerEmpresasConTransferenciasUltimoMes();
    }

    @GetMapping("/adheridas-ultimo-mes")
    public List<Empresa> obtenerEmpresasAdheridasUltimoMes() {
        return empresaService.obtenerEmpresasAdheridasUltimoMes();
    }

    @PostMapping("/adherir")
    public void adherirEmpresa(@Valid @RequestBody EmpresaDTO empresaDTO) {
        Empresa empresa = new Empresa();
        empresa.setCuit(empresaDTO.getCuit());
        empresa.setRazonSocial(empresaDTO.getRazonSocial());
        empresa.setFechaAdhesion(empresaDTO.getFechaAdhesion());
        empresaService.adherirEmpresa(empresa);
    }
}