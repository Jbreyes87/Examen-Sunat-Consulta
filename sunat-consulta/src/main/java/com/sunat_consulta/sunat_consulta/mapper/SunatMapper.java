package com.sunat_consulta.sunat_consulta.mapper;

import com.sunat_consulta.sunat_consulta.dto.CompanyResponse;
import com.sunat_consulta.sunat_consulta.dto.ConsultaResponse;
import com.sunat_consulta.sunat_consulta.dto.SunatRucResponse;
import com.sunat_consulta.sunat_consulta.entity.Company;
import com.sunat_consulta.sunat_consulta.entity.Consulta;
import com.sunat_consulta.sunat_consulta.enums.CondicionDomicilio;
import com.sunat_consulta.sunat_consulta.enums.EstadoContribuyente;
import com.sunat_consulta.sunat_consulta.enums.ResultadoConsulta;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SunatMapper {

    public Company toCompany(SunatRucResponse r) {
        Company company = new Company();
        company.setRuc(r.numeroDocumento());
        company.setRazonSocial(r.razonSocial());
        company.setEstado(EstadoContribuyente.valueOf(r.estado()));
        company.setCondicion(CondicionDomicilio.valueOf(r.condicion()));
        company.setDireccion(r.direccion());
        company.setUbigeo(r.ubigeo());
        company.setDepartamento(r.departamento());
        company.setProvincia(r.provincia());
        company.setDistrito(r.distrito());
        company.setEsAgenteRetencion(r.esAgenteRetencion());
        company.setEsBuenContribuyente(r.esBuenContribuyente());
        return company;
    }

    public Company updateCompany(Company existing, SunatRucResponse r) {
        existing.setRazonSocial(r.razonSocial());
        existing.setEstado(EstadoContribuyente.valueOf(r.estado()));
        existing.setCondicion(CondicionDomicilio.valueOf(r.condicion()));
        existing.setDireccion(r.direccion());
        existing.setUbigeo(r.ubigeo());
        existing.setDepartamento(r.departamento());
        existing.setProvincia(r.provincia());
        existing.setDistrito(r.distrito());
        existing.setEsAgenteRetencion(r.esAgenteRetencion());
        existing.setEsBuenContribuyente(r.esBuenContribuyente());
        return existing;
    }

    public Consulta toConsultaSuccess(SunatRucResponse r, Company company) {
        Consulta consulta = new Consulta();
        consulta.setRuc(r.numeroDocumento());
        consulta.setResultado(ResultadoConsulta.SUCCESS);
        consulta.setRazonSocial(r.razonSocial());
        consulta.setEstado(EstadoContribuyente.valueOf(r.estado()));
        consulta.setCondicion(CondicionDomicilio.valueOf(r.condicion()));
        consulta.setDireccion(r.direccion());
        consulta.setUbigeo(r.ubigeo());
        consulta.setDepartamento(r.departamento());
        consulta.setProvincia(r.provincia());
        consulta.setDistrito(r.distrito());
        consulta.setEsAgenteRetencion(r.esAgenteRetencion());
        consulta.setEsBuenContribuyente(r.esBuenContribuyente());
        consulta.setCompany(company);
        return consulta;
    }

    public Consulta toConsultaError(String ruc, int statusCode, String mensajeError) {
        Consulta consulta = new Consulta();
        consulta.setRuc(ruc);
        consulta.setResultado(ResultadoConsulta.ERROR);
        consulta.setProviderStatusCode(statusCode);
        consulta.setMensajeError(mensajeError);
        return consulta;
    }

    public ConsultaResponse toConsultaResponse(Consulta consulta) {
        return new ConsultaResponse(
                consulta.getId(),
                consulta.getRuc(),
                consulta.getResultado(),
                consulta.getProviderStatusCode(),
                consulta.getMensajeError(),
                consulta.getCreatedAt()
        );
    }

    public CompanyResponse toCompanyResponse(Company company, List<Consulta> consultas) {
        return new CompanyResponse(
                company.getId(),
                company.getRuc(),
                company.getRazonSocial(),
                company.getEstado(),
                company.getCondicion(),
                company.getDireccion(),
                company.getUbigeo(),
                company.getDepartamento(),
                company.getProvincia(),
                company.getDistrito(),
                company.isEsAgenteRetencion(),
                company.isEsBuenContribuyente(),
                consultas.stream().map(this::toConsultaResponse).toList()
        );
    }
}
