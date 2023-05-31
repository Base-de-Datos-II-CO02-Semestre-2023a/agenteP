package com.osba.agenteP.repository;

import com.osba.agenteP.domain.RegistroContratos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RegistroContratosRepository extends JpaRepository<RegistroContratos, Integer> {
    Optional<RegistroContratos> findFirstByIdEmpleado(int idEmpleado);
    Optional<RegistroContratos>  findById(int id);
}
