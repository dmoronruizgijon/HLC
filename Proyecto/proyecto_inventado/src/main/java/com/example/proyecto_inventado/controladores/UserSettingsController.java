package com.example.proyecto_inventado.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class UserSettingsController {
    // Lleva a los ajustes del usuario
    @GetMapping("/ajustes")
    public String getMethodName() {
        return "user_settings";
    }
    // Lleva al monedero del usario
    @GetMapping("/saldo")
    public String irAMonedero() {
        return "saldo";
    }
    
    
}
