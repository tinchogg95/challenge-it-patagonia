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

    public EmpresaRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

   @Override
    public List<Empresa> findEmpresasConTransferenciasUltimoMes() {
        LocalDate fechaInicio = LocalDate.now().minusDays(30);
        LocalDate fechaFin = LocalDate.now();

        Query queryTransferencias = new Query();
        queryTransferencias.addCriteria(Criteria.where("fecha").gte(fechaInicio).lte(fechaFin));
        List<Transferencia> transferencias = mongoTemplate.find(queryTransferencias, Transferencia.class, "transferencias");

        List<String> idsEmpresas = transferencias.stream()
                .map(Transferencia::getIdEmpresa)
                .distinct()
                .collect(Collectors.toList());

        Query queryEmpresas = new Query();
        queryEmpresas.addCriteria(Criteria.where("id").in(idsEmpresas));
        return mongoTemplate.find(queryEmpresas, Empresa.class, "empresas");
    }

    @Override
    public List<Empresa> findEmpresasAdheridasUltimoMes() {
        LocalDate fechaInicio = LocalDate.now().minusDays(30);
        LocalDate fechaFin = LocalDate.now();

        Query query = new Query();
        query.addCriteria(Criteria.where("fechaAdhesion").gte(fechaInicio).lte(fechaFin));
        return mongoTemplate.find(query, Empresa.class, "empresas");
    }

    @Override
    public void adherirEmpresa(Empresa empresa) {
        mongoTemplate.save(empresa, "empresas");
    }
}