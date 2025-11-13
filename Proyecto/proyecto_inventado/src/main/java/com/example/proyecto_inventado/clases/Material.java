package com.example.proyecto_inventado.clases;

public enum Material {
    // Enum de material de ropa
    ALGODON,
    POLIESTER,
    LANA,
    SEDA,
    LINO,
    VAQUERO,
    CUERO,
    GOMA,
    NAILON,
    TELA_SINTETICA,
    ANTE,
    MALLA,
    CAUCHO,
    LONA;


    @Override
    public String toString() {
        String nombre = name().toLowerCase().replace("_", " ");
        return Character.toUpperCase(nombre.charAt(0)) + nombre.substring(1);
    }
}
