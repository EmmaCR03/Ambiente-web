
package com.tienda_vi.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto{
    private int cantidad;
    
    public Item(Producto p){
        super.setActivo(p.isActivo());
        super.setCategoria(p.getCategoria());
        super.setDescripcion(p.getDescripcion());
        super.setDetalle(p.getDetalle());
        super.setExistencias(p.getExistencias());
        super.setIdProducto(p.getIdProducto());
        super.setPrecio(p.getPrecio());
        super.setRutaImagen(p.getRutaImagen());
        this.cantidad=0;
    
    }
}
