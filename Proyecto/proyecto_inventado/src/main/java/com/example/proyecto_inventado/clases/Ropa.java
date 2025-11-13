package com.example.proyecto_inventado.clases;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
@Component
public abstract class Ropa implements InterfazRopa, Comparable<Ropa>{
    private String nombre;
    private double precio;
    private Material material;
    private String marca;
    private String descripcion; // Descripción que aparecera en detalles.html
    private Temporada temporada;
    private Tipo tipo;

    public Ropa(String nombre, double precio, String marca, String descripcion, Tipo tipo) {
        this.nombre = nombre;
        this.precio = precio;
        this.tipo=tipo;
        this.material = InterfazRopa.materialDisponible(tipo);  // Le da un material aleatorio pero que concuerde con el tipo de prenda
        this.marca = marca;
        this.descripcion = descripcion;
        this.temporada = InterfazRopa.temporadaActual();    // Le da la temporada actual (Otoño)
    }

    @Override
    public String toString() {
        return "Ropa nombre=" + nombre + ", precio=" + precio + ", material=" + material + ", marca=" + marca
                + ", descripcion=" + descripcion + ", temporada=" + temporada.toString() + ", tipo=" + tipo.toString();
    }
    // Comparador de precios
    @Override
    public int compareTo(Ropa otro){
        return Double.compare(this.precio, otro.precio);
    }
    
}
