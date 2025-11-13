package com.example.proyecto_inventado.clases;

import lombok.Getter;

@Getter

public class Zapatos extends Ropa{
    // Talla numérica
    private int tallaZapatos;


    public Zapatos(String nombre, double precio, String marca, String descripcion, Tipo tipo,int tallaZapatos) {
        super(nombre, precio, marca, descripcion, tipo);
        this.tallaZapatos = tallaZapatos;
    }

    @Override
    public String toString() {
        return super.toString()+" Talla:"+tallaZapatos;
    }

}
