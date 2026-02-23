package com.sunat_consulta.sunat_consulta.repository;

import com.sunat_consulta.sunat_consulta.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findByRucOrderByCreatedAtDesc(String ruc);

    List<Consulta> findByCompanyId(Long companyId);

    boolean existsByRuc(String ruc);
}
