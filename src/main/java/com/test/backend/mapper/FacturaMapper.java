package com.test.backend.mapper;

import org.springframework.stereotype.Component;

import com.test.backend.domain.Factura;
import com.test.backend.dto.FacturaRequestDto;
import com.test.backend.dto.FacturaResponseDto;

@Component
public class FacturaMapper {

    public Factura toEntity(FacturaRequestDto dto) {
        if (dto == null) return null;

        Factura factura = new Factura();
        factura.setFecha(dto.getFecha());
        factura.setMonto(dto.getMonto());
        return factura;
    }

    public FacturaResponseDto toDto(Factura factura) {
        if (factura == null) return null;

        return FacturaResponseDto.builder()
                .id(factura.getId())
                .fecha(factura.getFecha())
                .monto(factura.getMonto())
                .personaId(factura.getPersona().getId())
                .build();
    }
}
