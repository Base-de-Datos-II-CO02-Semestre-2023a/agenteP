package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Objetivo;
import com.osba.agenteP.enums.TipoCliente;
import com.osba.agenteP.enums.TipoRegimen;
import com.osba.agenteP.model.ExternoInfo;
import jakarta.annotation.Nullable;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;

public interface ExternoRepository extends JpaRepository<Objetivo, Integer> {

    //CHECAR EN POSTMAN
    @Query(value = "SELECT * FROM externo WHERE rfc = :rfc", nativeQuery = true)
    public ExternoInfo getExternoInfo (String rfc);

    //CHECAR EN POSTMAN
    @Query(value = "SELECT * FROM externo WHERE rfc = :rfc", nativeQuery = true)
    public Boolean provedorExistente (String rfc);

    @Query(value = "INSERT INTO externo (id, nombre, telefono, correo, codigoPostal, idCiudad, calle, numeroInterior, numeroExterior,rfc, regimen_fiscal, tipo) VALUES (:id, :nombre, :telefono, :correo, :codigoPostal, :idCiudad, :calle, :numeroInterior, :numeroExterior,:rfc, :regimen_fiscal, :tipo)", nativeQuery = true)
    public Boolean insertarExterno(Integer id, String nombre, BigInteger telefono, String correo, Integer codigoPostal, String idCiudad, String calle,  Integer numeroInterior,Integer numeroExterior,  String rfc, TipoRegimen regimen_fiscal, TipoCliente tipo);

}
