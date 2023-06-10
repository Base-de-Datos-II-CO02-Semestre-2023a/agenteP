package com.osba.agenteP.domain;


import com.osba.agenteP.enums.TipoCliente;
import com.osba.agenteP.enums.TipoRegimen;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;

@Entity
@Table(name = "externo")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Externo {

    @Id
    private Integer id;
    private String nombre;
    private BigInteger telefono;
    private String correo;
    private Integer codigoPostal;
    private String idCiudad;
    private String calle;
    @Nullable
    private Integer numeroInterno;
    @Nullable
    private Integer numeroExterno;

    private String rfc;
    private TipoRegimen regimenFiscal;

    private TipoCliente tipo;

}
