package com.hotel.reservas.controller;


import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> listar() {
        return reservaService.listarTodas();
    }

    @GetMapping("/{id}")
    public Reserva obtener(@PathVariable Long id) {
        return reservaService.obtenerPorId(id);
    }

    @PostMapping
    public Reserva crear(@RequestBody Reserva reserva) {
        return reservaService.crear(reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        reservaService.eliminar(id);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<String> cancelar(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.ok("Reserva cancelada correctamente.");
    }

}

