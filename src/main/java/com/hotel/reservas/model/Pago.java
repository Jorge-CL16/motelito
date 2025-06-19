package com.hotel.reservas.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pago")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reserva_id", nullable = false, unique = true)
    private Reserva reserva;

    private LocalDateTime fechaPago;

    private String metodoPago;        // tarjeta, paypal, efectivo
    private String proveedorPago;     // Stripe, Paypal, N/A
    private Double montoPagado;
    private String estado;            // exitoso, fallido
    private String transaccionId;
}
