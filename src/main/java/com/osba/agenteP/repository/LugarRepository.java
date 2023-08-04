package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Lugar;
import com.osba.agenteP.enums.TipoLugar;
import com.osba.agenteP.model.BuscarLugar;
import com.osba.agenteP.model.EmpleadoEncontrado;
import com.osba.agenteP.model.InfoLugar;
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
    @Query(value = "select s1.id, nombre, telefono, empleados, tipo, direccion, nombreEncargado, correo, productividad from (" +
            "select fp.id_lugar as id, count(distinct fp.id_empleado) as empleados, avg(fp.productividad) as productividad " +
            "from empleado de inner join fact_productividad fp " +
            "on de.id = fp.id_empleado " +
            "where de.contrato is not null " +
            "group by fp.id_lugar" +
            ") s1 " +
            "inner join (" +
            "select l.id, l.nombre, l.telefono, l.correo, l.tipo, e.nombre as nombreEncargado, " +
            "l.calle||ifnullignore(' #'||l.numero_externo)||ifnullignore(' int '||l.numero_interno)||', '||c.nombre||', '||c.entidad||', '||c.pais||'. C.P:'||l.codigo_postal as direccion " +
            "from lugar l " +
            "inner join empleado e " +
            "on l.id_responsable = e.id " +
            "inner join ciudad c " +
            "on l.id_ciudad = c.id " +
            "where l.id = :id " +
            ") s2 " +
            "on s1.id = s2.id", nativeQuery = true)
    public InfoLugar getInforamtion(int id);

    @Query(value =" select e.id as id," +
                    " e.rfc as rfc," +
                    " e.nombre as nombre," +
                    " rc.puesto as puesto," +
                    " rc.salario as salario," +
                    " e.indice_productividad as indiceProductividad," +
                    " rc.id_lugar as idLugar," +
                    " e.fecha_de_ingreso as fechaDeIngreso " +
                    "from empleado e inner join registro_contratos rc on e.contrato = rc.id"+
                    " where id_lugar =  :lugar" , nativeQuery = true)
    public List<EmpleadoEncontrado> empleados( Integer lugar);
}
