package com.tienda_vi.controller;

import com.tienda_vi.domain.Categoria;
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
@RequestMapping("/pruebas")
public class PruebasController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var listado = categoriaService.getCategorias(false);
        model.addAttribute("categorias", listado);
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado";
    }

    @GetMapping("/listado/{idCategoria}")
    public String listadoFiltro(Categoria categoria, Model model) {
        categoria = categoriaService.getCategoria(categoria);
        var productos=categoria.getProductos();
        model.addAttribute("productos", productos);
        
        var listado = categoriaService.getCategorias(false);
        model.addAttribute("categorias", listado);
        
        return "/pruebas/listado";
    }
    
    
     @GetMapping("/listado2")
    public String listado2(Model model) {
        var productos = productoService.getProductos(false);
        model.addAttribute("productos", productos);
        return "/pruebas/listado2";
    }
    
    @PostMapping("/query1")
    public String query1(
            @RequestParam("precioInf")double precioInf,
            @RequestParam("precioSup")double precioSup,Model model) {
        var productos = productoService.consultaJPA(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf",precioInf);
        model.addAttribute("precioSup",precioSup);
        
        return "/pruebas/listado2";
    }
    
    
    @PostMapping("/query2")
    public String query2(
            @RequestParam("precioInf")double precioInf,
            @RequestParam("precioSup")double precioSup,Model model) {
        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf",precioInf);
        model.addAttribute("precioSup",precioSup);
        
        return "/pruebas/listado2";
    }
    
    
        @PostMapping("/query3")
    public String query3(
            @RequestParam("precioInf")double precioInf,
            @RequestParam("precioSup")double precioSup,Model model) {
        var productos = productoService.consultaNativa(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf",precioInf);
        model.addAttribute("precioSup",precioSup);
        
        return "/pruebas/listado2";
    }

    
     @PostMapping("/query4")
    public String query4(
            @RequestParam("existencias")int existencias,Model model) {
        var productos = productoService.consultaExistencias(existencias);
        model.addAttribute("productos", productos);
        model.addAttribute("existencias",existencias);
        
        return "/pruebas/listado2";
    }

}
