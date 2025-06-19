package com.hotel.reservas.service;

import com.hotel.reservas.model.Admin;

import java.util.List;

public interface AdminService {
    List<Admin> listarTodos();

    Admin obtenerPorId(Long id);

    Admin crear(Admin admin);

    Admin actualizar(Long id, Admin admin);

    void eliminar(Long id);
}
