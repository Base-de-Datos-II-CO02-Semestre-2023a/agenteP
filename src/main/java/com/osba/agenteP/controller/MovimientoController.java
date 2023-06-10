package com.osba.agenteP.controller;

import com.osba.agenteP.repository.LugarRepository;
import com.osba.agenteP.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoRepository movimientoRepository;





}
