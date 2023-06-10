package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Objetivo;
import com.osba.agenteP.model.EmpleadoInfo;
import com.osba.agenteP.model.ExternoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExternoRepository extends JpaRepository<Objetivo, Integer> {

    @Query(value = "SELECT * FROM reabastecimiento WHERE id_provedor = proveedor_id;", nativeQuery = true)
    public ExternoInfo getExternoInfo (Integer proveedorId);

    @Query(value ="SELECT * FROM proveedor WHERE id_proveedor = proveedor_id;", nativeQuery = true)
    public Boolean provedorExistente (Integer proveedorId);



}
