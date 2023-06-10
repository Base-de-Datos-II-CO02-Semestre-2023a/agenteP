package com.osba.agenteP.domain;

import com.osba.agenteP.enums.MetodoPago;
import com.osba.agenteP.enums.TipoMovimiento;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "venta")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Venta {

    @Id
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private TipoMovimiento tipo;
    private Integer idLugar;
    private Integer idEmpleado;
    private Integer idCliente;
    private Double subtotal;
    private Double iva;
    private Double total;
    @Enumerated(value = EnumType.STRING)
    private MetodoPago metodoPago;

}
