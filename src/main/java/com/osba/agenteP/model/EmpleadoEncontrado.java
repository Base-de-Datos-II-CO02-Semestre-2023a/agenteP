package com.osba.agenteP.model;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.domain.RegistroContratos;

import java.util.Date;

public interface EmpleadoEncontrado{
    Integer getId();
    String getRfc();
    String getNombre();
    String getPuesto();
    String getSalario();
    float getIndiceProductividad();
    String getIdLugar();
    Date getFechaDeIngreso();
}
