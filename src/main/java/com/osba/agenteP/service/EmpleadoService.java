package com.osba.agenteP.service;

import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.enums.TipoPuesto;
import com.osba.agenteP.exception.EmpleadoSinContratoException;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.repository.RegistroContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoService {
    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private RegistroContratosRepository registroContratosRepository;
    public List<Empleado> getEmpleados(){
        return empleadoRepository.findEmpleadosActivos();
    }

    public Empleado getEmpleado(Integer id){
        return empleadoRepository.findEmpleadoById(id).get();
    }

    public TipoPuesto getPuesto(Integer id) throws EmpleadoSinContratoException {
        Empleado empleado = getEmpleado(id);

        Integer contratoId = empleado.getContrato();
        if (contratoId == null){
            throw new EmpleadoSinContratoException(" No es un empleado activo");
        }

        Optional<RegistroContratos> registroContratosRes = registroContratosRepository.findById(contratoId);

        if (registroContratosRes.isEmpty()){
            throw new EmpleadoSinContratoException("Empleado "+empleado.getRfc()+" tiene errores con el contrato, favor de contactar a RH");
        }
        RegistroContratos contrato = registroContratosRes.get();
        if ( contrato.getFechaFin() != null && contrato.getFechaFin().after(new Date())){
            throw new EmpleadoSinContratoException("Al empleado "+empleado.getNombre()+" ya se le acabó el contrato, favor de contactar a RH");
        }

        return contrato.getPuesto();

    }


}
