package com.example.proyecto_inventado.clases;

public enum Temporada {
    // Enum de la temporada actual
    INVIERNO,
    VERANO,
    OTOÑO,
    PRIMAVERA;

    @Override
    public String toString() {
        String nombre = name().toLowerCase().replace("_", " ");
        return Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
    }
}
