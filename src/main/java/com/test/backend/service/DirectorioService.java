package com.test.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.stereotype.Service;

import com.test.backend.domain.Persona;
import com.test.backend.dto.PersonaRequestDto;
import com.test.backend.dto.PersonaResponseDto;
import com.test.backend.exception.BadRequestException;
import com.test.backend.exception.NotFoundPersonasException;
import com.test.backend.mapper.PersonaMapper;
import com.test.backend.repository.PersonaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class DirectorioService implements IDirectorioService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorioService.class);

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private PersonaMapper personaMapper;

    @Override
    public PersonaResponseDto findPersonaByIdentificacion(String identificacion) {
        logger.info("Buscando persona con identificación: {}", identificacion);

        Persona persona = personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new NotFoundPersonasException(
                        "Persona con identificación " + identificacion + " no encontrada"
                ));

        PersonaResponseDto dto = personaMapper.toDto(persona);
        logger.info("Persona encontrada: {}", dto.getIdentificacion());
        return dto;
    }

    @Override
    public Page<PersonaResponseDto> findPersonas(Pageable pageable) {
        logger.info("Consultando personas registradas con paginación: page={}, size={}",
                pageable.getPageNumber(), pageable.getPageSize());

        Page<PersonaResponseDto> dtoPage = personaRepository.findAll(pageable)
                .map(personaMapper::toDto);

        logger.info("Se encontraron {} personas en la página {}",
                dtoPage.getNumberOfElements(), pageable.getPageNumber());
        return dtoPage;
    }

    @Override
    public Boolean deletePersonaByIdentificacion(String identificacion) {
        logger.info("Intentando eliminar persona con identificación: {}", identificacion);

        Persona persona = personaRepository.findByIdentificacion(identificacion)
                .orElseThrow(() -> new NotFoundPersonasException(
                        "No se puede eliminar. Persona " + identificacion + " no encontrada"
                ));

        personaRepository.delete(persona);
        logger.info("Persona {} eliminada correctamente", identificacion);

        return true;
    }

    @Override
    public PersonaResponseDto storePersona(PersonaRequestDto dto) {
        if (dto.getIdentificacion() == null || dto.getIdentificacion().isBlank()) {
            throw new BadRequestException("El campo 'identificación' es obligatorio");
        }

        logger.info("Guardando persona {}", dto.getIdentificacion());

        Persona persona = personaMapper.toEntity(dto);
        Persona saved = personaRepository.save(persona);
        PersonaResponseDto responseDto = personaMapper.toDto(saved);

        logger.info("Persona {} guardada correctamente", responseDto.getIdentificacion());
        return responseDto;
    }


}
