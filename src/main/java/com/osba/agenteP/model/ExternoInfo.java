package com.osba.agenteP.model;

public interface ExternoInfo  {

    Integer getId();

    String getNombre();

    Long getTelefono();

    String getCorreo();

    Integer getCodigoPostal();

    String getIdCiudad();

    String getCalle();

    Integer getNumeroInterno();

    Integer getNumeroExterno();


    String getRfc();
    TipoRegimen getRegimenFiscal();

    TipoCliente getTipo();


}
