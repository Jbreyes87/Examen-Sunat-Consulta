package com.sunat_consulta.sunat_consulta.controller;

import com.sunat_consulta.sunat_consulta.dto.CompanyResponse;
import com.sunat_consulta.sunat_consulta.dto.ConsultaResponse;
import com.sunat_consulta.sunat_consulta.service.SunatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sunat")
public class SunatController {

    private final SunatService sunatService;

    public SunatController(SunatService sunatService) {
        this.sunatService = sunatService;
    }

    @GetMapping("/ruc/{ruc}")
    public ResponseEntity<CompanyResponse> consultarRuc(@PathVariable String ruc) {
        return ResponseEntity.ok(sunatService.consultarRuc(ruc));
    }

    @GetMapping("/ruc/{ruc}/consultas")
    public ResponseEntity<List<ConsultaResponse>> getHistorial(@PathVariable String ruc) {
        return ResponseEntity.ok(sunatService.getHistorial(ruc));
    }
}
