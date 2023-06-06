package com.osba.agenteP.model;

import com.osba.agenteP.enums.TipoLugar;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusquedaLugar {
    String query;
}
