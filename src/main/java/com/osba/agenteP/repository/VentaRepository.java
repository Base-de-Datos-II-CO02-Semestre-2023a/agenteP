package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VentaRepository extends JpaRepository<Venta,Integer> {

    @Query(value = "SELECT COUNT(id) FROM venta", nativeQuery = true)
    public Integer ventasTotales();
}
