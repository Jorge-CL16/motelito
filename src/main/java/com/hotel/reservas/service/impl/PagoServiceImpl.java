package com.hotel.reservas.service.impl;

import com.hotel.reservas.model.*;
import com.hotel.reservas.repository.*;
import com.hotel.reservas.service.AuditoriaService;
import com.hotel.reservas.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private AuditoriaService auditoriaService;

    @Override
    public Pago registrarPago(Pago pago) {
        Reserva reserva = reservaRepository.findById(pago.getReserva().getId())
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        if (pagoRepository.existsByReservaId(reserva.getId())) {
            throw new RuntimeException("Ya se ha registrado un pago para esta reserva.");
        }

        if ("CANCELADA".equalsIgnoreCase(reserva.getEstado())) {
            throw new RuntimeException("No se puede pagar una reserva cancelada.");
        }

        if ("PAGADA".equalsIgnoreCase(reserva.getEstado())) {
            throw new RuntimeException("La reserva ya ha sido pagada.");
        }


        reserva.setEstado("PAGADA");
        reservaRepository.save(reserva); // actualiza el estado

        pago.setReserva(reserva);
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstado("exitoso");

        Pago nuevo = pagoRepository.save(pago);


        // Auditoría
        auditoriaService.registrarOperacionCliente(
                reserva.getCliente().getId(),
                "PAGO_RESERVA",
                "Pago de $" + pago.getMontoPagado() + " para reserva ID: " + reserva.getId()
        );

        return nuevo;
    }

    @Override
    public List<Pago> listar() {
        return pagoRepository.findAll();
    }
}
