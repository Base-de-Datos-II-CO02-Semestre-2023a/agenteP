package com.osba.agenteP.security;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.exception.EmpleadoSinContratoException;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.repository.RegistroContratosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired private EmpleadoRepository empleadoRepository;

    @Autowired private RegistroContratosRepository registroContratosRepository;

    /**
     * Este metodo nos regresa un usuario de spring security con los datos del empleado
     * @param rfc the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String rfc) throws UsernameNotFoundException{


        Optional<Empleado> empleadoRes = empleadoRepository.findByRfc(rfc);
        if(empleadoRes.isEmpty()){
            throw new UsernameNotFoundException("Empleado no encontrado");
        }
        Empleado empleado = empleadoRes.get();
        int id = empleado.getId();
        Integer contratoId = empleado.getContrato();
        if (contratoId == null){
            throw new EmpleadoSinContratoException(empleado.getId()+" No es un empleado activo");
        }

        Optional<RegistroContratos> registroContratosRes = registroContratosRepository.findById(contratoId);

        if (registroContratosRes.isEmpty()){
            throw new EmpleadoSinContratoException("Empleado "+empleado.getId()+" tiene errores con el contrato, favor de contactar a RH");
        }
        RegistroContratos contrato = registroContratosRes.get();
        System.out.println(contrato.getPuesto());
        return new User(
                empleado.getId()+"",
                empleado.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(contrato.getPuesto()))
        );
    }
}
