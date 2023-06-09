package com.osba.agenteP.controller;

import com.osba.agenteP.model.BuscarLugar;
import com.osba.agenteP.model.BusquedaLugar;
import com.osba.agenteP.model.OptionLugar;
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
}
