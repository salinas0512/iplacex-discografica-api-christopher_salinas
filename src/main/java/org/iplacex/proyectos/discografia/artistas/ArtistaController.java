package org.iplacex.proyectos.discografia.artistas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ArtistaController {

    @Autowired
    private IArtistaRepository artistaRepository;

    @PostMapping(value = "/artista", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)

    // Método para crear un nuevo artista
    // Aquí se usa insert() para generar un registro único del artista
    // Devuelvo el artista creado junto con el estado CREATED para confirmar la
    // operación
    public ResponseEntity<Artista> HandleInsertArtistaRequest(@RequestBody Artista artista) {
        Artista nuevoArtista = artistaRepository.insert(artista);
        return new ResponseEntity<>(nuevoArtista, HttpStatus.CREATED);
    }

    @GetMapping(value = "/artistas", produces = MediaType.APPLICATION_JSON_VALUE

    )

    // Método para obtener todos los registros en la colección "artistas"
    public ResponseEntity<List<Artista>> HandleGetArtistasRequest() {
        List<Artista> artistas = artistaRepository.findAll();
        return new ResponseEntity<>(artistas, HttpStatus.OK);
    }

    @GetMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para obtener un registro en base a su Id
    // Para mi entendimiento, se usa @PathVariable para que Spring tome el id
    // directamente de la URL (por ejemplo, /api/artista/123)
    // findById devuelve un Optional, así que tengo que revisar si el artista existe
    // antes de devolverlo
    public ResponseEntity<Artista> HandleGetArtistaRequest(@PathVariable("id") String id) {
        Optional<Artista> artista = artistaRepository.findById(id);
        if (!artista.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(artista.get(), HttpStatus.OK);
        }
    }

    @PutMapping(value = "/artista/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para actualizar un registro de la colección "artistas" en base a su id
    public ResponseEntity<Artista> HandleUpdateArtistaRequest(@PathVariable("id") String id,
            @RequestBody Artista artista) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        artista._id = id; // Aseguro que el id del artista a actualizar sea el mismo que el de la URL
        Artista guardarArtista = artistaRepository.save(artista);
        return new ResponseEntity<>(guardarArtista, HttpStatus.OK);
    }

    @DeleteMapping(value = "/artista/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // Método para eliminar un registro de la colección "artistas" en base a su id
    public ResponseEntity<Artista> HandleDeleteArtistaRequest(@PathVariable("id") String id) {
        if (!artistaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Artista encontrarArtista = artistaRepository.findById(id).get();
        artistaRepository.deleteById(id);
        return new ResponseEntity<>(encontrarArtista, HttpStatus.OK);
    }
}