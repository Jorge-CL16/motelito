package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    private String puesto;
    private Boolean activo = true;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();
}
