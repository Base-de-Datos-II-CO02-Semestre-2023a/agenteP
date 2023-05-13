package com.osba.agenteP.controller;

import com.osba.agenteP.service.EmpleadoService;
import com.osba.agenteP.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping()
    public List<Empleado> empleado(){
        System.out.println("Hola");

        return empleadoService.getEmpleados();
    }

}
