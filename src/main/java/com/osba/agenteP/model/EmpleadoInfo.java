package com.osba.agenteP.model;

public interface EmpleadoInfo {
    Integer getId();
    Long getNss();
    Long getTelefono();
    String getRfc();
    String getPuesto();
    String getNombre();
    String getNombreLugar();
    String getDireccion();
    String getCorreo();

    float getIndiceProductividad();

    String getDiasInicio();
    float getSalario();
    String getDiasFin();



}
