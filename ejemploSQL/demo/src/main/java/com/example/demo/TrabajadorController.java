package com.example.demo;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class TrabajadorController {

    private final TrabajadorRepository repository;

    public TrabajadorController(TrabajadorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/trabajadores")
    public List<Trabajador> listarTrabajadores() {
        return repository.findAll();
    }

    @GetMapping("/trabajadoresApellidos")
    public List <Trabajador> getMethodName() {
        return repository.findByApellidos("asdf");
    }
    
}
