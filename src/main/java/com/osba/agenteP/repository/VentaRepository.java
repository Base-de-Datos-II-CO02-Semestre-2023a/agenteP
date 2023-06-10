package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Venta;
import com.osba.agenteP.model.ArticulosMasVendidos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta,Integer> {


}
