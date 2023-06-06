package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Lugar;
import com.osba.agenteP.enums.TipoLugar;
import com.osba.agenteP.model.OptionLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {
    @Query(value = "select nombre, id from lugar where nombre like('%'||:query||'%')" , nativeQuery = true)
    public List<OptionLugar> lugarSuggestions(String query);
}
