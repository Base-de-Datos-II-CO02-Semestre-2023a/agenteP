package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Objetivo;
import com.osba.agenteP.model.EmpleadoInfo;
import com.osba.agenteP.model.ExternoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExternoRepository extends JpaRepository<Objetivo, Integer> {

    //CHECAR EN POSTMAN
    @Query(value = "SELECT * FROM externo WHERE rfc = :rfc", nativeQuery = true)
    public ExternoInfo getExternoInfo (String rfc);

    //CHECAR EN POSTMAN
    @Query(value ="SELECT * FROM externo WHERE rfc = :rfc", nativeQuery = true)
    public Boolean provedorExistente (String rfc);



}
