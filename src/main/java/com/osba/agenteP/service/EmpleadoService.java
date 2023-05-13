package com.osba.agenteP.service;

import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    public List<Empleado> getEmpleados(){
        return empleadoRepository.findAll();
    }



}
