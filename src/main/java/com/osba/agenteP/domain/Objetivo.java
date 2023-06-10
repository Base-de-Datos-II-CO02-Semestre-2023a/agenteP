package com.osba.agenteP.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "objetivo")
@ToString
@AllArgsConstructor
public class Objetivo {



    @Id
    private Integer id;
    private Integer idEmpleado;
    private String descripcion;
    private Double porcentajeAvance;
    private Double impactoProductividad;
}
