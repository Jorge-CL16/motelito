package com.hotel.reservas.controller;

import com.hotel.reservas.dto.AuditoriaOperacionDTO;
import com.hotel.reservas.service.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auditoria")
@CrossOrigin("*")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @GetMapping
    public List<AuditoriaOperacionDTO> listar() {
        return auditoriaService.listarTodas();
    }
}
