package com.osba.agenteP.domain;


import com.osba.agenteP.enums.TipoCliente;
import com.osba.agenteP.enums.TipoRegimen;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Externo extends Sujeto{


    private String rfc;
    private TipoRegimen regimenFiscal;

    private TipoCliente tipo;

}
