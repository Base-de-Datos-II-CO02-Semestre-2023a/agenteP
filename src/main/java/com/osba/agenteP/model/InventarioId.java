package com.osba.agenteP.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
@Getter
@Setter
@ToString
@NoArgsConstructor

public class InventarioId {
    private Integer idLugar;
    private Integer idArticulo;
    private Date caducidad;
}
