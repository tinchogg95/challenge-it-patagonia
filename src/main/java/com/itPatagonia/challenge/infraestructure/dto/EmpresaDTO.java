package com.itPatagonia.challenge.infraestructure.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EmpresaDTO {
    private final static String CUIT_ERROR = "El CUIT es obligatorio";
    private final static String RAZON_SOCIAL_ERROR = "La razón social es obligatoria";
    private final static String FECHA_ADHESION_ERROR = "La fecha de adhesión es obligatoria";
    public EmpresaDTO(String cuit, String razonSocial, LocalDate fechaAdhesion) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fechaAdhesion = fechaAdhesion;
    }

    public EmpresaDTO() {
        super();
    }

    @NotBlank(message = CUIT_ERROR)
    private String cuit;

    @NotBlank(message = RAZON_SOCIAL_ERROR)
    private String razonSocial;

    @NotNull(message = FECHA_ADHESION_ERROR)
    private LocalDate fechaAdhesion;
}