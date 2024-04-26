package com.tienda_vi.controller;

import com.tienda_vi.domain.Item;
import com.tienda_vi.domain.Producto;
import com.tienda_vi.service.ItemService;
import com.tienda_vi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ProductoService productoService;

    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregar(Model model, Item item) {
        Item item2 = itemService.get(item);
        if (item2==null) {
            Producto p = productoService.getProducto(item);
            item2=new Item(p);
        }
        itemService.save(item2);
        var lista = itemService.gets();
        var totalCarrito=0;
        var carritoTotalVenta=0;
        for (Item i : lista) {
            totalCarrito+=i.getCantidad();
            carritoTotalVenta+=(i.getCantidad()*i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarrito);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
    
    @GetMapping("/carrito/listado")
    public String listado(Model model) {
        var listado = itemService.gets();
        var total=0;
        for(Item i : listado){
            total+=i.getPrecio()*i.getCantidad();
        }
        model.addAttribute("items", listado);
        model.addAttribute("carritoTotal", total);

        return "/carrito/listado";
    }
    
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modifica(Item item, Model model) {
      item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

    @GetMapping("/carrito/eliminar/{idProducto}")
    public String elimina(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

}
