package com.tienda_vi.service.impl;

import com.tienda_vi.dao.ProductoDao;
import com.tienda_vi.domain.Producto;
import com.tienda_vi.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductoServiceImpl implements ProductoService{
    
    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly=true)
    public List<Producto> getProductos(boolean activos) {
        var listado=productoDao.findAll();
        
        if (activos) { //si solo quiero los activos
            listado.removeIf(c -> c.isActivo());
            
        }
        
        return listado;
        
    }
    
    @Override
    @Transactional(readOnly=true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }
 
    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
 
    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }
    
    
    @Override
    @Transactional(readOnly=true)
      /*Esta consulta utiliza un Query JPA*/
    public List<Producto> consultaJPA(double precioInf,double precioSup){
        return productoDao.findByPrecioBetweenOrderByDescripcion(precioInf, precioSup);
    }
    
    @Override
    @Transactional(readOnly=true)
    public List<Producto> consultaExistencias(int existencias){
        return productoDao.findByExistencias(existencias);
    }
    
    
    
    
    @Override
    @Transactional(readOnly=true)
    /*Esta consulta utiliza un Query JPQL*/
    public List<Producto> consultaJPQL(double precioInf,double precioSup){
        return productoDao.consultaJPQL(precioInf, precioSup);
    }
    
    @Override
    @Transactional(readOnly=true)
    /*Esta consulta utiliza un Query con SQL NATIVO*/
    public List<Producto> consultaNativa(double precioInf,double precioSup){
        return productoDao.consultaNativa(precioInf, precioSup);
    }
    
}

