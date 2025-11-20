package com.test.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PersonaRequestDto {

	    @NotBlank(message = "El nombre es obligatorio")
	    private String nombre;

	    @NotBlank(message = "El apellido paterno es obligatorio")
	    private String apellidoPaterno;

	    private String apellidoMaterno;

	    @NotBlank(message = "La identificación es obligatoria")
	    private String identificacion;
	}

