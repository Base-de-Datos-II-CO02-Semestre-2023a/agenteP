package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Falta;
import com.osba.agenteP.model.*;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.service.EmpleadoService;
import com.osba.agenteP.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Map;

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
    public EmpleadoInfo getEmpleadoById(@PathVariable String idString){
        Integer id = Integer.parseInt(idString);
        return empleadoRepository.getEmpleadoInfo(id);
    }

    @GetMapping("/buscar/{query}")
    public List<EmpleadoEncontrado> buscarEmpleadosPorRFC(@PathVariable String query){
        return  empleadoRepository.findEmpleadosByRfcOrNombreOrCorreo(query);
    }

    @GetMapping("/vacaciones/contar")
    public Map<String, Integer> contarEmpleadosDeVacaciones(){
        return Collections.singletonMap("empleados",empleadoRepository.contarEmpleadosVacaciones());
    }
    @GetMapping("/vacaciones/contar/{id}")
    public Map<String, Integer> contarEmpleadosDeVacacionesLugar(@PathVariable Integer id){
        return Collections.singletonMap("empleados",empleadoRepository.contarEmpleadosVacaciones(id));
    }

    @GetMapping("/productividad/promedio")
    public Map<String,Double> getPromedioProductividad(){
        return Collections.singletonMap("productividad",empleadoRepository.getPromedioProductividad());
    }

    @PatchMapping("/despedir/{id}")
    public Map<String,Boolean> despedirEmpleado(@PathVariable Integer id){
        System.out.println(id);
        boolean isFired = empleadoService.despedirEmpleado(id);

        if (isFired) {
            return Collections.singletonMap("status", true);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Puede que el usuario ya no tenga contrato");
        }
    }

    @GetMapping("/productividad/{id}")
    public List<ProductividadMes> productividadMes(@PathVariable Integer id){
        return empleadoRepository.getHistorialProductividad(id);
    }

    @GetMapping("/productividad")
    public List<ProductividadMes> productividadMes(){
        String id = SecurityContextHolder.getContext().getAuthentication().getName();
        return empleadoRepository.getHistorialProductividad(Integer.parseInt(id));
    }

    @PostMapping("/falta")
    public Map<String,Boolean> falta (@RequestBody Falta falta){
        System.out.println(falta);
        empleadoRepository.registrarFalta(falta.getIdEmpleado(),falta.getTipo().toString(),falta.getFecha(),falta.getImpactoProductividad(),falta.getDescripcion());
        return Collections.singletonMap("Falta",true);

    }
}
