package com.hotel.reservas.controller;

import com.hotel.reservas.model.Admin;
import com.hotel.reservas.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<Admin> listar() {
        return adminService.listarTodos();
    }

    @GetMapping("/{id}")
    public Admin obtener(@PathVariable Long id) {
        return adminService.obtenerPorId(id);
    }

    @PostMapping
    public Admin crear(@RequestBody Admin admin) {
        return adminService.crear(admin);
    }

    @PutMapping("/{id}")
    public Admin actualizar(@PathVariable Long id, @RequestBody Admin admin) {
        return adminService.actualizar(id, admin);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        adminService.eliminar(id);
    }
}
