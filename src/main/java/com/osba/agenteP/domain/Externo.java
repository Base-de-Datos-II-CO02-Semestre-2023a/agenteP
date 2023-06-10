package com.osba.agenteP.domain;


import com.osba.agenteP.model.TipoCliente;
import com.osba.agenteP.model.TipoRegimen;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "externo")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Externo extends Sujeto{


    private String rfc;
    private TipoRegimen regimenFiscal;

    private TipoCliente tipo;

}
