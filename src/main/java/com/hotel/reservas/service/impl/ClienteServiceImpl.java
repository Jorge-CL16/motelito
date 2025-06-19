package com.hotel.reservas.service.impl;

import com.hotel.reservas.model.Cliente;
import com.hotel.reservas.repository.ClienteRepository;
import com.hotel.reservas.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente obtenerPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con ID: " + id));
    }

    @Override
    public Cliente crear(Cliente cliente) {
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("El email ya está registrado");
        }
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente actualizar(Long id, Cliente cliente) {
        Cliente existente = obtenerPorId(id);
        existente.setNombre(cliente.getNombre());
        existente.setApellido(cliente.getApellido());
        existente.setEmail(cliente.getEmail());
        existente.setTelefono(cliente.getTelefono());
        existente.setActivo(cliente.getActivo());
        return clienteRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
