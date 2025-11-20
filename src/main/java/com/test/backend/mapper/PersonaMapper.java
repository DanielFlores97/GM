package com.test.backend.mapper;

import org.springframework.stereotype.Component;

import com.test.backend.domain.Persona;
import com.test.backend.dto.PersonaRequestDto;
import com.test.backend.dto.PersonaResponseDto;

@Component
public class PersonaMapper {

    public Persona toEntity(PersonaRequestDto dto) {
        if (dto == null) return null;

        return Persona.builder()
                .nombre(dto.getNombre())
                .apellidoPaterno(dto.getApellidoPaterno())
                .apellidoMaterno(dto.getApellidoMaterno())
                .identificacion(dto.getIdentificacion())
                .build();
    }

    public PersonaResponseDto toDto(Persona persona) {
        if (persona == null) return null;

        return PersonaResponseDto.builder()
                .id(persona.getId())
                .nombre(persona.getNombre())
                .apellidoPaterno(persona.getApellidoPaterno())
                .apellidoMaterno(persona.getApellidoMaterno())
                .identificacion(persona.getIdentificacion())
                .build();
    }
}
