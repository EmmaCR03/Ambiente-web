
package com.tienda_vi.service;

import com.tienda_vi.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoService {
    
    
    //Recupera una lista de los registros de as tabla producto
    public List<Producto> getProductos(boolean activos);
    
    //Recupera un registro de la tabla producto, buscando el atributo idProducto en la tabla
    public Producto getProducto(Producto producto);
    
    
    //Elimina un registro de la tabla producto si rencuentra un registro con el idProducto pasado
    public void delete(Producto producto);
    
    
    //Si el objeto producto pasado por paramentro tiene un valor en idProducto
    //lo intenta actualizar. Si no tiene valor idProducto, se crea el registro
    
    public void save(Producto producto);
            
    /*Esta consulta utiliza un Query JPA*/
    public List<Producto> consultaJPA(double precioInf,double precioSup);
    public List<Producto> consultaExistencias(int existencias);
    
    /*Esta consulta utiliza un Query JPQL*/
    public List<Producto> consultaJPQL(double precioInf,double precioSup);
    
    
    
    /*Esta consulta utiliza un Query con SQL NATIVO*/
    public List<Producto> consultaNativa(double precioInf,double precioSup);
    
    
   
    
}
