package com.example.proyecto_inventado.clases;


import lombok.Getter;

@Getter
public class Torso extends Ropa{
    // Talla alfabetica
    private String tallaPrendas;

    public Torso(String nombre, double precio, String marca, String descripcion, Tipo tipo,String tallaPrendas) {
        super(nombre, precio, marca, descripcion, tipo);
        this.tallaPrendas = tallaPrendas;
    }

    @Override
    public String toString() {
        return super.toString()+" Talla:"+tallaPrendas;
    }

    
}
