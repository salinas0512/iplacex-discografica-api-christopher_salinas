package org.iplacex.proyectos.discografia.artistas;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;

//Para mi entendimiento, esta interfaz es responsable de la comunicación con la base de datos para la clase Artista
@Repository
public interface IArtistaRepository extends MongoRepository<Artista, String> {

}
