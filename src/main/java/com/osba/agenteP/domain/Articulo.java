package com.osba.agenteP.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "articulo")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class Articulo {
    @Id @GeneratedValue(generator = "articulo_id_seq")
    @SequenceGenerator(name = "articulo_id_seq", sequenceName = "articulo_id_seq", allocationSize = 1)

    private String nombre;
    private String descripcion;
    private String unidad;
    private String categoria;
    private Integer obj_imp;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String caracteristicas;
    private float precio_base;
    private float porcentaje_iva;
    private float porcentaje_ieps;
    private float porcentaje_ganancia;

}
