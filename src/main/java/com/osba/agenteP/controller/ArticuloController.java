package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Articulo;
import com.osba.agenteP.service.ArticuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/articulos")
public class ArticuloController {
    @Autowired
    private ArticuloService articuloService;
    @GetMapping()
    public List<Articulo>articulo(){
        System.out.println("siuuu");

        return articuloService.getArticulos();
    }
}
