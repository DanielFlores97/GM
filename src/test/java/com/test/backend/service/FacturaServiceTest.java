package com.test.backend.service;

import com.test.backend.domain.Factura;
import com.test.backend.domain.Persona;
import com.test.backend.dto.FacturaRequestDto;
import com.test.backend.dto.FacturaResponseDto;
import com.test.backend.exception.NotFoundPersonasException;
import com.test.backend.mapper.FacturaMapper;
import com.test.backend.repository.FacturaRepository;
import com.test.backend.repository.PersonaRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FacturaServiceTest {

    @Mock
    private FacturaRepository facturaRepository;

    @Mock
    private PersonaRepository personaRepository;

    @Mock
    private FacturaMapper facturaMapper;

    @InjectMocks
    private FacturaService facturaService;

    private Persona persona;
    private Factura factura;
    private FacturaResponseDto facturaResponseDto;
    private FacturaRequestDto facturaRequestDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        persona = new Persona();
        persona.setId(1);
        persona.setNombre("Carlos");
        persona.setApellidoMaterno("Perez");
        persona.setIdentificacion("132123");

        factura = new Factura();
        factura.setId(1);
        factura.setFecha(LocalDate.now());
        factura.setMonto(500.0);
        factura.setPersona(persona);

        facturaRequestDto = new FacturaRequestDto();
        facturaRequestDto.setIdPersona(1);
        facturaRequestDto.setFecha(factura.getFecha());
        facturaRequestDto.setMonto(factura.getMonto());

        facturaResponseDto = new FacturaResponseDto();
        facturaResponseDto.setId(factura.getId());
        facturaResponseDto.setFecha(factura.getFecha());
        facturaResponseDto.setMonto(factura.getMonto());
        facturaResponseDto.setPersonaId(persona.getId());
    }

    @Test
    void testStoreFactura() {
        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        when(facturaMapper.toEntity(facturaRequestDto)).thenReturn(factura);
        when(facturaRepository.save(factura)).thenReturn(factura);
        when(facturaMapper.toDto(factura)).thenReturn(facturaResponseDto);

        FacturaResponseDto saved = facturaService.storeFactura(1, facturaRequestDto);

        assertNotNull(saved);
        assertEquals(500.0, saved.getMonto());
        verify(facturaRepository, times(1)).save(factura);
        verify(facturaMapper, times(1)).toDto(factura);
    }

    @Test
    void testFindFacturasByPersona() {
        Factura f1 = new Factura();
        f1.setId(1);
        f1.setFecha(LocalDate.now());
        f1.setMonto(200.0);
        f1.setPersona(persona);

        Factura f2 = new Factura();
        f2.setId(2);
        f2.setFecha(LocalDate.now());
        f2.setMonto(300.0);
        f2.setPersona(persona);

        FacturaResponseDto dto1 = new FacturaResponseDto();
        dto1.setId(1);
        dto1.setMonto(200.0);
        dto1.setPersonaId(1);
        dto1.setFecha(f1.getFecha());

        FacturaResponseDto dto2 = new FacturaResponseDto();
        dto2.setId(2);
        dto2.setMonto(300.0);
        dto2.setPersonaId(1);
        dto2.setFecha(f2.getFecha());

        when(personaRepository.findById(1)).thenReturn(Optional.of(persona));
        when(facturaRepository.findByPersonaId(1)).thenReturn(Arrays.asList(f1, f2));
        when(facturaMapper.toDto(f1)).thenReturn(dto1);
        when(facturaMapper.toDto(f2)).thenReturn(dto2);

        List<FacturaResponseDto> facturas = facturaService.findFacturasByPersona(1);

        assertEquals(2, facturas.size());
        assertEquals(200.0, facturas.get(0).getMonto());
        assertEquals(300.0, facturas.get(1).getMonto());
    }

    @Test
    void testStoreFacturaPersonaNoExiste() {
        when(personaRepository.findById(99)).thenReturn(Optional.empty());

        FacturaRequestDto dto = new FacturaRequestDto();
        dto.setIdPersona(99);
        dto.setMonto(150.0);
        dto.setFecha(LocalDate.now());

        NotFoundPersonasException ex = assertThrows(
                NotFoundPersonasException.class,
                () -> facturaService.storeFactura(99, dto)
        );

        assertEquals("No se encontró la persona con ID 99", ex.getMessage());
    }
}
