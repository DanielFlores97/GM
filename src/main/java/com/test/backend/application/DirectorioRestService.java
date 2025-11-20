package com.test.backend.application;

import com.test.backend.domain.Persona;
import com.test.backend.dto.PersonaRequestDto;
import com.test.backend.dto.PersonaResponseDto;
import com.test.backend.mapper.PersonaMapper;
import com.test.backend.service.IDirectorioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/directorio")
public class DirectorioRestService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorioRestService.class);

    @Autowired
    private final IDirectorioService directorioService;

    @GetMapping
    public ResponseEntity<Page<PersonaResponseDto>> getAllPersonas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        logger.info("GET /api/directorio - page: {}, size: {}", page, size);
        Page<PersonaResponseDto> response = directorioService.findPersonas(PageRequest.of(page, size));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{identificacion}")
    public ResponseEntity<PersonaResponseDto> getPersona(@PathVariable String identificacion) {
        logger.info("GET /api/directorio/{}", identificacion);
        PersonaResponseDto response = directorioService.findPersonaByIdentificacion(identificacion);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonaResponseDto> createPersona(
            @Valid @RequestBody PersonaRequestDto dto) {

        logger.info("POST /api/directorio");
        PersonaResponseDto response = directorioService.storePersona(dto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{identificacion}")
    public ResponseEntity<Void> deletePersona(@PathVariable String identificacion) {
        logger.info("DELETE /api/directorio/{}", identificacion);

        boolean deleted = directorioService.deletePersonaByIdentificacion(identificacion);
        if (!deleted) {
            logger.warn("Intento eliminar persona no encontrada: {}", identificacion);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
