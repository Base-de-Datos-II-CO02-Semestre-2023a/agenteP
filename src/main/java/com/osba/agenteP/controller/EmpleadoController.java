package com.osba.agenteP.controller;

import com.osba.agenteP.model.EmpleadoProductivo;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.service.EmpleadoService;
import com.osba.agenteP.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping()
    public List<Empleado> empleado(){
        System.out.println("Hola");

        return empleadoService.getEmpleados();
    }


    @GetMapping("/userdata")
    private Empleado getUserData(){
        String rfc = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(rfc);
        return empleadoService.getEmpleado(rfc).get();
    }
    @GetMapping("/productivos")
    public List<EmpleadoProductivo> getProductivos(){
        return empleadoRepository.obtenerMásProductivos();
    }
    @GetMapping("/inproductivos")
    public List<EmpleadoProductivo> getInProductivos(){
        return empleadoRepository.obtenerMenosProductivos();
    }

    @GetMapping("/{rfc}")
    public Empleado getEmpleadoByRfc(@PathVariable String rfc){

        return empleadoRepository.findByRfc(rfc).get();
    }
}
