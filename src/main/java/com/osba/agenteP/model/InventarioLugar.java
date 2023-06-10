package com.osba.agenteP.model;

import com.osba.agenteP.enums.Unidad;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


public interface InventarioLugar {

     Integer getId();
     String getNombre();
     @Enumerated(value = EnumType.STRING)
     Unidad getUnidad();
     Integer getCantidad();

}
