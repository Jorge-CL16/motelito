package com.hotel.reservas.service;

import com.hotel.reservas.model.Reserva;

import java.util.List;

public interface ReservaService {
    List<Reserva> listarTodas();

    Reserva obtenerPorId(Long id);

    Reserva crear(Reserva reserva);

    void eliminar(Long id);

    void cancelarReserva(Long id);
}

