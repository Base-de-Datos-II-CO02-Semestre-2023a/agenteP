package com.osba.agenteP.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class EmpleadoSinContratoException extends UsernameNotFoundException {

    public EmpleadoSinContratoException(String msg){
        super( msg);
    }
}

