
package com.tienda_vi.service;

import com.tienda_vi.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    
    
    //Recupera una lista de los registros de as tabla categoria
    public List<Categoria> getCategorias(boolean activos);
    
    //Recupera un registro de la tabla categoria, buscando el atributo idCategoria en la tabla
    public Categoria getCategoria(Categoria categoria);
    
    
    //Elimina un registro de la tabla categoria si rencuentra un registro con el idCategoria pasado
    public void delete(Categoria categoria);
    
    
    //Si el objeto categoria pasado por paramentro tiene un valor en idCategoria
    //lo intenta actualizar. Si no tiene valor idCategoria, se crea el registro
    
    public void save(Categoria categoria);
}
