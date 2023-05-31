package com.osba.agenteP.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ciudad")
public class Ciudad {
    @Id
    private Integer id;
    private String entidad;
    private String pais;
    private String nombre;

}
