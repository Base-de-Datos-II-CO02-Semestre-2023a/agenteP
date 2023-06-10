package com.osba.agenteP.controller;


import com.osba.agenteP.domain.Reabastecimiento;
import com.osba.agenteP.model.CambioLugar;
import com.osba.agenteP.model.EmpleadoInfo;
import com.osba.agenteP.repository.ReabastecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/reabastecimiento")
public class ReabastecimientoController {

    @Autowired
    private ReabastecimientoRepository reabastecimientoRepository;

    //CHECAR EN POSTMAN
    @GetMapping("/crearReabastecimiento")
    public Map<String,Boolean> crearReabastecimiento (@RequestBody Reabastecimiento body){

        reabastecimientoRepository.crearReabastecimiento(body.getId(),body.getId_lugar(),body.getId_provedor(),body.getTotal_compra(),body.getFecha());
        return Collections.singletonMap("Creacion de reabastecimiento",true);
    }


}
