package com.osba.agenteP.domain;

import com.osba.agenteP.model.InventarioId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "inventario")
@Getter
@Setter
@ToString
@NoArgsConstructor
@IdClass(InventarioId.class)
public class Inventario {

    private Integer cantidad;
    private Double descuento;
    @Id
    private Integer idLugar;
    @Id
    private Integer idArticulo;
    private Double precioBase;
    @Id
    private Date caducidad;
}
