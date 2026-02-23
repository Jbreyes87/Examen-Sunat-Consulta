package com.sunat_consulta.sunat_consulta.dto;

import com.sunat_consulta.sunat_consulta.enums.ResultadoConsulta;

import java.time.LocalDateTime;

public record ConsultaResponse(
        Long id,
        String ruc,
        ResultadoConsulta resultado,
        Integer providerStatusCode,
        String mensajeError,
        LocalDateTime createdAt
) {
}
