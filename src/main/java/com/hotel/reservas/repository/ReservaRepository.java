package com.hotel.reservas.repository;

import com.hotel.reservas.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT r FROM Reserva r
        JOIN ReservaHabitacion rh ON r.id = rh.reserva.id
        WHERE rh.habitacion.id = :habitacionId
        AND r.estado <> :estadoCancelado
        AND (
            (r.fechaEntrada <= :fechaSalida AND r.fechaSalida >= :fechaEntrada)
        )
    """)
    List<Reserva> findReservasSolapadas(
            @Param("habitacionId") Long habitacionId,
            @Param("estadoCancelado") String estadoCancelado,
            @Param("fechaEntrada") LocalDate fechaEntrada,
            @Param("fechaSalida") LocalDate fechaSalida
    );

}

