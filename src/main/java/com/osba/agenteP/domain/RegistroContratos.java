package com.osba.agenteP.domain;

import com.osba.agenteP.enums.TipoPuesto;
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
    @Id @GeneratedValue(generator = "registro_contratos_id_seq")
    @SequenceGenerator(name="registro_contratos_id_seq", sequenceName = "registro_contratos_id_seq", allocationSize=1)
    private int id;
    private int idEmpleado;
    private int idLugar;
    private Date fechaInicio;
    @Nullable
    private Date fechaFin;
    @Enumerated(EnumType.STRING)
    private TipoPuesto puesto;
    private Double salario;
    private int horasDiarias;
    private int diasVacaciones;

}
