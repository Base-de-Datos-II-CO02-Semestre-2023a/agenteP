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
@Table(name = "externo")
@Getter
@Setter
@ToString
@NoArgsConstructor

public class externo Extends Sujeto {
  
    @Id @GeneratedValue(generator = "externo_id_seq")
    @SequenceGenerator(name = "externo_id_seq", sequenceName = "externo_id_seq", allocationSize = 1)
  
    private String rfc;
    private BigInteger regimenFiscal;
    private Integer tipo;
   
    public externo (){
      
      super();
      
    }

}
