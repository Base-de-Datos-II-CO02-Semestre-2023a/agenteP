package com.osba.agenteP.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudad")

public class Ciudad {

    private Integer id_ciudad;
    private String entidad;
    private String pais;
    private String nombre;

}
