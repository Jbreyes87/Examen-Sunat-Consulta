package com.sunat_consulta.sunat_consulta.entity;

import com.sunat_consulta.sunat_consulta.enums.CondicionDomicilio;
import com.sunat_consulta.sunat_consulta.enums.EstadoContribuyente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(unique = true, nullable = false, length = 11)
    private String ruc;

    @Column(nullable = false)
    private String razonSocial;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoContribuyente estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
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

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Consulta> consultas;

    @Setter(lombok.AccessLevel.NONE)
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = LocalDateTime.now();
    }
}
