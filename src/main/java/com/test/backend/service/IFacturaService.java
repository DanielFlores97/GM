package com.test.backend.service;

import java.util.List;

import com.test.backend.dto.FacturaRequestDto;
import com.test.backend.dto.FacturaResponseDto;

public interface IFacturaService {

    List<FacturaResponseDto> findFacturasByPersona(Integer idPersona);

    FacturaResponseDto storeFactura(Integer idPersona, FacturaRequestDto dto);
}
