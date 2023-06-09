package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Lugar;
import com.osba.agenteP.enums.TipoLugar;
import com.osba.agenteP.model.BuscarLugar;
import com.osba.agenteP.model.OptionLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {
    @Query(value = "select nombre, id from lugar where nombre like('%'||:query||'%')" , nativeQuery = true)
    public List<OptionLugar> lugarSuggestions(String query);

    @Query(value = "select s1.id, empleados, tipo, productividad, nombre, nombreEncargado, direccion from (" +
                "select fp.id_lugar as id, count(distinct fp.id_empleado) as empleados, avg(fp.productividad) as productividad " +
                "from empleado de inner join fact_productividad fp " +
                "on de.id = fp.id_empleado " +
                "where de.contrato is not null " +
                "group by fp.id_lugar" +
            ") s1 " +
            "inner join (" +
                "select l.id, l.nombre, l.tipo, e.nombre as nombreEncargado, " +
                    "l.calle||ifnullignore(' #'||l.numero_externo)||ifnullignore(' int '||l.numero_interno)||', '||c.nombre||', '||c.entidad||', '||c.pais||'. C.P:'||l.codigo_postal as direccion " +
                "from lugar l " +
                "inner join empleado e " +
                "on l.id_responsable = e.id " +
                "inner join ciudad c " +
                "on l.id_ciudad = c.id " +
                "where upper(l.nombre) like('%'||upper(:query)||'%')" +
            ") s2 " +
            "on s1.id = s2.id", nativeQuery = true)
    public List<BuscarLugar> searchRH(String query);

    @Query(value = "select s1.id, empleados, tipo, productividad, nombre, nombreEncargado, direccion from (" +
            "select fp.id_lugar as id, count(distinct fp.id_empleado) as empleados, avg(fp.productividad) as productividad " +
            "from empleado de inner join fact_productividad fp " +
            "on de.id = fp.id_empleado " +
            "where de.contrato is not null " +
            "group by fp.id_lugar" +
            ") s1 " +
            "inner join (" +
            "select l.id, l.nombre, l.tipo, e.nombre as nombreEncargado, " +
            "l.calle||ifnullignore(' #'||l.numero_externo)||ifnullignore(' int '||l.numero_interno)||', '||c.nombre||', '||c.entidad||', '||c.pais||'. C.P:'||l.codigo_postal as direccion " +
            "from lugar l " +
            "inner join empleado e " +
            "on l.id_responsable = e.id " +
            "inner join ciudad c " +
            "on l.id_ciudad = c.id " +
            ") s2 " +
            "on s1.id = s2.id", nativeQuery = true)
    public List<BuscarLugar> searchAllRh();
}
