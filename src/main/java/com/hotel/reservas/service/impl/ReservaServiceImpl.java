package com.hotel.reservas.service.impl;

import com.hotel.reservas.service.AuditoriaService;
import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.repository.ReservaRepository;
import com.hotel.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private AuditoriaService auditoriaService;

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> listarTodas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva obtenerPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    @Override
    public Reserva crear(Reserva reserva) {
        // Calculamos total noches
        int noches = (int) (reserva.getFechaSalida().toEpochDay() - reserva.getFechaEntrada().toEpochDay());
        reserva.setTotalNoches(noches);

        // Calculamos total a pagar basado en habitaciones
        double total = reserva.getHabitacionesReservadas().stream()
                .mapToDouble(h -> h.getPrecioUnitario() * h.getCantidad())
                .sum();

        reserva.setTotalPagar(total);

        // Relación bidireccional
        reserva.getHabitacionesReservadas().forEach(h -> h.setReserva(reserva));

        auditoriaService.registrarOperacionCliente(
                reserva.getCliente().getId(),
                "CREAR_RESERVA",
                "Reserva del " + reserva.getFechaEntrada() + " al " + reserva.getFechaSalida() +
                        " para " + reserva.getTotalPersonas() + " personas. Total: $" + reserva.getTotalPagar()
        );

        return reservaRepository.save(reserva);

    }

    @Override
    public void eliminar(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada."));

        if ("CANCELADA".equalsIgnoreCase(reserva.getEstado())) {
            throw new RuntimeException("La reserva ya está cancelada.");
        }

        if ("PAGADA".equalsIgnoreCase(reserva.getEstado())) {
            throw new RuntimeException("No se puede cancelar una reserva ya pagada.");
        }

        reserva.setEstado("CANCELADA");
        reservaRepository.save(reserva);

        auditoriaService.registrarOperacionCliente(
                reserva.getCliente().getId(),
                "CANCELAR_RESERVA",
                "Reserva cancelada con ID: " + reserva.getId()
        );
    }


}
