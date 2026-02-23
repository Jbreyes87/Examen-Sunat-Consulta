package com.sunat_consulta.sunat_consulta.dto;

import com.sunat_consulta.sunat_consulta.enums.CondicionDomicilio;
import com.sunat_consulta.sunat_consulta.enums.EstadoContribuyente;

import java.time.LocalDateTime;

public record CompanyConsultaResponse(

        Long companyId,
        Long consultaId,
        String ruc,
        String razonSocial,
        EstadoContribuyente estado,
        CondicionDomicilio condicion,
        String direccion,
        String ubigeo,
        String departamento,
        String provincia,
        String distrito,
        boolean esAgenteRetencion,
        boolean esBuenContribuyente,
        LocalDateTime createdAt
) {
}
