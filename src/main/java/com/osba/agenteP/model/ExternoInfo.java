package com.osba.agenteP.model;

import com.osba.agenteP.enums.TipoCliente;
import com.osba.agenteP.enums.TipoRegimen;

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
