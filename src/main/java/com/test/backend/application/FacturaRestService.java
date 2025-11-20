package com.test.backend.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.test.backend.domain.Factura;
import com.test.backend.domain.Persona;
import com.test.backend.dto.FacturaRequestDto;
import com.test.backend.dto.FacturaResponseDto;
import com.test.backend.mapper.FacturaMapper;
import com.test.backend.service.IFacturaService;
import com.test.backend.service.IDirectorioService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/facturas")
public class FacturaRestService {

    private static final Logger logger = LoggerFactory.getLogger(FacturaRestService.class);

    @Autowired
    private IFacturaService facturaService;

    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<List<FacturaResponseDto>> getFacturasByPersona(@PathVariable Integer idPersona) {

        logger.info("GET /api/facturas/persona/{}", idPersona);

        List<FacturaResponseDto> response = facturaService.findFacturasByPersona(idPersona);

        logger.info("Se retornan {} facturas", response.size());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/persona")
    public ResponseEntity<FacturaResponseDto> createFactura(@Valid @RequestBody FacturaRequestDto dto) {

        logger.info("POST /api/facturas/persona");

        FacturaResponseDto response = facturaService.storeFactura(dto.getIdPersona(), dto);

        logger.info("Factura creada correctamente: {}", response);
        return ResponseEntity.ok(response);
    }
}
