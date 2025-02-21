package com.itPatagonia.challenge.infraestructure.adapters;

import com.itPatagonia.challenge.domain.model.Empresa;
import com.itPatagonia.challenge.domain.model.Transferencia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class EmpresaRepositoryImplTest {

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private EmpresaRepositoryImpl empresaRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findEmpresasConTransferenciasUltimoMes_DebeRetornarListaDeEmpresas() {
        LocalDate fechaInicio = LocalDate.now().minusDays(30);
        LocalDate fechaFin = LocalDate.now();

        Transferencia transferencia1 = new Transferencia();
        transferencia1.setIdEmpresa("1");
        transferencia1.setFecha(LocalDate.now().minusDays(10));

        Transferencia transferencia2 = new Transferencia();
        transferencia2.setIdEmpresa("2");
        transferencia2.setFecha(LocalDate.now().minusDays(5));

        Transferencia transferencia3 = new Transferencia();
        transferencia3.setIdEmpresa("3");
        transferencia3.setFecha(LocalDate.now().minusDays(31));
        List<Transferencia> transferencias = Arrays.asList(transferencia1, transferencia2, transferencia3);

        Empresa empresa1 = new Empresa();
        empresa1.setId("1");
        empresa1.setCuit("30-12345678-9");
        empresa1.setRazonSocial("Empresa A");

        Empresa empresa2 = new Empresa();
        empresa2.setId("2");
        empresa2.setCuit("27-98765432-1");
        empresa2.setRazonSocial("Empresa B");

        Empresa empresa3 = new Empresa();
        empresa3.setId("3");
        empresa3.setCuit("27-11165432-1");
        empresa3.setRazonSocial("Empresa C");

        List<Empresa> empresas = Arrays.asList(empresa1, empresa2, empresa3);

        when(mongoTemplate.find(any(Query.class), eq(Transferencia.class), eq("transferencias")))
                .thenReturn(transferencias);

        when(mongoTemplate.find(any(Query.class), eq(Empresa.class), eq("empresas")))
                .thenReturn(empresas);

        List<Empresa> resultado = empresaRepository.findEmpresasConTransferenciasUltimoMes();

        assertEquals(2, resultado.size());
        assertEquals("1", resultado.get(0).getId());
        assertEquals("2", resultado.get(1).getId());
    }

    @Test
    void findEmpresasAdheridasUltimoMes_DebeRetornarListaDeEmpresas() {
        LocalDate fechaInicio = LocalDate.now().minusDays(30);
        LocalDate fechaFin = LocalDate.now();

        Empresa empresa1 = new Empresa();
        empresa1.setId("1");
        empresa1.setCuit("30-12345678-9");
        empresa1.setRazonSocial("Empresa A");
        empresa1.setFechaAdhesion(LocalDate.now().minusDays(10));

        Empresa empresa2 = new Empresa();
        empresa2.setId("2");
        empresa2.setCuit("27-98765432-1");
        empresa2.setRazonSocial("Empresa B");
        empresa2.setFechaAdhesion(LocalDate.now().minusDays(5));

        Empresa empresa3 = new Empresa();
        empresa3.setId("3");
        empresa3.setCuit("27-98765412-1");
        empresa3.setRazonSocial("Empresa C");
        empresa3.setFechaAdhesion(LocalDate.now().minusDays(40));

        List<Empresa> empresas = Arrays.asList(empresa1, empresa2, empresa3);

        when(mongoTemplate.find(any(Query.class), eq(Empresa.class), eq("empresas")))
                .thenReturn(empresas);

        List<Empresa> resultado = empresaRepository.findEmpresasAdheridasUltimoMes();

        assertEquals(2, resultado.size());
        assertEquals("1", resultado.get(0).getId());
        assertEquals("2", resultado.get(1).getId());
    }

    @Test
    void adherirEmpresa_DebeGuardarEmpresaEnLaBaseDeDatos() {
        Empresa empresa = new Empresa();
        empresa.setId("1");
        empresa.setCuit("30-12345678-9");
        empresa.setRazonSocial("Empresa A");
        empresa.setFechaAdhesion(LocalDate.now());

        when(mongoTemplate.save(any(Empresa.class), eq("empresas"))).thenReturn(empresa);

        empresaRepository.adherirEmpresa(empresa);

        verify(mongoTemplate).save(empresa, "empresas");
    }
}