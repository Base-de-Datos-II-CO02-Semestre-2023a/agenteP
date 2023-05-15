package com.osba.agenteP.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "empleado")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Sujeto {
      
    //ATRIBUTOS
  
    private Integer id;
    private String nombre;
    private BigInteger telefono;
    private String correo;
    private Integer codigoPostal;
    private String idCiudad = new Ciudad().id;
    private String calle;
    @Nullable
    private Integer numeroInterno;
    @Nullable
    private Integer numeroExterno;
 
}
