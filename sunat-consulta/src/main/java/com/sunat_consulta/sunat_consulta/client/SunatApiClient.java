package com.sunat_consulta.sunat_consulta.client;

import com.sunat_consulta.sunat_consulta.config.SunatFeignConfig;
import com.sunat_consulta.sunat_consulta.dto.SunatRucResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "sunat-api",
        url = "${decolecta.api.base-url}",
        configuration = SunatFeignConfig.class
)
public interface SunatApiClient {

    @GetMapping("/sunat/ruc")
    SunatRucResponse consultarRuc(@RequestParam("numero") String ruc);
}
