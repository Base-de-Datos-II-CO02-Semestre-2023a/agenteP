package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Objetivo;
import com.osba.agenteP.repository.ObjetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
