package com.hotel.reservas.service.impl;

import com.hotel.reservas.model.Empleado;
import com.hotel.reservas.repository.EmpleadoRepository;
import com.hotel.reservas.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarTodos() {
        return empleadoRepository.findAll();
    }

    @Override
    public Empleado obtenerPorId(Long id) {
        return empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado con ID: " + id));
    }

    @Override
    public Empleado crear(Empleado empleado) {
        if (empleadoRepository.existsByEmail(empleado.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return empleadoRepository.save(empleado);
    }

    @Override
    public Empleado actualizar(Long id, Empleado empleado) {
        Empleado existente = obtenerPorId(id);
        existente.setNombre(empleado.getNombre());
        existente.setApellido(empleado.getApellido());
        existente.setEmail(empleado.getEmail());
        existente.setPuesto(empleado.getPuesto());
        existente.setActivo(empleado.getActivo());
        return empleadoRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        empleadoRepository.deleteById(id);
    }
}
