package com.osba.agenteP.model;

import com.osba.agenteP.enums.TipoPuesto;

import java.util.Date;


public interface EmpleadoPorLugar {

    String getRfc();
    String getNombre();
    TipoPuesto getPuesto();
    Float getSalario();
    Float getIndiceProductividad();
    Date getFechaDeIngreso();

}
