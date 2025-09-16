package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Esta clase representa un disco musical en mi base de datos
@Document(collection = "discos") // Indico que esta clase se guarda en la colección "discos"
public class Disco {
    @Id
    public String _id;

    public String idArtista; // Id del artista al que pertenece este disco
    public String nombre; // Nombre del disco
    public int anioLanzamiento; // Año en que salió el disco
    public List<String> canciones; // Lista de canciones que tiene el disco
}
