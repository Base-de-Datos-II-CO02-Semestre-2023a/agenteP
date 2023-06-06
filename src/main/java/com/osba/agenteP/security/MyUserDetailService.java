package com.osba.agenteP.security;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.exception.EmpleadoSinContratoException;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.repository.RegistroContratosRepository;
import com.osba.agenteP.service.EmpleadoService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Component
public class MyUserDetailService implements UserDetailsService {
    @Autowired private EmpleadoRepository empleadoRepository;

    @Autowired private EmpleadoService empleadoService;

    @Autowired private RegistroContratosRepository registroContratosRepository;

    /**
     * Este metodo nos regresa un usuario de spring security con los datos del empleado
     * @param id the username identifying the user whose data is required.
     * @return
     * @throws UsernameNotFoundException
     */
    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException{

        Optional<Empleado> empleadoRes = empleadoRepository.findById(Integer.parseInt(id));
        if(empleadoRes.isEmpty()){
            throw new UsernameNotFoundException("Empleado no encontrado");
        }
        Empleado empleado = empleadoRes.get();

        return new User(
                id ,
                empleado.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(empleadoService.getPuesto(Integer.parseInt(id))+""))
        );
    }
}
