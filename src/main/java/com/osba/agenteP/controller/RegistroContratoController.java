package com.osba.agenteP.controller;

import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.repository.RegistroContratosRepository;
import com.osba.agenteP.service.RegistroContratosService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
class IdContrato{
    Integer idContrato = null;
}
@RestController
@RequestMapping("/contrato")
public class RegistroContratoController {

    @Autowired
    private RegistroContratosRepository registroContratosRepository;
    @Autowired
    private RegistroContratosService registroContratosService;
    @GetMapping()
    public RegistroContratos contrato(){
        String rfc = SecurityContextHolder.getContext().getAuthentication().getName();
        return registroContratosService.getContrato(rfc).get();
    }
    @PostMapping()
    public Map<String, Integer> setContrato(@RequestBody RegistroContratos newContrato){
        RegistroContratos contrato = registroContratosRepository.save(newContrato);
        return Collections.singletonMap("contrato", contrato.getId());
    }
    @DeleteMapping()
    public ResponseEntity<Map<String, Object>> popContrato(@RequestBody IdContrato body){
        HashMap<String, Object> data = new HashMap<>();
        Integer idContrato = body.idContrato;
        try{
            registroContratosRepository.deleteById(idContrato);
            data.put("mensaje","Se elimino el contrato: "+idContrato);

            return ResponseEntity.ok(data);
        }catch (Error e){
            data.put("error", "Error interno");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(data);

        }
    }
}
