package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Inventario;
import com.osba.agenteP.model.InventarioLugar;
import com.osba.agenteP.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/porLugar/{nombre}")
    public List<InventarioLugar> inventarioLugar(@PathVariable String nombre){
        return inventarioRepository.inventarioLugar(nombre);
    }

    @GetMapping("/articulosDistintos")
    public Map<String,Integer> articulosDistintos(){
        return Collections.singletonMap("articulos distintos",inventarioRepository.articulosDistintos());

    }

    @GetMapping("/sumaArticulos/{id_lugar}")
    public Map<String,Integer> sumaArticulos(@PathVariable Integer id_lugar){
        return Collections.singletonMap("Articulos totales",inventarioRepository.sumaArticulos(id_lugar));

    }
}
