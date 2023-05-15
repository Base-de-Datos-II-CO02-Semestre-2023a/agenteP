package com.osba.agenteP.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "CAT_PROD_SER")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Cat_Prod_Ser {
    @Id @GeneratedValue(generator = "Cat_Prod_ser_clave_eq")
    @SequenceGenerator(name = "Cat_Prod_ser_clave_seq", sequenceName = "Cat_Prod_ser_clave_seq", allocationSize = 1)
    
  private Integer clave;
  private String descripcion;
    
  public Cat_Prod_Ser(){}

}
