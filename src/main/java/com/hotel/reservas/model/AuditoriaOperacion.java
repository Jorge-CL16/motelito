package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "auditoria_operaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaOperacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fecha = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        this.fecha = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private String operacion;

    @Column(columnDefinition = "TEXT")
    private String descripcion;
}
