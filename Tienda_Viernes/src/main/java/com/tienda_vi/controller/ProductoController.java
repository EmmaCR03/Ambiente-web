package com.tienda_vi.controller;

import com.tienda_vi.service.FirebaseStorageService;
import com.tienda_vi.domain.Producto;
import com.tienda_vi.service.CategoriaService;
import com.tienda_vi.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        var listado = productoService.getProductos(false);
        model.addAttribute("productos", listado);
        model.addAttribute("totalProductos", listado.size());

        return "/producto/listado";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String guardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            productoService.save(producto);
            String rutaImagen = firebaseStorageService.cargaImagen(imagenFile,
                    "producto", producto.getIdProducto());
            producto.setRutaImagen(rutaImagen);
        }
        productoService.save(producto);
        return "redirect:/producto/listado";

    }

    @GetMapping("/modificar/{idProducto}")
    public String modifica(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        model.addAttribute("producto", producto);
        return "/producto/modifica";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String elimina(Producto producto) {
        productoService.delete(producto);
        return "redirect:/producto/listado";
    }
}
