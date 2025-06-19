package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
@Entity
@Table(name = "habitacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo; // Ej: Simple, Doble, Suite

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "precio_noche", nullable = false)
    private BigDecimal precioNoche;

    @Column(name = "cantidad_total", nullable = false)
    private Integer cantidadTotal;

    private Boolean activo = true;

    private String nombre;

    // No es necesario definir getters/setters manuales por @Getter y @Setter
}
