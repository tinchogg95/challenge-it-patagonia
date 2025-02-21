package com.itPatagonia.challenge.application.exceptions;

import com.itPatagonia.challenge.application.controllers.EmpresaController;
import com.itPatagonia.challenge.domain.service.EmpresaService;
import com.itPatagonia.challenge.infraestructure.dto.EmpresaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpresaController.class)
@ExtendWith(MockitoExtension.class)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private EmpresaService empresaService;

    @BeforeEach
    void setUp() {
        empresaService = mock(EmpresaService.class);
    }

    @Test
    void whenIllegalArgumentException_thenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/empresas/adherir")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"cuit\": \"\", \"razonSocial\": \"\", \"fechaAdhesion\": \"\" }"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid CUIT"));
    }

    @Test
    void whenGenericException_thenReturnInternalServerError() throws Exception {
        doThrow(new Exception("Unexpected error")).when(empresaService).adherirEmpresa(Mockito.any());

        mockMvc.perform(post("/empresas/adherir")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"cuit\": \"20-12345678-9\", \"razonSocial\": \"Empresa Test\", \"fechaAdhesion\": \"2025-02-21\" }"))
                .andExpect(status().isInternalServerError())
                .andExpect(content().string("Ocurrió un error inesperado"));
    }
}
