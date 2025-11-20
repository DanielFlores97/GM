package com.test.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonaResponseDto {
	    private Integer id;
	    private String nombre;
	    private String apellidoPaterno;
	    private String apellidoMaterno;
	    private String identificacion;
}
