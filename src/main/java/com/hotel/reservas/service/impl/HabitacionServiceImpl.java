package com.hotel.reservas.service.impl;

import com.hotel.reservas.model.Habitacion;
import com.hotel.reservas.repository.HabitacionRepository;
import com.hotel.reservas.service.HabitacionService;
import com.hotel.reservas.dto.HabitacionDisponibleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

import com.hotel.reservas.model.Reserva;
import com.hotel.reservas.repository.ReservaRepository;
import com.hotel.reservas.dto.DisponibilidadRequest;

@Service
public class HabitacionServiceImpl implements HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    @Autowired
    private ReservaRepository reservaRepository;


    @Override
    public Habitacion crear(Habitacion h) {
        return habitacionRepository.save(h);
    }

    @Override
    public Habitacion actualizar(Long id, Habitacion h) {
        Habitacion existente = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        existente.setTipo(h.getTipo());
        existente.setDescripcion(h.getDescripcion());
        existente.setPrecioNoche(h.getPrecioNoche());
        existente.setCantidadTotal(h.getCantidadTotal());
        existente.setActivo(h.getActivo());

        return habitacionRepository.save(existente);
    }

    @Override
    public List<Habitacion> listar() {
        return habitacionRepository.findByActivoTrue();
    }

    @Override
    public Habitacion obtenerPorId(Long id) {
        return habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        Habitacion h = habitacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));
        h.setActivo(false);
        habitacionRepository.save(h);
    }

    @Override
    public List<HabitacionDisponibleResponse> buscarDisponibles(DisponibilidadRequest request) {
        LocalDate entrada = request.getFechaEntrada();
        LocalDate salida = request.getFechaSalida();

        List<Habitacion> habitaciones = habitacionRepository.findByActivoTrue();

        return habitaciones.stream().map(hab -> {
                    List<Reserva> reservasSolapadas = reservaRepository
                            .findReservasSolapadas(hab.getId(), "CANCELADA", entrada, salida);

                    int cantidadReservada = reservasSolapadas.size();
                    int disponible = hab.getCantidadTotal() - cantidadReservada;

                    if (disponible > 0) {
                        HabitacionDisponibleResponse dto = new HabitacionDisponibleResponse();
                        dto.setId(hab.getId());
                        dto.setNombre(hab.getNombre());
                        dto.setDescripcion(hab.getDescripcion());
                        dto.setPrecioNoche(hab.getPrecioNoche());
                        dto.setCantidadDisponible(disponible);
                        return dto;
                    }

                    return null;
                })
                .filter(h -> h != null)
                .collect(Collectors.toList());
    }

    @Override
    public HabitacionDisponibleResponse verificarDisponibilidad(Long habitacionId, LocalDate fechaEntrada, LocalDate fechaSalida) {
        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new RuntimeException("Habitación no encontrada"));

        List<Reserva> reservas = reservaRepository.findReservasSolapadas(habitacionId, "CANCELADA", fechaEntrada, fechaSalida);

        int cantidadReservada = reservas.size();
        int disponible = habitacion.getCantidadTotal() - cantidadReservada;

        if (disponible > 0) {
            HabitacionDisponibleResponse dto = new HabitacionDisponibleResponse();
            dto.setId(habitacion.getId());
            dto.setNombre(habitacion.getNombre());
            dto.setDescripcion(habitacion.getDescripcion());
            dto.setPrecioNoche(habitacion.getPrecioNoche());
            dto.setCantidadDisponible(disponible);
            return dto;
        }

        // Retorna null o lanza excepción si no hay disponibilidad
        return null;
    }
}


