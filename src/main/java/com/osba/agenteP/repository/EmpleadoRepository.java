package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Empleado;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    public Optional<Empleado> findByRfc(String rfc);
}