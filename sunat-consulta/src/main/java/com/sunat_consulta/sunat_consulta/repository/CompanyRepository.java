package com.sunat_consulta.sunat_consulta.repository;

import com.sunat_consulta.sunat_consulta.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByRuc(String ruc);

    boolean existsByRuc(String ruc);
}
