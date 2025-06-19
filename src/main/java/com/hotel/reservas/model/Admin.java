package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String password;

    private String email;
    private Boolean activo = true;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();
}

