package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.enums.TipoPuesto;
import com.osba.agenteP.exception.EmpleadoSinContratoException;
import com.osba.agenteP.model.LoginCredentials;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.security.JWTUtil;
import com.osba.agenteP.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import org.postgresql.util.PSQLException;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired private JWTUtil jwtUtil;

    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private PasswordEncoder passwordEncoder;

    //TODO: Cambiar el campo que maneja las sesiones de rfc a id
    // TODO: Manejar los errores y enviar mensajes de error
    @PostMapping("/register")

    public Map<String, Object>registrarEmpleado(@RequestBody Empleado empleado){
        System.out.println(empleado);

        String encodedPassword = passwordEncoder.encode(empleado.getPassword());
        empleado.setPassword(encodedPassword);
        try {
                empleado = empleadoRepository.save(empleado);
                HashMap<String, Object> data = new HashMap<>();
                data.put("id", empleado.getId());
                data.put("nombre", empleado.getNombre());
                return data;
        } catch (DataIntegrityViolationException e){
            PSQLException err = (PSQLException) e.getRootCause();
            String errCode = err.getSQLState();


            if (errCode.equals("23505")){
                String errField = err.getServerErrorMessage().getDetail().split("\\(")[1].split("\\)")[0];
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errField+" ya registrado", err);
            }
            if (errCode.equals("22001")){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hay un campo que excede el tamaño permitido", err);
            }

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error desconocido", err);
        } catch (IllegalArgumentException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Argumentos invalidos", e);
        }
    }

    @PostMapping("/login")
    @ResponseBody()
    public Map<String, Object> iniciarSesion(@RequestBody LoginCredentials body) throws Exception {
        Integer id = empleadoRepository.findIdByRfc(body.getRfc());
        try{
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(
                            Integer.toString(id),
                            body.getPassword()
                    );
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(id);
            HashMap<String, Object> data = new HashMap<>();


            TipoPuesto tipoPuesto = empleadoService.getPuesto(id);

            data.put("jwt_token", token);
            data.put("id", id);
            data.put("puesto", tipoPuesto);

            return data;
        } catch (EmpleadoSinContratoException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        } catch (UsernameNotFoundException e){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage(),e);
        } catch (AuthenticationException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        } catch (NullPointerException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado", e);
        }

    }


}
