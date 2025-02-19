package com.itPatagonia.challenge.infraestructure;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class EmpresaDTO {
    @NotBlank(message = "El CUIT es obligatorio")
    private String cuit;

    @NotBlank(message = "La razón social es obligatoria")
    private String razonSocial;

    @NotNull(message = "La fecha de adhesión es obligatoria")
    private LocalDate fechaAdhesion;
}