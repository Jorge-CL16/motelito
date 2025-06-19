package com.hotel.reservas.controller;

import java.time.LocalDate;
import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;
import com.hotel.reservas.dto.HabitacionDisponibleResponse;
import org.springframework.format.annotation.DateTimeFormat; // <- Import faltante

@RestController
@RequestMapping("/api/habitaciones")
@CrossOrigin(origins = "*")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @PostMapping
    public ResponseEntity<Habitacion> crear(@RequestBody Habitacion h) {
        return ResponseEntity.ok(habitacionService.crear(h));
    }

    @GetMapping
    public ResponseEntity<List<Habitacion>> listar() {
        return ResponseEntity.ok(habitacionService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(habitacionService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habitacion> actualizar(@PathVariable Long id, @RequestBody Habitacion h) {
        return ResponseEntity.ok(habitacionService.actualizar(id, h));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {
        habitacionService.eliminar(id);
        return ResponseEntity.ok("Habitación eliminada (desactivada) correctamente.");
    }

    @GetMapping("/disponibilidad/{id}")
    public ResponseEntity<HabitacionDisponibleResponse> verificarDisponibilidad(
            @PathVariable Long id,
            @RequestParam("entrada") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
            @RequestParam("salida") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate salida) {

        HabitacionDisponibleResponse response = habitacionService.verificarDisponibilidad(id, entrada, salida);
        return ResponseEntity.ok(response);
    }
}