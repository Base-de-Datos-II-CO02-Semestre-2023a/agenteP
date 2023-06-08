package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Empleado;
import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.enums.TipoFalta;
import com.osba.agenteP.model.EmpleadoEncontrado;
import com.osba.agenteP.model.EmpleadoInfo;
import com.osba.agenteP.model.EmpleadoProductivo;
import com.osba.agenteP.model.ProductividadMes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Integer> {
    public Optional<Empleado> findByRfc(String rfc);
    public Optional<Empleado> findEmpleadoById(Integer id);
    @Query(value = "select * from empleado where contrato is not null order by nombre", nativeQuery = true)
    public List<Empleado> findEmpleadosActivos();

    @Query(value = "select id, rfc, nombre, indice_productividad as indiceProductividad from empleado where contrato is not null order by indice_productividad desc limit 14", nativeQuery = true)
    public List<EmpleadoProductivo> obtenerMásProductivos();

    @Query(value = "select id, rfc, nombre, indice_productividad as indiceProductividad from empleado where contrato is not null  order by indice_productividad asc limit 14", nativeQuery = true)
    public List<EmpleadoProductivo> obtenerMenosProductivos();

    @Query(value = "select id from empleado where rfc = :rfc", nativeQuery = true)
    public Integer findIdByRfc(String rfc);

    @Query(value = "select e.id as id," +
            " e.rfc as rfc," +
            " e.nombre as nombre," +
            " rc.puesto as puesto," +
            " rc.salario as salario," +
            " e.indice_productividad as indiceProductividad," +
            " rc.id_lugar as idLugar," +
            " e.fecha_de_ingreso as fechaDeIngreso " +
            "from empleado e inner join registro_contratos rc on e.contrato = rc.id"+
            " where " +
            "(lower(rfc) like('%'||lower(:query)||'%') or " +
            "lower(nombre) like('%'||lower(:query)||'%') or " +
            "lower(correo) like('%'||lower(:query)||'%'))",nativeQuery = true)
    public List<EmpleadoEncontrado> findEmpleadosByRfcOrNombreOrCorreo(String query);

    @Query(nativeQuery = true, value = "select count(*) from registro_vacaciones where fecha_fin > current_date and fecha_inicio < current_date")
    public Integer contarEmpleadosVacaciones();

    //Queda pendiente el where contrato is not null
    @Query(value = "SELECT AVG(indice_productividad) FROM empleado", nativeQuery = true)
    public Double getPromedioProductividad();

    @Transactional
    @Modifying
    @Query(value = "UPDATE empleado SET contrato = null WHERE id = :id AND (contrato is not null)", nativeQuery = true)
    public void despedirEmpleado(Integer id);

    @Query(value = "SELECT * FROM empleado WHERE contrato is not null AND (id = :id)", nativeQuery = true)
    public Optional<Empleado> findEmpleadobyIdContrato(Integer id);

    @Query(value = "select avg(f.productividad) as productividad, d.mes as mes from fact_productividad f " +
            "    inner join dim_tiempo d on f.id_tiempo = d.id " +
            "where id_empleado = :id " +
            "group by mes;", nativeQuery = true)
    public List<ProductividadMes> getHistorialProductividad(Integer id);

    @Query(nativeQuery = true, value = "select e.id, e.nombre, e.rfc, e.nss, e.telefono, " +
            "rc.puesto, l.nombre as nombreLugar, " +
            "e.calle||ifnullignore(' #'||e.numero_externo)||ifnullignore(' int.'||e.numero_interno)||', '||c.nombre||', '||c.entidad||', '||c.pais as direccion, " +
            "e.correo, rc.salario, e.indice_productividad as indiceProductividad, current_date - e.fecha_de_ingreso as diasInicio, rc.fecha_fin - current_date as diasFin from empleado e " +
            "left join control_asistencia ca on e.id = ca.id_empleado " +
            "left join registro_contratos rc on e.contrato = rc.id " +
            "left join lugar l on rc.id_lugar = l.id " +
            "left join ciudad c on e.id_ciudad = c.id " +
            "where e.id = :id")


    public EmpleadoInfo getEmpleadoInfo(Integer id);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO falta(id_empleado, tipo, fecha, impacto_productividad, descripcion) VALUES (:idEmpleado, :tipoFalta, :fechaFalta, :impacto, :descripcion)", nativeQuery = true)
    public void registrarFalta(Integer idEmpleado, String tipoFalta, Date fechaFalta, Double impacto, String descripcion);



}