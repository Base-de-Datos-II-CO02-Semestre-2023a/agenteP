package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Ciudad;
import com.osba.agenteP.repository.CiudadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/ciudad")
public class CiudadController {
    @Autowired
    private CiudadRepository ciudadRepository;
    @CrossOrigin
    @GetMapping("/buscar/{query}")
    public List<Ciudad> buscarCiudad(@PathVariable String query){
        System.out.println(ciudadRepository.buscarCiudad(query));
        return ciudadRepository.buscarCiudad(query);
    }
}