package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Inventario;
import com.osba.agenteP.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private InventarioRepository inventarioRepository;

    @GetMapping("/productosCaducar")
    public Map<String,Integer> productosACaducar(){
        return Collections.singletonMap("proximos a caducar",inventarioRepository.productosCaducar());
    }
    @GetMapping("/reabasteciemtoRecomendado")
    public Map<String,Integer> reabastecimientoRecommended(){
        return Collections.singletonMap("reabastecimiento recomendado",inventarioRepository.reabasteciemtoRecommended());
    }

    @GetMapping("/productos")
    public List<Inventario> cosasEnInventario(){
        return inventarioRepository.cosasEnInventario();
    }
}
