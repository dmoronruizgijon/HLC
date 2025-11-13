package com.example.proyecto_inventado.clases;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Usuario implements InterfazUsuario{
    // Nombre y saldo
    private String username;
    private double dinero;
    
    public Usuario(String username) {
        this.username = username;
        this.dinero=0;
    }


    // Este constructor se usa a la hora de recuperar el usuario por la cookie
    public Usuario(String username, double dinero) {
    this.username = username;
    this.dinero = dinero;
}


    @Override
    public void ingresar(double ingreso) {
        this.dinero+=ingreso;
    }

    @Override
    public void sacar(double sacado) {
        if (this.dinero>=sacado) {
            this.dinero-=sacado;
        }
    }

    
    
}
