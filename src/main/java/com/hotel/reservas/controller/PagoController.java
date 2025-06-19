package com.hotel.reservas.controller;

import com.hotel.reservas.model.Pago;
import com.hotel.reservas.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@CrossOrigin("*")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @PostMapping
    public Pago registrar(@RequestBody Pago pago) {
        return pagoService.registrarPago(pago);
    }

    @GetMapping
    public List<Pago> listar() {
        return pagoService.listar();
    }
}
