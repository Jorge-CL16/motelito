package com.hotel.reservas.dto;

import java.time.LocalDate;

public class DisponibilidadRequest {
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;

    // Getters y Setters
    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
}
