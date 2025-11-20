package com.test.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.test.backend.dto.PersonaRequestDto;
import com.test.backend.dto.PersonaResponseDto;

public interface IDirectorioService {
    PersonaResponseDto findPersonaByIdentificacion(String identificacion);

    Boolean deletePersonaByIdentificacion(String identificacion);

    PersonaResponseDto storePersona(PersonaRequestDto dto);
    
    Page<PersonaResponseDto> findPersonas(Pageable pageable);
}
