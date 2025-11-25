package com.biblioteca.sistema_biblioteca.resource;

import com.biblioteca.sistema_biblioteca.entity.Categoria;
import com.biblioteca.sistema_biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return ResponseEntity.ok(categoriaSalva);
    }
    
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }
}