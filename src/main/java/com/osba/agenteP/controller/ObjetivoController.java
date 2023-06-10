package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Objetivo;
import com.osba.agenteP.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/objetivo")
public class ObjetivoController {

    @Autowired
    private ObjetivoRepository objetivoRepository;

    @GetMapping("/objetivos")
    public List<Objetivo> getObjetivos(){
        return objetivoRepository.getObjetivo();
    }

    //CHECAR EN POSTMAN
    @GetMapping("/crearObjetivo")
    public Map<String, Boolean> cambiarLugar(@RequestBody Objetivo body) {
        objetivoRepository.crearObjetivo(body.getIdEmpleado(), body.getDescripcion(), body.getPorcentajeAvance(), body.getImpactoProductividad());
        return Collections.singletonMap("Creacion del objetivo", true);
    }



}
