
package com.tienda_vi.dao;

import com.tienda_vi.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoDao extends JpaRepository<Producto, Long> {
    /*Esta consulta utiliza un Query JPA*/
    public List<Producto> findByPrecioBetweenOrderByDescripcion(double precioInf,double precioSup);
    public List<Producto> findByExistencias(int existencias);

    
    /*Esta consulta utiliza un Query JPQL*/
    @Query(value = "SELECT p "
            + "FROM Producto p "  //Producto va con P en mayuscula porque se refiere a la clase
            + "WHERE p.precio "
            + "BETWEEN :precioInf and :precioSup "
            + "ORDER BY p.descripcion")
    public List<Producto> consultaJPQL(double precioInf,double precioSup);
    
    
    
    /*Esta consulta utiliza un Query con SQL NATIVO*/
    @Query(nativeQuery=true,
            value = "SELECT * "
            + "FROM producto p "  //Aqui se pone en minuscula
            + "WHERE p.precio "
            + "BETWEEN :precioInf and :precioSup "
            + "ORDER BY p.descripcion")
    public List<Producto> consultaNativa(double precioInf,double precioSup);


   
    
}