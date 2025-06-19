package com.hotel.reservas.service;

import com.hotel.reservas.model.Pago;

import java.util.List;

public interface PagoService {
    Pago registrarPago(Pago pago);
    List<Pago> listar();
}
