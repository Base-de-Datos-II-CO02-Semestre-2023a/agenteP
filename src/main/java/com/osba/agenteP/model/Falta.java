package com.osba.agenteP.model;

import com.osba.agenteP.enums.TipoFalta;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class Falta {

    private Integer id_empleado;
    private TipoFalta tipo;
    private Date fecha;
    private Double impactoProductividad;
}
