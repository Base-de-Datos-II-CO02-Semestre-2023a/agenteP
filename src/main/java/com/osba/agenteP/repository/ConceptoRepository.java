package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Concepto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptoRepository extends JpaRepository<Concepto, Long> {
}
