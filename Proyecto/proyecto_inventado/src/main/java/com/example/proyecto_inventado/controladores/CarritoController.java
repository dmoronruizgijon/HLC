package com.example.proyecto_inventado.controladores;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyecto_inventado.Servicios.CatalogoService;
import com.example.proyecto_inventado.clases.Usuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CarritoController {
    private final CatalogoService catalogoService;

    public CarritoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    // Añade o crea un carrito en sesion
    @PostMapping("/carrito")
    public String addCarrito(@RequestParam("prenda_carrito") int id, HttpSession session) {
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");

        if (carrito == null) {
            carrito = new HashMap<>();
        }

        Object obj = session.getAttribute("usuario");

        if (obj == null) {
            return "iniciar_sesion";
        }

        // Añade el id y mira la cantidad en ese id
        carrito.put(id, carrito.getOrDefault(id, 0) + 1);

        // Guardamos de nuevo en la sesión
        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    // Llama al carrito de la sesión y lo muestra junto al precio total
    @GetMapping("/carrito")
    public String verCarrito(
            @CookieValue(value = "usuario_nombre", required = false) String usuarioNombre,
            @CookieValue(value = "usuario_saldo", required = false) String usuarioSaldo,
            Model model, HttpSession session) {
        model.addAttribute("catalogo", catalogoService.getCatalogo());

        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
        double total = 0.0;

        if (carrito != null) {
            for (Map.Entry<Integer, Integer> entry : carrito.entrySet()) {
                int id = entry.getKey();
                int cantidad = entry.getValue();
                total += catalogoService.getCatalogo().get(id).getPrecio() * cantidad;
                double redondeado = Math.round(total * 100.0) / 100.0;
                model.addAttribute("total", redondeado);
            }
        }

        if (usuarioNombre != null && usuarioSaldo != null) {
            model.addAttribute("usuarioNombre", usuarioNombre);
            model.addAttribute("usuarioSaldo", Double.parseDouble(usuarioSaldo));
        }

        return "carrito";
    }

    // Elimina un producto del carrito
    @PostMapping("/eliminar")
    public String postMethodName(@RequestParam("eliminar_id") int id, HttpSession session) {
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");

        // Quita cantidad 1 o lo borra
        if (carrito.get(id) > 1) {
            carrito.put(id, carrito.get(id) - 1);
        } else {
            carrito.remove(id);
        }

        // Guardamos de nuevo en la sesión
        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    // Elimina todos los productos con el mismo id del carrito
    @PostMapping("/eliminar_todo")
    public String eliminarProducto(@RequestParam("eliminar_id") int id, HttpSession session) {
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");
        carrito.remove(id);

        // Guardamos de nuevo en la sesión
        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    // Añade una unidad a un producto ya existente en carrito
    @GetMapping("/añadir")
    public String getMethodName(@RequestParam("id") int id, HttpSession session) {
        Map<Integer, Integer> carrito = (Map<Integer, Integer>) session.getAttribute("carrito");

        carrito.put(id, carrito.get(id) + 1);

        // Guardamos de nuevo en la sesión
        session.setAttribute("carrito", carrito);

        return "redirect:/carrito";
    }

    // Evalua si el usuario tiene el saldo sufieciente y lo redirije.
    // Destruye el carrito anterior y guarda el saldo actualizado en cookie
    @PostMapping("/comprar")
    public String compra(HttpSession session, HttpServletResponse response,
            @RequestParam("total") double total) {

        Object obj = session.getAttribute("usuario");

        Usuario user = (Usuario) obj;

        if (total > user.getDinero()) {
            return "compra_fallida";
        } else {
            double numero = user.getDinero() - total;
            double redondeado = Math.round(numero);
            user.setDinero(redondeado);
            session.setAttribute("usuario", user);
            session.setAttribute("carrito", null);
            Cookie cookie = new Cookie("usuario_saldo", String.valueOf(user.getDinero()));
            cookie.setPath("/");
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return "compra_realizada";
        }

    }
}
