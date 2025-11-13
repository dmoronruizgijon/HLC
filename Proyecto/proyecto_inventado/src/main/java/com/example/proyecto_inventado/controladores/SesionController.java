package com.example.proyecto_inventado.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.proyecto_inventado.clases.Usuario;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SesionController {
    // Inicia sesión y guarda datos en la cookie
    @PostMapping("inicio")
    public String inicioSesion(@RequestParam("usuario") String usuario, @RequestParam("contra") String contrasena,
            HttpSession session, Model model, HttpServletResponse response) {
        if (usuario.equals("Superbat") && contrasena.equals("Dani")) {
            session.setAttribute("usuario", new Usuario(usuario));

            Object obj = session.getAttribute("usuario");

            Usuario user = (Usuario) obj;

            Cookie cookieNombre = new Cookie("usuario_nombre", user.getUsername());
            Cookie cookieSaldo = new Cookie("usuario_saldo", String.valueOf(user.getDinero()));

            cookieNombre.setPath("/");
            cookieSaldo.setPath("/");

            cookieNombre.setMaxAge(60 * 60); // 1 hora
            cookieSaldo.setMaxAge(60 * 60);

            cookieNombre.setHttpOnly(true);
            cookieSaldo.setHttpOnly(true);

            response.addCookie(cookieNombre);
            response.addCookie(cookieSaldo);
            return "redirect:/";
        } else {
            model.addAttribute("inicio_malo", "Se ha equivocado en el usuario o la contraseña");
            return "iniciar_sesion";
        }
    }

    // Cierra sesión y destruye la cookie
    @GetMapping("/cerrar_sesion")
    public String getMethodName(HttpSession session, HttpServletResponse response) {
        session.invalidate();

        Cookie cookieNombre = new Cookie("usuario_nombre", null);
        cookieNombre.setPath("/");
        cookieNombre.setHttpOnly(true);
        cookieNombre.setMaxAge(0);
        Cookie cookieSaldo = new Cookie("usuario_saldo", null);
        cookieSaldo.setPath("/");
        cookieSaldo.setHttpOnly(true);
        cookieSaldo.setMaxAge(0);
        response.addCookie(cookieNombre);
        response.addCookie(cookieSaldo);

        return "redirect:/";
    }

    // Añade saldo
    @PostMapping("anadir_saldo")
    public String postMethodName(HttpSession session, HttpServletResponse response,
            @RequestParam("añadir") double dinero) {
        Object obj = session.getAttribute("usuario");

        Usuario user = (Usuario) obj;

        user.ingresar(dinero);

        session.setAttribute("usuario", user);

        Cookie cookie = new Cookie("usuario_saldo", String.valueOf(user.getDinero()));

        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        return "redirect:/";
    }

    // Saca saldo
    @PostMapping("sacar_saldo")
    public String sacarDinero(HttpSession session, HttpServletResponse response, @RequestParam("sacar") double dinero,
            Model model) {
        Object obj = session.getAttribute("usuario");

        Usuario user = (Usuario) obj;

        if (dinero > user.getDinero()) {
            String msgError = "Debera sacar como máximo: " + String.valueOf(user.getDinero()) + " €";
            model.addAttribute("error", msgError);
            return "saldo";
        }

        user.sacar(dinero);

        session.setAttribute("usuario", user);

        Cookie cookie = new Cookie("usuario_saldo", String.valueOf(user.getDinero()));

        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60);
        response.addCookie(cookie);

        return "redirect:/";
    }

}
