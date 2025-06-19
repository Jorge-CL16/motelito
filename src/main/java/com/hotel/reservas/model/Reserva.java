package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reserva")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(name = "fecha_entrada")
    private LocalDate fechaEntrada;

    @Column(name = "fecha_salida")
    private LocalDate fechaSalida;

    @Column(name = "total_personas")
    private int totalPersonas;

    @Column(name = "total_noches")
    private int totalNoches;

    @Column(name = "total_pagar")
    private double totalPagar;

    private String estado = "PENDIENTE";

    @Column(name = "creado_en")
    private LocalDateTime creadoEn = LocalDateTime.now();

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL)
    private List<ReservaHabitacion> habitacionesReservadas;
}
