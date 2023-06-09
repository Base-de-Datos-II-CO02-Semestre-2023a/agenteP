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

    @Id @GeneratedValue(generator = "objetivo_id_seq")
    @SequenceGenerator(name = "objetivo_id_seq", sequenceName = "objetivo_id_seq", allocationSize = 1)
    private Integer id;
    private Integer idEmpleado;
    private String descripcion;
    private Double porcentajeAvance;
    private Double impactoProductividad;
}
