package com.hotel.reservas.service;

import com.hotel.reservas.model.Habitacion;

import java.util.List;

import com.hotel.reservas.dto.DisponibilidadRequest;
import com.hotel.reservas.dto.HabitacionDisponibleResponse;
import java.time.LocalDate;

public interface HabitacionService {

    Habitacion crear(Habitacion h);

    Habitacion actualizar(Long id, Habitacion h);

    List<Habitacion> listar();

    Habitacion obtenerPorId(Long id);

    void eliminar(Long id);

    List<HabitacionDisponibleResponse> buscarDisponibles(DisponibilidadRequest request);

    HabitacionDisponibleResponse verificarDisponibilidad(Long habitacionId, LocalDate fechaEntrada, LocalDate fechaSalida);

}
