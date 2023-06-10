package com.osba.agenteP.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "reabastecimiento")
@ToString
@AllArgsConstructor
public class Reabastecimiento {

    @Id
    private Integer id;
    private Integer id_lugar;
    private Integer id_provedor;
    private Double total_compra;
    private Date fecha;



}
