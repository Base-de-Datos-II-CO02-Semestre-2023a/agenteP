package com.osba.agenteP.domain;

import com.osba.agenteP.enums.TipoLugar;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Entity
@Table(name = "lugar")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lugar {
    @Id @GeneratedValue(generator = "lugar_id_seq")
    @SequenceGenerator(name = "lugar_id_seq", sequenceName = "lugar_id_seq", allocationSize = 1)
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
    @Enumerated(EnumType.STRING)
    private TipoLugar tipoLugar;
    private Integer idResponsable;
    private float capAlmacenamientoMax;
}
