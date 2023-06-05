package com.osba.agenteP.controller;

import com.osba.agenteP.model.EmpleadoEncontrado;
import com.osba.agenteP.model.EmpleadoProductivo;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.service.EmpleadoService;
import com.osba.agenteP.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/empleados")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @GetMapping()
    public List<EmpleadoEncontrado> empleado(){

        return empleadoRepository.findEmpleadosByRfcOrNombreOrCorreo("");
    }


    @GetMapping("/userdata")
    private Empleado getUserData(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            return empleadoService.getEmpleado(Integer.parseInt(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No se encontró la información", e);
        }
    }
    @GetMapping("/productivos")
    public List<EmpleadoProductivo> getProductivos(){
        return empleadoRepository.obtenerMásProductivos();
    }
    @GetMapping("/inproductivos")
    public List<EmpleadoProductivo> getInProductivos(){
        return empleadoRepository.obtenerMenosProductivos();
    }

    @GetMapping("/{idString}")
    public Empleado getEmpleadoById(@PathVariable String idString){
        Integer id = Integer.parseInt(idString);
        return empleadoRepository.findEmpleadoById(id).get();
    }

    @GetMapping("/buscar/{query}")
    public List<EmpleadoEncontrado> buscarEmpleadosPorRFC(@PathVariable String query){
        return  empleadoRepository.findEmpleadosByRfcOrNombreOrCorreo(query);
    }
}
