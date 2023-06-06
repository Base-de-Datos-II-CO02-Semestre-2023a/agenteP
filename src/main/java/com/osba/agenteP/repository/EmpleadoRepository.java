package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.domain.EmpleadoProductivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    public Optional<Empleado> findByRfc(String rfc);
    @Query(value = "select * from empleado where contrato is not null", nativeQuery = true)

    public List<Empleado> findEmpleadosActivos();

    @Query(value = "select id, rfc, nombre, indice_productividad as indiceProductividad from empleado where contrato is not null order by indice_productividad desc limit 14", nativeQuery = true)
    List<EmpleadoProductivo> obtenerMásProductivos();

    @Query(value = "select id, rfc, nombre, indice_productividad as indiceProductividad from empleado where contrato is not null  order by indice_productividad asc limit 14", nativeQuery = true)

    List<EmpleadoProductivo> obtenerMenosProductivos();
}