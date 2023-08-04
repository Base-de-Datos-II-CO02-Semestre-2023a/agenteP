package com.osba.agenteP.controller;

import com.osba.agenteP.model.*;
import com.osba.agenteP.repository.LugarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lugar")
public class LugarController {
    @Autowired
    private LugarRepository lugarRepository;
    @CrossOrigin
    @GetMapping("/suggest/{query}")
    private List<OptionLugar> suggestLugar(@PathVariable String query) {
        return lugarRepository.lugarSuggestions(query);
    }

    @GetMapping("buscar/rh/{query}")
    private List<BuscarLugar> searchRH(@PathVariable String query){
        return lugarRepository.searchRH(query);
    }
    @GetMapping("buscar/rh")
    private List<BuscarLugar> searchAllRH(){
        return lugarRepository.searchAllRh();
    }

    @GetMapping("/rh/{id}")
    private InfoLugar getInfoLugarRh(@PathVariable Integer id){
        return lugarRepository.getInforamtion(id);
    }
    @GetMapping("/empleados/{lugar}")
    private List<EmpleadoEncontrado> empleadosLugar(@PathVariable Integer lugar){
        return  lugarRepository.empleados(lugar);
    }
}
