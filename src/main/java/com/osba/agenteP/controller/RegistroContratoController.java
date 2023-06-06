package com.osba.agenteP.controller;

import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.model.IdContrato;
import com.osba.agenteP.model.RfcEmpleado;
import com.osba.agenteP.repository.RegistroContratosRepository;
import com.osba.agenteP.service.RegistroContratosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

//TODO Manejar los errores
@RestController
@RequestMapping("/contrato")
public class RegistroContratoController {

    @Autowired
    private RegistroContratosRepository registroContratosRepository;
    @Autowired
    private RegistroContratosService registroContratosService;
    @GetMapping()
    public RegistroContratos contrato(){
        String idString = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer id = Integer.parseInt(idString);
        return registroContratosService.getContrato(id).get();
    }
    @PostMapping()
    public Map<String, Integer> setContrato(@RequestBody RegistroContratos newContrato){
        RegistroContratos contrato = registroContratosRepository.save(newContrato);
        return Collections.singletonMap("contrato", contrato.getId());
    }
    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> popContrato(@RequestBody IdContrato body){
        HashMap<String, Object> data = new HashMap<>();
        Integer idContrato = body.getIdContrato();
        try{
            registroContratosRepository.findById(idContrato).orElseThrow();
            registroContratosRepository.deleteById(idContrato);
            data.put("mensaje","Se elimino el contrato: "+idContrato);

            return ResponseEntity.ok(data);
        }catch (Error e){
            data.put("error", "Error interno");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);

        }catch (NoSuchElementException e){
            data.put("error", "No existe el contrato: "+idContrato);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        }
    }

   //TODO Agregar la informacion del empleado al reporte
    @GetMapping("/reportemodificaciones")
    public String getReporteModificaciones(@RequestBody RfcEmpleado body){
        String rfc = "'ejemploderfc'";
        return"<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+registroContratosRepository.getReporteModificaciones(rfc);
    }

    @GetMapping("/concluir")
    public Map <String,Integer> getContratosConcluir(){
        return Collections.singletonMap("contratos", registroContratosRepository.getConcluirContratos());
    }
}
