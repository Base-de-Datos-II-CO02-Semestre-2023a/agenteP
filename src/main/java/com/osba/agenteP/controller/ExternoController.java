package com.osba.agenteP.controller;


import com.osba.agenteP.model.ExternoInfo;
import com.osba.agenteP.repository.ExternoRepository;
import com.osba.agenteP.repository.ReabastecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/externo")
public class ExternoController {

    @Autowired
    private ExternoRepository externoRepository;

    @GetMapping("/datosProveedor/{id}")
    public ExternoInfo datosProveedor(@PathVariable Integer id){
       return externoRepository.getExternoInfo(id);
    }

    @GetMapping("/ExistenciaProveedor/{id}")
    public Boolean ExistenciaProveedor(@PathVariable Integer id){
        return externoRepository.provedorExistente(id);
    }

}
