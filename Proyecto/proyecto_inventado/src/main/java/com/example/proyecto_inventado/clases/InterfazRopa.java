package com.example.proyecto_inventado.clases;

import java.time.LocalDate;
import java.time.Month;

public interface InterfazRopa {
    public static Temporada temporadaActual(){
        LocalDate hoy = LocalDate.now();    // Fecha actual
        Month mes = hoy.getMonth(); // Mes actual
        int dia = hoy.getDayOfMonth(); // Dia actual
        if ((mes == Month.DECEMBER && dia >= 21) || mes == Month.JANUARY || mes == Month.FEBRUARY || (mes == Month.MARCH && dia < 20)) {
            return Temporada.INVIERNO;
        } else if ((mes == Month.MARCH && dia >= 20) || mes == Month.APRIL || mes == Month.MAY || (mes == Month.JUNE && dia < 21)) {
            return Temporada.PRIMAVERA;
        } else if ((mes == Month.JUNE && dia >= 21) || mes == Month.JULY || mes == Month.AUGUST || (mes == Month.SEPTEMBER && dia < 22)) {
            return Temporada.VERANO;
        } else {
            return Temporada.OTOÑO;
        }
    };
    // Los números que hay en la condición del while son los materiales que no puede tener cierto tipo de prenda
    public static Material materialDisponible(Tipo tipo) {
    int rand;
    int total = Material.values().length;

    if (tipo == Tipo.TORSO) {
        
        do {
            rand = (int)(Math.random() * total);
        } while (rand == 5 || rand == 6 || rand == 7 || rand == 10 || rand == 11 || rand == 12);
        return Material.values()[rand];

    } else if (tipo == Tipo.PIERNAS) {
        
        do {
            rand = (int)(Math.random() * total);
        } while (rand == 3 || rand == 6 || rand == 7 || rand == 10 || rand == 11 || rand == 12);
        return Material.values()[rand];

    } else { // ZAPATO
        
        do {
            rand = (int)(Math.random() * total);
        } while (rand == 5 || rand == 3 || rand == 0 || rand == 2 || rand == 4);
        return Material.values()[rand];
    }
};
}
