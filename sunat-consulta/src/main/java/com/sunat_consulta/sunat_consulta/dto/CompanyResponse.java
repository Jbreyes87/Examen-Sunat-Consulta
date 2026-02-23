package com.sunat_consulta.sunat_consulta.dto;

import com.sunat_consulta.sunat_consulta.enums.CondicionDomicilio;
import com.sunat_consulta.sunat_consulta.enums.EstadoContribuyente;

import java.util.List;

public record CompanyResponse(
        Long id,
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
        List<ConsultaResponse> consultas
) {
}
