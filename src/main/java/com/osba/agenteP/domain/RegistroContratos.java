package com.osba.agenteP.domain;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@Table(name = "registro_contratos")
public class RegistroContratos {
    @Id @GeneratedValue
    private int id;
    private int idEmpleado;
    private Date fechaInicio;
    @Nullable
    private Date fechaFin;
    private String puesto;
    private float salario;
    private int diasVacaciones;

}
