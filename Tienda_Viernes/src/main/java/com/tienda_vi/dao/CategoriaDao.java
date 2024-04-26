
package com.tienda_vi.dao;

import com.tienda_vi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDao extends JpaRepository <Categoria, Long> {

   
    
}