package com.osba.agenteP.controller;

import com.osba.agenteP.model.ArticulosVendidos;
import com.osba.agenteP.repository.ConceptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/concepto")
public class ConceptoController {

    @Autowired
    private ConceptoRepository conceptoRepository;

    @GetMapping("/articulosMasVendidos")
    public List<ArticulosVendidos> articulosMasVendidos(){
        return conceptoRepository.articulosMasVendidos();
    }
    @GetMapping("/articulosMenosVendidos")
    public List<ArticulosVendidos> articulosMenosVendidos(){
        return conceptoRepository.articulosMenosVendidos();
    }
}
