package com.osba.agenteP.repository;

import com.osba.agenteP.domain.RegistroContratos;
import com.osba.agenteP.enums.TipoPuesto;
import com.osba.agenteP.model.EmpleadoPorLugar;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface RegistroContratosRepository extends JpaRepository<RegistroContratos, Integer> {
    Optional<RegistroContratos> findFirstByIdEmpleado(int idEmpleado);
    Optional<RegistroContratos>  findById(int id);
    @Query(value = "select * from cast ((select * from query_to_xml( 'select * from modificacion_contrato where id_contrato = (select contrato from empleado where rfc = '||:rfc||')',true,false,'')) as varchar)", nativeQuery = true)
    Object getReporteModificaciones( @Param(value = "rfc") String ejemplo);

    @Query(value = "SELECT COUNT(*) FROM registro_contratos WHERE ((fecha_fin - current_date) < 14 ) AND (fecha_fin > current_date)", nativeQuery = true)
    public Integer getConcluirContratos();

    @Query(value = "SELECT COUNT(*) FROM registro_contratos WHERE ((fecha_fin - current_date) < 14 ) AND (fecha_fin > current_date) AND (id_lugar = :id_lugar)", nativeQuery = true)
    public Integer getConcluirContratosLugar(Integer id_lugar);

    @Query(value = "SELECT dias_vacaciones FROM registro_contratos WHERE id_empleado = :id_empleado", nativeQuery = true)
    public Integer getVacacionesbyId(Integer id_empleado);

    @Transactional
    @Modifying
    @Query(value ="UPDATE registro_contratos SET id_lugar = :id_lugar WHERE id_empleado = :id_empleado", nativeQuery = true)
    public void moverEmpleadoSucursal(Integer id_empleado,Integer id_lugar);

    @Query(value= "SELECT empleado.rfc, empleado.nombre, registro_contratos.puesto, registro_contratos.salario, empleado.indice_productividad, empleado.fecha_de_ingreso FROM empleado INNER JOIN registro_contratos ON empleado.id = registro_contratos.id_empleado" +
            " WHERE registro_contratos.id_lugar = :idLugar", nativeQuery = true)
    public List<EmpleadoPorLugar> empleadosByLugar(Integer idLugar);

    //CHECAR EN POSTMAN
    @Query(value = "Select fecha_fin From registro_contratos Where id = :id", nativeQuery = true)
    public Date diasFinContrato (Integer id);

    //CHECAR EN POSTMAN
    @Query(value = "SELECT COUNT (tipo) FROM falta WHERE tipo = 'inasistencia' AND id_empleado = :id", nativeQuery = true)
    public Integer inasistencia (Integer id);

    //CHECAR EN POSTMAN
    @Query(value = "Select AVG (o.impacto_productividad) FROM objetivo AS o JOIN empleado AS e ON e.id = o.id_empleado JOIN sujeto AS s ON s.id = e.id JOIN lugar AS l ON  l.id = s.id", nativeQuery = true)
    public Double promedioProductivad (Double impacto_productividad);

    //CHECAR EN POSTMAN
    @Query(value = "UPDATE registro_contratos SET fecha_inicio = :fecha_inicio, fecha_fin = :fecha_fin, puesto = :puesto, salario = :salario, dias_vacaciones = :dias_vacaciones, WHERE id_empleado = :id_empleado", nativeQuery = true)

    public void modificarContrato (Date fecha_inicio, Date fecha_fin, TipoPuesto puesto, Double salario, Integer dias_vacaciones, Integer id_empleado);


}

