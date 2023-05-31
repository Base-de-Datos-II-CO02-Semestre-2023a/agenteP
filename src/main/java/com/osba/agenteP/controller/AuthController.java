package com.osba.agenteP.controller;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.exception.EmpleadoSinContratoException;
import com.osba.agenteP.model.LoginCredentials;
import com.osba.agenteP.repository.EmpleadoRepository;
import com.osba.agenteP.security.JWTUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private EmpleadoRepository empleadoRepository;
    @Autowired private JWTUtil jwtUtil;


    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> registerHandler(@RequestBody Empleado empleado){

        System.out.println(empleado.toString());
        String encodedPassword = passwordEncoder.encode(empleado.getPassword());
        empleado.setPassword(encodedPassword);
        empleado = empleadoRepository.save(empleado);
        String token = jwtUtil.generateToken(empleado.getRfc());
        return Collections.singletonMap("jwt-token", token);

    }

    @PostMapping("/login")
    @ResponseBody()
    public ResponseEntity<Map<String, Object>> loginHandler(@RequestBody LoginCredentials body) throws Exception {

        try{
            UsernamePasswordAuthenticationToken authInputToken =
                    new UsernamePasswordAuthenticationToken(
                            body.getRfc(),
                            body.getPassword()
                    );
            authenticationManager.authenticate(authInputToken);
            String token = jwtUtil.generateToken(body.getRfc());
            return ResponseEntity.ok(Collections.singletonMap("jwt_token", token));

        } catch (UsernameNotFoundException e){

            if (e instanceof EmpleadoSinContratoException){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", e.getMessage()));

        }
    }


}
