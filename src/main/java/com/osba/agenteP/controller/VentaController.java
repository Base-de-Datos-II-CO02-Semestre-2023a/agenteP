package com.osba.agenteP.controller;

import com.osba.agenteP.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/venta")
public class VentaController {
    @Autowired
    private VentaRepository ventaRepository;

    @GetMapping("/ventasTotales")
    public Integer ventasTotales(){
        return ventaRepository.ventasTotales();
    }
}
