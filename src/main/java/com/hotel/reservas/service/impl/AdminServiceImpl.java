package com.hotel.reservas.service.impl;

import com.hotel.reservas.model.Admin;
import com.hotel.reservas.repository.AdminRepository;
import com.hotel.reservas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public List<Admin> listarTodos() {
        return adminRepository.findAll();
    }

    @Override
    public Admin obtenerPorId(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Admin no encontrado con ID: " + id));
    }

    @Override
    public Admin crear(Admin admin) {
        if (adminRepository.existsByUsuario(admin.getUsuario())) {
            throw new RuntimeException("El usuario ya está registrado");
        }
        return adminRepository.save(admin);
    }

    @Override
    public Admin actualizar(Long id, Admin admin) {
        Admin existente = obtenerPorId(id);
        existente.setUsuario(admin.getUsuario());
        existente.setPassword(admin.getPassword());
        existente.setEmail(admin.getEmail());
        existente.setActivo(admin.getActivo());
        return adminRepository.save(existente);
    }

    @Override
    public void eliminar(Long id) {
        adminRepository.deleteById(id);
    }
}
