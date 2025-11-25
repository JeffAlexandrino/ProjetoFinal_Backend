package com.biblioteca.sistema_biblioteca.resource;

import com.biblioteca.sistema_biblioteca.entity.Autor;
import com.biblioteca.sistema_biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/autores")
public class AutorResource {
    
    @Autowired
    private AutorRepository autorRepository;
    
    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody Autor autor) {
        Autor autorSalvo = autorRepository.save(autor);
        return ResponseEntity.ok(autorSalvo);
    }
    
    @GetMapping
    public ResponseEntity<List<Autor>> listarTodosAutores() {
        List<Autor> autores = autorRepository.findAll();
        return ResponseEntity.ok(autores);
    }
    
    @GetMapping("/buscar")
    public ResponseEntity<List<Autor>> buscarAutoresPorNome(@RequestParam String nome) {
        List<Autor> autores = autorRepository.findByNomeContainingIgnoreCase(nome);
        return ResponseEntity.ok(autores);
    }
}