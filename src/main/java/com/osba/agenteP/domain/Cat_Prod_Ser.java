package com.osba.agenteP.domain;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "cat_prod_serv")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cat_Prod_Ser {
    @Id @GeneratedValue(generator = "Cat_Prod_ser_clave_eq")
    @SequenceGenerator(name = "Cat_Prod_ser_clave_seq", sequenceName = "Cat_Prod_ser_clave_seq", allocationSize = 1)
    
  private Integer clave;
  private String descripcion;
}
