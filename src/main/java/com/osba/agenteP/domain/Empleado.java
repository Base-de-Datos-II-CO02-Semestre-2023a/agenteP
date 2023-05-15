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

/**
 * Clase que representa a un empleado de la empresa
 */
@Entity
@Table(name = "empleado")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Empleado {
    //Estas anotaciones son usadas para indicar el nombre de la secuencia en la BD, esto evita el error:
    // empleado_seq no existe
    @Id @GeneratedValue(generator = "empleado_id_seq")
    @SequenceGenerator(name = "empleado_id_seq", sequenceName = "empleado_id_seq", allocationSize = 1)
    private Integer id;
    private String nombre;
    private BigInteger telefono;
    private String correo;
    private Integer codigoPostal;
    private String idCiudad;
    private String calle;
    @Nullable
    private Integer numeroInterno;
    @Nullable
    private Integer numeroExterno;
    private BigInteger nss;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String rfc;
    private Date fechaDeNacimiento;
    private Date fechaDeIngreso;
    @Nullable
    private Integer contrato;

    private float indiceProductividad;

}
