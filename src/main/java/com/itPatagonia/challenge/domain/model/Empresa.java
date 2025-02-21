package com.itPatagonia.challenge.domain.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection = "empresas")
@CompoundIndex(name = "fecha_adhesion_idx", def = "{'fechaAdhesion': 1}")
public class Empresa {
    @Id
    private String id; 
    private String cuit;
    private String razonSocial;
    private LocalDate fechaAdhesion;

 
    public Empresa() {
        super();
    }
    public Empresa(String id, String cuit, String razonSocial, LocalDate fechaAdhesion) {
        this.id = id;
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fechaAdhesion = fechaAdhesion;
    }
    public Empresa(String cuit, String razonSocial, LocalDate fechaAdhesion) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fechaAdhesion = fechaAdhesion;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCuit() {
        return cuit;
    }
    public void setCuit(String cuit) {
        this.cuit = cuit;
    }
    public String getRazonSocial() {
        return razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public LocalDate getFechaAdhesion() {
        return fechaAdhesion;
    }
    public void setFechaAdhesion(LocalDate fechaAdhesion) {
        this.fechaAdhesion = fechaAdhesion;
    }
}
