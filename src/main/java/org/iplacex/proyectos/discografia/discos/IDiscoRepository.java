package org.iplacex.proyectos.discografia.discos;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IDiscoRepository extends MongoRepository<Disco, String> {
    // Este método es personalizado y busca discos por el ID del artista
    @Query("{ 'idArtista': ?0 }")
    List<Disco> findDiscosByIdArtista(String idArtista);
}
