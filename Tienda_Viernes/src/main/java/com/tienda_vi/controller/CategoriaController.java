package com.tienda_vi.controller;

import com.tienda_vi.service.FirebaseStorageService;
import com.tienda_vi.domain.Categoria;
import com.tienda_vi.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var listado = categoriaService.getCategorias(false);
        model.addAttribute("categorias", listado);
        model.addAttribute("totalCategorias", listado.size());
        return "/categoria/listado";
    }
    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("guardar")
    public String guardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            //Se requiere guardar una imagen en Firebase Storage
            categoriaService.save(categoria);
            String rutaImagen
                    = firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());
            categoria.setRutaImagen(rutaImagen);
        }
        categoriaService.save(categoria);
        return "redirect:/categoria/listado";
    }

 @GetMapping("/modificar/{idCategoria}")
    public String modifica(Categoria categoria, Model model){
        categoria=categoriaService.getCategoria(categoria);
        model.addAttribute("categoria", categoria);
        return"/categoria/modifica";
    }
        @GetMapping("/eliminar/{idCategoria}")
    public String elimina(Categoria categoria){
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }
}