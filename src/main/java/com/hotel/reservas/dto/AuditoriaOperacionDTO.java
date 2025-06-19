package com.hotel.reservas.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaOperacionDTO {
    private Long id;
    private LocalDateTime fecha;
    private String operacion;
    private String descripcion;
    private String nombreCliente;   // puede ser null
    private String nombreEmpleado;  // puede ser null
}
