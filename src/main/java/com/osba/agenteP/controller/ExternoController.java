package com.osba.agenteP.controller;


import com.osba.agenteP.domain.Externo;
import com.osba.agenteP.model.ExternoInfo;
import com.osba.agenteP.repository.ExternoRepository;
import com.osba.agenteP.repository.ReabastecimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/externo")
public class ExternoController {

    @Autowired
    private ExternoRepository externoRepository;

    //CHECAR EN POSTMAN
    @GetMapping("/datosProveedor/{rfc}")
    public ExternoInfo datosProveedor(@PathVariable String rfc){
       return externoRepository.getExternoInfo(rfc);
    }

    //CHECAR EN POSTMAN
    @GetMapping("/existenciaProveedor/{rfc}")
    public Boolean ExistenciaProveedor(@PathVariable String rfc){
        return externoRepository.provedorExistente(rfc);
    }

    //CHECAR POSTMAN
    @GetMapping("/ingresoExterno")
    public Boolean IngresoExterno (@RequestBody Externo body) {

        return externoRepository.insertarExterno(body.getId(),body.getNombre(),body.getTelefono(), body.getCorreo(), body.getCodigoPostal(),body.getIdCiudad(),body.getCalle(), body.getNumeroInterno(), body.getNumeroExterno(),body.getRfc(),body.getRegimenFiscal(),body.getTipo());

    }

}
