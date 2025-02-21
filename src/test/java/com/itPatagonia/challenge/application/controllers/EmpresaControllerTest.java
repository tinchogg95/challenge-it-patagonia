package com.itPatagonia.challenge.application.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itPatagonia.challenge.domain.model.Empresa;
import com.itPatagonia.challenge.domain.service.EmpresaService;
import com.itPatagonia.challenge.infraestructure.dto.EmpresaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@WebMvcTest(EmpresaController.class)
class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpresaService empresaService;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testObtenerEmpresasConTransferenciasUltimoMes() throws Exception {
        List<Empresa> empresas = Arrays.asList(
                new Empresa("1", "Empresa A", LocalDate.now()),
                new Empresa("2", "Empresa B", LocalDate.now())
        );

        when(empresaService.obtenerEmpresasConTransferenciasUltimoMes()).thenReturn(empresas);

        mockMvc.perform(get("/empresas/transferencias-ultimo-mes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(empresas.size()));
    }

    @Test
    void testObtenerEmpresasAdheridasUltimoMes() throws Exception {
        List<Empresa> empresas = Arrays.asList(
                new Empresa("1", "Empresa A", LocalDate.now()),
                new Empresa("2", "Empresa B", LocalDate.now())
        );

        when(empresaService.obtenerEmpresasAdheridasUltimoMes()).thenReturn(empresas);

        mockMvc.perform(get("/empresas/adheridas-ultimo-mes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(empresas.size()));
    }

    @Test
    void testAdherirEmpresa() throws Exception {
        EmpresaDTO empresaDTO = new EmpresaDTO("30-12345678-9", "Empresa C", LocalDate.now());

        doNothing().when(empresaService).adherirEmpresa(any(Empresa.class));

        mockMvc.perform(post("/empresas/adherir")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(empresaDTO)))
                .andExpect(status().isOk());
    }
}
