package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjetivoRepository extends JpaRepository<Objetivo, Integer> {

    @Query(value = "SELECT * FROM objetivo",nativeQuery = true)
    public List<Objetivo> getObjetivo();
    @Query(value = "SELECT porcentaje_avance FROM objetivo WHERE id_empleado = :id", nativeQuery = true)
    public Double getAvance(Integer id);

    //CHECAR EN POSTMAN
    @Query(value = "INSERT INTO objetivo (id_empleado, descripcion, porcentaje_avance, impacto_productividad) VALUES (:id_empleado, :descripcion, :porcentaje_avance, :impacto_productividad)", nativeQuery = true)
    public void crearObjetivo (Integer id_empleado, String descripcion,  Double porcentaje_avance, Double  impacto_productividad);
}
