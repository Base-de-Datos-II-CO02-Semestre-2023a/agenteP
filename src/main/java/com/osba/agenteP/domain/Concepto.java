package com.osba.agenteP.domain;

import com.osba.agenteP.enums.TipoMovimiento;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "concepto")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Concepto {

    private Integer cantidad;
    @Id
    private Integer idArticulo;
    @Id
    private Integer idMovimiento;
    private Date caducidad;
    private Double precioUnitario;
    private Double precioBase;
    @Id
    private TipoMovimiento tipo;
    private Double monto;



}
