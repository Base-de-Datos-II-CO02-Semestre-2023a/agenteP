package com.osba.agenteP.service;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.repository.RegistroContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistroContratosService {
    @Autowired
    private RegistroContratosRepository registroContratosRepository;
    @Autowired
    private EmpleadoService empleadoService;

    public Optional<RegistroContratos> getContrato(String rfc){
        Empleado empleado = empleadoService.getEmpleado(rfc).get();

        assert empleado.getContrato() != null;
        return registroContratosRepository.findById(empleado.getContrato());
    }
}
