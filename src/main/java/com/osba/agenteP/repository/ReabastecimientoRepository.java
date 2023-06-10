package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Objetivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;

public interface ReabastecimientoRepository extends JpaRepository<Objetivo, Integer> {

    @Query(value = "INSERT INTO reabastecimiento (id, id_lugar, id_provedor, total_compra, fecha)\n" +
            "VALUES (:id, :id_lugar, :id_provedor, :total_compra, :fecha);", nativeQuery = true)
    public void crearReabastecimiento (Integer id_lugar, Integer id_rovedor, Integer idProvedor, Double total_compra, Date fecha) ;






}
