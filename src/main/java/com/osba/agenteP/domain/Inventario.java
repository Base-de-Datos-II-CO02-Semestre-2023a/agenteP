package com.osba.agenteP.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "inventario")
public class Inventario {
    private Integer cantidad;
    private float descuento;
    @Id
    private Integer id_lugar;
    @Id
    private Integer id_articulo;
    private Date caducidad;

}
