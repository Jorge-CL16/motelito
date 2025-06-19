package com.hotel.reservas.repository;

import com.hotel.reservas.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Long> {
    boolean existsByReservaId(Long reservaId);
}
