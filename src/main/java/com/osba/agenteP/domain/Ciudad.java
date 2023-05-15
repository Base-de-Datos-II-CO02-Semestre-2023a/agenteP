package com.osba.agenteP.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ciudad")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Ciudad {

    @Id @GeneratedValue(generator = "ciudad_id_seq")
    @SequenceGenerator(name = "ciudad_id_seq", sequenceName = "ciudad_id_seq", allocationSize = 1)
    private String id_ciudad;
    private String entidad;
    private String pais;
    private String nombre;


}
