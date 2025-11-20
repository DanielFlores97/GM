package com.test.backend.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FacturaRequestDto {

	@NotNull(message = "El id es obligatorio")
	private Integer idPersona;

	@NotNull(message = "La fecha es obligatoria")
	private LocalDate fecha;

	@NotNull(message = "El total es obligatorio")
	private Double monto;
}
