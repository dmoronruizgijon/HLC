package com.example.proyecto_inventado.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyecto_inventado.Servicios.CatalogoService;


@Controller
public class DetallesController {
     private final CatalogoService catalogoService;

    public DetallesController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }
    // Te lleva al producto seleccionado
    @GetMapping("detalles")
    public String mostrarDetalles(@RequestParam("id") int id, Model model) {
        model.addAttribute("prenda", catalogoService.getCatalogo().get(id));
        model.addAttribute("prendaid", id);
        return "detalles";
    }
    
}
