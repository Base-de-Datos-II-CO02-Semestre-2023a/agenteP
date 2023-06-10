package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Concepto;
import com.osba.agenteP.model.ArticulosMasVendidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConceptoRepository extends JpaRepository<Concepto, Integer> {

    @Query(value = "SELECT articulo.nombre, COUNT(concepto.id_articulo) AS cantidad\n" +
            "FROM concepto\n" +
            "INNER JOIN articulo ON articulo.id = concepto.id_articulo\n" +
            "GROUP BY articulo.nombre\n" +
            "ORDER BY cantidad DESC\n" +
            "LIMIT 4;", nativeQuery = true)
    public List<ArticulosMasVendidos> articulosMasVendidos();
}
