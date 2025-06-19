package com.hotel.reservas.service;

import com.hotel.reservas.dto.AuditoriaOperacionDTO;
import java.util.List;

public interface AuditoriaService {

    void registrarOperacionCliente(Long clienteId, String operacion, String descripcion);
    void registrarOperacionEmpleado(Long empleadoId, String operacion, String descripcion);
    List<AuditoriaOperacionDTO> listarTodas();

}
