package com.test.backend.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.backend.domain.Factura;
import com.test.backend.domain.Persona;
import com.test.backend.dto.FacturaRequestDto;
import com.test.backend.dto.FacturaResponseDto;
import com.test.backend.exception.NotFoundPersonasException;
import com.test.backend.mapper.FacturaMapper;
import com.test.backend.repository.FacturaRepository;
import com.test.backend.repository.PersonaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class FacturaService implements IFacturaService {

    private static final Logger logger = LoggerFactory.getLogger(FacturaService.class);

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private FacturaMapper facturaMapper;

    @Override
    public List<FacturaResponseDto> findFacturasByPersona(Integer idPersona) {

        logger.info("Buscando facturas para persona con ID: {}", idPersona);

        personaRepository.findById(idPersona)
                .orElseThrow(() ->
                        new NotFoundPersonasException("Persona con ID " + idPersona + " no encontrada")
                );

        List<FacturaResponseDto> response = facturaRepository.findByPersonaId(idPersona)
                .stream()
                .map(facturaMapper::toDto)
                .collect(Collectors.toList());

        logger.info("Facturas encontradas: {}", response.size());
        return response;
    }

    @Override
    public FacturaResponseDto storeFactura(Integer idPersona, FacturaRequestDto dto) {

        logger.info("Guardando factura para persona ID: {}", idPersona);

        Persona persona = personaRepository.findById(idPersona)
                .orElseThrow(() -> {
                    logger.error("Error: No se encontro la persona con ID {}", idPersona);
                    return new NotFoundPersonasException("No se encontro la persona con ID " + idPersona);
                });

        Factura factura = facturaMapper.toEntity(dto);
        factura.setPersona(persona);

        Factura saved = facturaRepository.save(factura);
        logger.info("Factura guardada correctamente: {}", saved);

        return facturaMapper.toDto(saved);
    }
}
