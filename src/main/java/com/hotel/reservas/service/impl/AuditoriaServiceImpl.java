package com.hotel.reservas.service.impl;

import com.hotel.reservas.dto.AuditoriaOperacionDTO;
import com.hotel.reservas.model.*;
import com.hotel.reservas.repository.*;
import com.hotel.reservas.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AuditoriaServiceImpl implements AuditoriaService {

    @Autowired
    private AuditoriaOperacionRepository auditoriaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public void registrarOperacionCliente(Long clienteId, String operacion, String descripcion) {
        Cliente cliente = clienteRepository.findById(clienteId).orElse(null);

        AuditoriaOperacion auditoria = AuditoriaOperacion.builder()
                .cliente(cliente)
                .operacion(operacion)
                .descripcion(descripcion)
                .build();

        auditoriaRepository.save(auditoria);
    }

    @Override
    public void registrarOperacionEmpleado(Long empleadoId, String operacion, String descripcion) {
        Empleado empleado = empleadoRepository.findById(empleadoId).orElse(null);

        AuditoriaOperacion auditoria = AuditoriaOperacion.builder()
                .empleado(empleado)
                .operacion(operacion)
                .descripcion(descripcion)
                .build();

        auditoriaRepository.save(auditoria);
    }

    @Override
    public List<AuditoriaOperacionDTO> listarTodas() {
        List<AuditoriaOperacion> auditorias = auditoriaRepository.findAll();

        return auditorias.stream().map(a -> AuditoriaOperacionDTO.builder()
                .id(a.getId())
                .fecha(a.getFecha())
                .operacion(a.getOperacion())
                .descripcion(a.getDescripcion())
                .nombreCliente(a.getCliente() != null
                        ? a.getCliente().getNombre() + " " + a.getCliente().getApellido()
                        : null)
                .nombreEmpleado(a.getEmpleado() != null
                        ? a.getEmpleado().getNombre() + " " + a.getEmpleado().getApellido()
                        : null)
                .build()
        ).toList();
    }
}
