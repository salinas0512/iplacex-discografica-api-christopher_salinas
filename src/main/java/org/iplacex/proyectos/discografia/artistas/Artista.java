package org.iplacex.proyectos.discografia.artistas;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Esta clase representa a un artista en mi base de datos de MongoDBatlas
@Document(collection = "artistas") // Indico que esta clase se guarda en la colección "artistas" de mi BDD
public class Artista {
    @Id 
    public String _id;

    public String nombre;

    // Lista de estilos musicales asociados al artista (por ejemplo: rock, pop, etc.)
    public List<String> estilos;

    // Año en que se fundó el artista o banda
    public int anioFundacion;

    // Indica si el artista sigue activo actualmente
    public boolean estaActivo;
}
