package org.iplacex.proyectos.discografia.discos;

import java.util.List;
import java.util.Optional;

import org.iplacex.proyectos.discografia.artistas.Artista;
import org.iplacex.proyectos.discografia.artistas.IArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DiscoController {

    @Autowired
    private IDiscoRepository discoRepository;
    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(value = "/disco", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para la creación de un nuevo disco
    public ResponseEntity<Disco> HandlePostDiscoRequest(@RequestBody Disco disco) {
        // Validar que el artista existe antes de crear el registro del nuevo disco
        if (!artistaRepository.existsById(disco.idArtista)) {
            // Si el artista no existe, devuelvo 404 y no creo el registro del disco
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        Disco nuevoDisco = discoRepository.insert(disco);
        return new ResponseEntity<Disco>(nuevoDisco, null, HttpStatus.CREATED);
    }

    @GetMapping(value = "/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para obtener todos los registros en la colección "discos"
    public ResponseEntity<List<Disco>> HandleGetDiscosRequest() {
        List<Disco> discos = discoRepository.findAll();
        return new ResponseEntity<>(discos, HttpStatus.OK);
    }

    @GetMapping(value = "/disco/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para obtener un disco por su ID
    public ResponseEntity<Disco> HandleGetDiscoRequest(@PathVariable("id") String id) {
        Optional<Disco> disco = discoRepository.findById(id);
        if (!disco.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(disco.get(), null, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/artista/{id}/discos", produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para obtener todos los discos de un artista en base a su ID desde la
    // colección "discos"
    public ResponseEntity<List<Disco>> HandleGetDiscosByArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (!artista.isPresent()) {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
        // Aquí uso el método personalizado que cree en IDiscoRepository
        List<Disco> discos = discoRepository.findDiscosByIdArtista(id);
        return new ResponseEntity<>(discos, null, HttpStatus.OK);
    }

}
