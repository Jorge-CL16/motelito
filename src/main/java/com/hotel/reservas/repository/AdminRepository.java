package com.hotel.reservas.repository;

import com.hotel.reservas.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsByUsuario(String usuario);
}
