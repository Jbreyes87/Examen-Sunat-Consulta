package com.sunat_consulta.sunat_consulta.entity;

import com.sunat_consulta.sunat_consulta.enums.CondicionDomicilio;
import com.sunat_consulta.sunat_consulta.enums.EstadoContribuyente;
import com.sunat_consulta.sunat_consulta.enums.ResultadoConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consultas")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 11)
    private String ruc;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResultadoConsulta resultado;

    @Column
    private Integer providerStatusCode;

    @Column
    private String mensajeError;

    @Column
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    @Column
    private EstadoContribuyente estado;

    @Enumerated(EnumType.STRING)
    @Column
    private CondicionDomicilio condicion;

    @Column
    private String direccion;

    @Column(length = 6)
    private String ubigeo;

    @Column
    private String departamento;

    @Column
    private String provincia;

    @Column
    private String distrito;

    @Column(nullable = false)
    private boolean esAgenteRetencion;

    @Column(nullable = false)
    private boolean esBuenContribuyente;

    @ManyToOne(optional = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = true)
    private Company company;

    @Setter(lombok.AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
