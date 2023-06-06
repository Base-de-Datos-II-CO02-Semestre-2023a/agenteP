package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CiudadRepository extends JpaRepository<Ciudad, String> {
    @Query(nativeQuery = true, value = "select * from ciudad where lower(nombre) like ('%'||lower(:query)||'%')" +
            "or lower(id) like ('%'||lower(:query)||'%') " +
            "or lower(id) like ('%'||lower(:query)||'%') " +
            "or lower(entidad) like( '%'||lower(:query)||'%' )" +
            "or lower(pais) like ('%'||lower(:query)||'%') limit 5")
    public List<Ciudad> buscarCiudad(String query);
}
