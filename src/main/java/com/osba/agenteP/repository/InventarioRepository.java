package com.osba.agenteP.repository;

import com.osba.agenteP.domain.Inventario;
import com.osba.agenteP.model.InventarioLugar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InventarioRepository extends JpaRepository<Inventario,Integer> {

    @Query(value = "SELECT COUNT(id_articulo) FROM inventario WHERE ((caducidad - current_date) < 14) AND (caducidad > current_date)", nativeQuery = true)
    public Integer productosCaducar();

    @Query(value="SELECT COUNT(id_articulo) FROM inventario WHERE (cantidad < 15)", nativeQuery = true)
    public Integer reabasteciemtoRecommended();

    @Query(value="SELECT * FROM inventario", nativeQuery = true)
    public List<Inventario> cosasEnInventario();

    // la tabla a la que se consulta es inventario o articulo?
    /*@Query(value = "", nativeQuery = true)
    public Integer articulosDistintos();*/

    @Query(value = "SELECT  inventario.id_articulo, articulo.nombre, articulo.unidad, inventario.cantidad FROM inventario INNER JOIN articulo " +
            "ON inventario.id_articulo = articulo.id INNER JOIN lugar ON inventario.id_lugar = lugar.id WHERE lugar.nombre LIKE :nombre", nativeQuery = true)
    public List<InventarioLugar> inventarioLugar(String nombre);

}
