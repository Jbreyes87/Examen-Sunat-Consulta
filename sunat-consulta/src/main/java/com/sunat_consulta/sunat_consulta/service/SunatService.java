package com.sunat_consulta.sunat_consulta.service;

import com.sunat_consulta.sunat_consulta.client.SunatApiClient;
import com.sunat_consulta.sunat_consulta.dto.CompanyResponse;
import com.sunat_consulta.sunat_consulta.dto.ConsultaResponse;
import com.sunat_consulta.sunat_consulta.dto.SunatRucResponse;
import com.sunat_consulta.sunat_consulta.entity.Company;
import com.sunat_consulta.sunat_consulta.entity.Consulta;
import com.sunat_consulta.sunat_consulta.exception.ProviderException;
import com.sunat_consulta.sunat_consulta.mapper.SunatMapper;
import com.sunat_consulta.sunat_consulta.repository.CompanyRepository;
import com.sunat_consulta.sunat_consulta.repository.ConsultaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SunatService {

    private final SunatApiClient sunatApiClient;
    private final CompanyRepository companyRepository;
    private final ConsultaRepository consultaRepository;
    private final SunatMapper sunatMapper;

    public SunatService(SunatApiClient sunatApiClient,
                        CompanyRepository companyRepository,
                        ConsultaRepository consultaRepository,
                        SunatMapper sunatMapper) {
        this.sunatApiClient = sunatApiClient;
        this.companyRepository = companyRepository;
        this.consultaRepository = consultaRepository;
        this.sunatMapper = sunatMapper;
    }

    @Transactional(noRollbackFor = ProviderException.class)
    public CompanyResponse consultarRuc(String ruc) {
        validateRuc(ruc);

        try {
            SunatRucResponse apiResponse = sunatApiClient.consultarRuc(ruc);

            Company company = companyRepository.findByRuc(ruc)
                    .map(existing -> sunatMapper.updateCompany(existing, apiResponse))
                    .orElseGet(() -> sunatMapper.toCompany(apiResponse));
            company = companyRepository.save(company);

            Consulta consulta = sunatMapper.toConsultaSuccess(apiResponse, company);
            consultaRepository.save(consulta);

            List<Consulta> historial = consultaRepository.findByRucOrderByCreatedAtDesc(ruc);
            return sunatMapper.toCompanyResponse(company, historial);

        } catch (ProviderException e) {
            Consulta errorConsulta = sunatMapper.toConsultaError(ruc, e.getStatusCode(), e.getMessage());
            consultaRepository.save(errorConsulta);
            throw e;
        }
    }

    public List<ConsultaResponse> getHistorial(String ruc) {
        validateRuc(ruc);
        return consultaRepository.findByRucOrderByCreatedAtDesc(ruc)
                .stream()
                .map(sunatMapper::toConsultaResponse)
                .toList();
    }

    private void validateRuc(String ruc) {
        if (!ruc.matches("\\d{11}")) {
            throw new IllegalArgumentException("RUC debe tener exactamente 11 dígitos");
        }
    }
}
