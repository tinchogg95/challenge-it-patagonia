package com.itPatagonia.challenge.infraestructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Schema(description = "DTO para la creación o actualización de una empresa")
public class EmpresaDTO {
    private static final String CUIT_ERROR = "El CUIT es obligatorio";
    private static final String RAZON_SOCIAL_ERROR = "La razón social es obligatoria";
    private static final String FECHA_ADHESION_ERROR = "La fecha de adhesión es obligatoria";
    public EmpresaDTO(String cuit, String razonSocial, LocalDate fechaAdhesion) {
        this.cuit = cuit;
        this.razonSocial = razonSocial;
        this.fechaAdhesion = fechaAdhesion;
    }
    public EmpresaDTO() {
        super();
    }

    @NotBlank(message = CUIT_ERROR)
    @Schema(description = "CUIT de la empresa", example = "30-12345678-9", required = true)
    private String cuit;

    @NotBlank(message = RAZON_SOCIAL_ERROR)
    @Schema(description = "Razón social de la empresa", example = "Empresa de Prueba", required = true)
    private String razonSocial;

    @NotNull(message = FECHA_ADHESION_ERROR)
    @Schema(description = "Fecha de adhesión de la empresa", example = "2023-10-01", required = true)
    private LocalDate fechaAdhesion;
}