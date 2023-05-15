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
public class Empleado extends Sujeto {
    
    
    @Id @GeneratedValue(generator = "empleado_id_seq")
    @SequenceGenerator(name = "empleado_id_seq", sequenceName = "empleado_id_seq", allocationSize = 1)
   
    private BigInteger nss;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String rfc;
    private Date fechaDeNacimiento;
    private Date fechaDeIngreso;
    @Nullable
    private Integer contrato;

    private float indiceProductividad;
    
    public Empleado (){
        
        super();
        
    ]

}
