package com.osba.agenteP.repository;

import com.osba.agenteP.domain.RegistroContratos;
import jakarta.persistence.StoredProcedureParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import javax.xml.crypto.dsig.XMLObject;
import java.util.Optional;

public interface RegistroContratosRepository extends JpaRepository<RegistroContratos, Integer> {
    Optional<RegistroContratos> findFirstByIdEmpleado(int idEmpleado);
    Optional<RegistroContratos>  findById(int id);
    @Query(value = "select * from cast ((select * from query_to_xml( 'select * from modificacion_contrato where id_contrato = (select contrato from empleado where rfc = '||:rfc||')',true,false,'')) as varchar)", nativeQuery = true)
    Object getReporteModificaciones( @Param(value = "rfc") String ejemplo);
}

