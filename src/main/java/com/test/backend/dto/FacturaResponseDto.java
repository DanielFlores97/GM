package com.test.backend.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacturaResponseDto {

    private Integer id;
    private LocalDate fecha;
    private Double monto;
    private Integer personaId;
}
