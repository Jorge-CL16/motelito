package com.hotel.reservas.service;

import com.hotel.reservas.model.Empleado;

import java.util.List;

public interface EmpleadoService {
    List<Empleado> listarTodos();

    Empleado obtenerPorId(Long id);

    Empleado crear(Empleado empleado);

    Empleado actualizar(Long id, Empleado empleado);

    void eliminar(Long id);
}
