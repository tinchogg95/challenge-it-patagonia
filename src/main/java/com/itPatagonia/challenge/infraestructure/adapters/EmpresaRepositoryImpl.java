package com.itPatagonia.challenge.infraestructure.adapters;


import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.itPatagonia.challenge.domain.model.Empresa;
import com.itPatagonia.challenge.domain.model.Transferencia;
import com.itPatagonia.challenge.domain.ports.EmpresaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmpresaRepositoryImpl implements EmpresaRepository {

    private final MongoTemplate mongoTemplate;
    private final static String FECHA_KEY = "fecha";
    private final static String ID_KEY = "id";
    private final static String FECHA_AD_KEY = "fechaAdhesion";
    private final static String TRANSFERENCIAS_COLLECTION = "transferencias";
    private final static String EMPRESAS_COLLECTION = "empresas";
    final static Integer DIAS_A_CALCULAR = 30;


    public EmpresaRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

   @Override
    public List<Empresa> findEmpresasConTransferenciasUltimoMes() {
        LocalDate fechaInicio = LocalDate.now().minusDays(DIAS_A_CALCULAR);
        LocalDate fechaFin = LocalDate.now();

        Query queryTransferencias = new Query();
        queryTransferencias.addCriteria(Criteria.where(FECHA_KEY).gte(fechaInicio).lte(fechaFin));
        //busca transferencias entre hoy y 30 dias atras
        List<Transferencia> transferencias = mongoTemplate.find(queryTransferencias, Transferencia.class, TRANSFERENCIAS_COLLECTION);
        List<String> idsEmpresas = transferencias.stream()
                .map(Transferencia::getIdEmpresa)
                .distinct()
                .collect(Collectors.toList());
        //obtiene el id de las empresas que realizaron transferencias el ultimo mes
        Query queryEmpresas = new Query();
        queryEmpresas.addCriteria(Criteria.where(ID_KEY).in(idsEmpresas));
        return mongoTemplate.find(queryEmpresas, Empresa.class, EMPRESAS_COLLECTION);
    }

    @Override
    public List<Empresa> findEmpresasAdheridasUltimoMes() {
        LocalDate fechaInicio = LocalDate.now().minusDays(DIAS_A_CALCULAR);
        LocalDate fechaFin = LocalDate.now();

        Query query = new Query();
        query.addCriteria(Criteria.where(FECHA_AD_KEY).gte(fechaInicio).lte(fechaFin));
        return mongoTemplate.find(query, Empresa.class, EMPRESAS_COLLECTION);
    }

    @Override
    public void adherirEmpresa(Empresa empresa) {
        mongoTemplate.save(empresa, EMPRESAS_COLLECTION);
    }
}