package com.example.proyecto_inventado.Servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.proyecto_inventado.clases.Piernas;
import com.example.proyecto_inventado.clases.Ropa;
import com.example.proyecto_inventado.clases.Tipo;
import com.example.proyecto_inventado.clases.Torso;
import com.example.proyecto_inventado.clases.Zapatos;

import lombok.Getter;

@Getter
@Service
public class CatalogoService {

        // Servicio con el catálogo constante
    private final List<Ropa> catalogo = new ArrayList<>();

    public CatalogoService() {
        catalogo.add(new Zapatos("Converse", 15.99, "Paco Rabane",
                "Zapatillas Converse clásicas, cómodas y resistentes, ideales para el día a día y para combinar con cualquier estilo urbano.", Tipo.ZAPATOS, 44));

        catalogo.add(new Torso("Camiseta", 50.99, "Moschino",
                "Camiseta de algodón de alta calidad con estampado elegante, perfecta para un look casual o para combinar con prendas más formales.", Tipo.TORSO, "L"));

        catalogo.add(new Piernas("Vaqueros", 33.50, "Levis",
                "Vaqueros ajustados de tejido resistente, cómodos para uso diario y con un diseño clásico que nunca pasa de moda.", Tipo.PIERNAS, "S"));

        catalogo.add(new Zapatos("Nike Air Max", 120.00, "Nike",
                "Zapatillas deportivas Nike Air Max, con suela acolchada y diseño ergonómico que ofrece máximo confort durante largas jornadas.", Tipo.ZAPATOS, 42));

        catalogo.add(new Torso("Sudadera", 65.00, "Adidas",
                "Sudadera con capucha de tejido suave y cálido, perfecta para el invierno o para un look deportivo y moderno.", Tipo.TORSO, "M"));

        catalogo.add(new Piernas("Pantalón Chino", 45.99, "Zara",
                "Pantalón chino elegante color beige, cómodo y versátil, ideal para la oficina o para salidas informales.", Tipo.PIERNAS, "M"));

        catalogo.add(new Zapatos("Botas", 89.99, "Timberland",
                "Botas Timberland resistentes al agua, con suela antideslizante y diseño robusto para aventuras al aire libre o uso urbano.", Tipo.ZAPATOS, 43));

        catalogo.add(new Torso("Camisa", 39.90, "Hugo Boss",
                "Camisa de vestir azul de corte clásico, ideal para reuniones formales o eventos especiales, con acabado de alta calidad.", Tipo.TORSO, "L"));

        catalogo.add(new Piernas("Shorts", 25.00, "Puma",
                "Shorts deportivos ligeros y transpirables, perfectos para entrenamientos, running o actividades al aire libre en verano.", Tipo.PIERNAS, "M"));

        catalogo.add(new Zapatos("Sandalias", 19.99, "Havaianas",
                "Sandalias cómodas y ligeras, perfectas para la playa o para un look veraniego casual, fabricadas con materiales resistentes al agua.", Tipo.ZAPATOS, 41));

        catalogo.add(new Torso("Chaqueta", 120.50, "North Face",
                "Chaqueta impermeable y cortavientos, ideal para actividades al aire libre en climas lluviosos o fríos, con detalles de alta calidad.", Tipo.TORSO, "XL"));

        catalogo.add(new Piernas("Leggins", 29.99, "Nike",
                "Leggins de entrenamiento de alta elasticidad, transpirables y cómodos, perfectos para gimnasio, yoga o actividades deportivas.", Tipo.PIERNAS, "S"));

        catalogo.add(new Zapatos("Mocasines", 75.00, "Clarks",
                "Mocasines de vestir cómodos y elegantes, confeccionados en cuero de alta calidad, ideales para oficina o eventos formales.", Tipo.ZAPATOS, 42));

        catalogo.add(new Torso("Jersey", 55.00, "Tommy Hilfiger",
                "Jersey de lana azul marino, cálido y cómodo, perfecto para combinar con camisas o prendas casuales durante el otoño e invierno.", Tipo.TORSO, "M"));

        catalogo.add(new Piernas("Falda", 40.00, "Mango",
                "Falda de flores primaveral, ligera y fresca, con un diseño elegante y cómodo que combina con diferentes tops y calzado casual.", Tipo.PIERNAS, "S"));
    }

}
