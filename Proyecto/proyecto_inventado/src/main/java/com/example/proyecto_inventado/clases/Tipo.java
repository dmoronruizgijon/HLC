package com.example.proyecto_inventado.clases;

public enum Tipo {
    //Enum de tipo de prenda
    TORSO,
    PIERNAS,
    ZAPATOS;

    @Override
    public String toString() {
        String nombre = name().toLowerCase().replace("_", " ");
        return Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
    }
}
