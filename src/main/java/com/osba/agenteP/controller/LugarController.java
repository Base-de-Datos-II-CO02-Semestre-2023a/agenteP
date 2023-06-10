package com.osba.agenteP.controller;

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
    @GetMapping("/buscar/{query}")
    private List<OptionLugar> suggestLugar(@PathVariable String query) {
        return lugarRepository.lugarSuggestions(query);
    }

    @GetMapping()
    public List<String> nombres(){
        return lugarRepository.nombres();
    }
}
