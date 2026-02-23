package com.sunat_consulta.sunat_consulta.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SunatRucResponse(

        @JsonProperty("razon_social")
        String razonSocial,

        @JsonProperty("numero_documento")
        String numeroDocumento,

        @JsonProperty("estado")
        String estado,

        @JsonProperty("condicion")
        String condicion,

        @JsonProperty("direccion")
        String direccion,

        @JsonProperty("ubigeo")
        String ubigeo,

        @JsonProperty("distrito")
        String distrito,

        @JsonProperty("provincia")
        String provincia,

        @JsonProperty("departamento")
        String departamento,

        @JsonProperty("es_agente_retencion")
        boolean esAgenteRetencion,

        @JsonProperty("es_buen_contribuyente")
        boolean esBuenContribuyente,

        @JsonProperty("locales_anexos")
        Object localesAnexos
) {
}
