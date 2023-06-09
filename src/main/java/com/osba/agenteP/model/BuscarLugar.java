package com.osba.agenteP.model;

import lombok.Getter;
import lombok.Setter;

public interface BuscarLugar {
    Integer getId();
    Integer getEmpleados();
    float getProductividad();
    String getNombre();
    String getNombreEncargado();
    String getDireccion();

    String getTipo();
}
