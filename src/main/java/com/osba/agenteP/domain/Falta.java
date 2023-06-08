package com.osba.agenteP.domain;

import com.osba.agenteP.enums.TipoFalta;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "falta")
@ToString
@AllArgsConstructor
public class Falta {

    @Id
    private Integer idEmpleado;
    @Id
    @Enumerated(value = EnumType.STRING)
    private TipoFalta tipo;
    @Id
    private Date fecha;
    private Double impactoProductividad;
    private String descripcion;
}
