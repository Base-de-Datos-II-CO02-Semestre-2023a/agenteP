package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Articulo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticuloRepository extends JpaRepository<Articulo, Integer> {
    public Optional<Articulo> findById(String id);
}
