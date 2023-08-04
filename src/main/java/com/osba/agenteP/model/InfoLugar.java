package com.osba.agenteP.model;

import java.math.BigInteger;

public interface InfoLugar {
    Integer getId();
    Integer getEmpleados();
    Float getProductividad();
    String getNombre();
    String getNombreEncargado();
    String getDireccion();

    String getTipo();
    Long getTelefono();
    String getCorreo();
}
