package com.example.proyecto_inventado.controladores;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.proyecto_inventado.Servicios.CatalogoService;
import com.example.proyecto_inventado.clases.Material;
import com.example.proyecto_inventado.clases.Ropa;
import com.example.proyecto_inventado.clases.Tipo;
import com.example.proyecto_inventado.clases.Usuario;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InicioController {
    private final CatalogoService catalogoService;

    public InicioController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    // Te lleva al inicio evaluando si tienes sesion o no, y si existen cookies,
    // establece el usuario y saldo de la cookie
    @GetMapping("/")
    public String inicio(Model model, @CookieValue(value = "usuario_nombre", required = false) String usuarioNombre,
            @CookieValue(value = "usuario_saldo", required = false) String usuarioSaldo, HttpSession session) {
        if (usuarioNombre != null && !usuarioNombre.isEmpty() &&
                usuarioSaldo != null && !usuarioSaldo.isEmpty()) {
            try {
                double saldo = Double.parseDouble(usuarioSaldo);
                session.setAttribute("usuario", new Usuario(usuarioNombre, saldo));
            } catch (NumberFormatException e) {
                session.removeAttribute("usuario");
            }
        }

        model.addAttribute("catalogo", catalogoService.getCatalogo());
        return "inicio";
    }

    // Te lleva al inicio sin comprobaciones
    @GetMapping("/inicio")
    public String inicio2(Model model) {
        model.addAttribute("catalogo", catalogoService.getCatalogo());
        return "inicio";
    }

    // Te lleva al inicio de sesión
    @GetMapping("iniciar_sesion")
    public String inicioDeSesion() {
        return "iniciar_sesion";
    }

    // Te lleva a la sección de busqueda
    @GetMapping("/buscar")
    public String getMethodName() {
        return "buscar";
    }

    // Busca por enum Tipo y Enum Material
    @PostMapping("/buscar_datos")
    public String postMethodName(@RequestParam("tipo") Tipo tipo, @RequestParam("material") Material material,
            Model model) {
        Map<Integer, Ropa> catalogo_filtrado = new HashMap<Integer, Ropa>();
        List<Ropa> catalogo_entero = catalogoService.getCatalogo();

        for (int i = 0; i < catalogo_entero.size(); i++) {
            Ropa ropa = catalogo_entero.get(i);
            if (ropa.getMaterial() == material && ropa.getTipo() == tipo) {
                catalogo_filtrado.put(i, ropa);
            }
        }
        model.addAttribute("catalogo", catalogo_filtrado);
        return "buscar";
    }

    // Filtra productos por enum Tipo
    @PostMapping("/filtrar_datos")
    public String filtrar(@RequestParam("tipo") Tipo tipo,
            Model model) {
        Map<Integer, Ropa> catalogo_filtrado = new HashMap<Integer, Ropa>();
        List<Ropa> catalogo_entero = catalogoService.getCatalogo();

        for (int i = 0; i < catalogo_entero.size(); i++) {
            Ropa ropa = catalogo_entero.get(i);
            if (ropa.getTipo() == tipo) {
                catalogo_filtrado.put(i, ropa);
            }
        }
        model.addAttribute("catalogo", catalogo_filtrado);
        return "buscar";
    }
    // Compara precios
    @PostMapping("/comparar_precio")
    public String filtrar(Model model, @RequestParam("menor") int menor) {
        Map<Integer, Ropa> catalogo_ordenado1 = new HashMap<Integer, Ropa>();
        List<Ropa> catalogo_entero = catalogoService.getCatalogo();

        for (int i = 0; i < catalogo_entero.size(); i++) {
            Ropa ropa = catalogo_entero.get(i);
            catalogo_ordenado1.put(i, ropa);
        }
        if (menor == 1) {
            List<Map.Entry<Integer, Ropa>> catalago_ordenado2 = catalogo_ordenado1.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).collect(Collectors.toList());
            model.addAttribute("catalogo", catalago_ordenado2);
        } else {
            List<Map.Entry<Integer, Ropa>> catalago_ordenado2 = catalogo_ordenado1.entrySet().stream()
                    .sorted(Map.Entry.comparingByValue()).collect(Collectors.toList());
            model.addAttribute("catalogo", catalago_ordenado2);
        }
        return "buscar";
    }

}
