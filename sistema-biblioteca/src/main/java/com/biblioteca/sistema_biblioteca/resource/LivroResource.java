package com.biblioteca.sistema_biblioteca.resource;

import com.biblioteca.sistema_biblioteca.dto.request.LivroRequest;
import com.biblioteca.sistema_biblioteca.dto.response.LivroResponse;
import com.biblioteca.sistema_biblioteca.service.LivroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@Tag(name = "Livros", description = "Operações relacionadas ao acervo de livros")
public class LivroResource {
    
    @Autowired
    private LivroService livroService;
    
    @PostMapping
    @Operation(summary = "Criar livro", description = "Adiciona um novo livro ao acervo")
    public ResponseEntity<LivroResponse> criarLivro(@Valid @RequestBody LivroRequest request) {
        LivroResponse response = livroService.criarLivro(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar livros", description = "Retorna todos os livros do acervo")
    public ResponseEntity<List<LivroResponse>> listarTodosLivros() {
        List<LivroResponse> livros = livroService.listarTodosLivros();
        return ResponseEntity.ok(livros);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar livro por ID", description = "Retorna um livro específico pelo ID")
    public ResponseEntity<LivroResponse> buscarLivroPorId(@PathVariable Long id) {
        LivroResponse livro = livroService.buscarLivroPorId(id);
        return ResponseEntity.ok(livro);
    }
    
    @GetMapping("/buscar")
    @Operation(summary = "Buscar livros por título", description = "Busca livros por parte do título")
    public ResponseEntity<List<LivroResponse>> buscarLivrosPorTitulo(@RequestParam String titulo) {
        List<LivroResponse> livros = livroService.buscarLivrosPorTitulo(titulo);
        return ResponseEntity.ok(livros);
    }
    
    @GetMapping("/disponiveis")
    @Operation(summary = "Listar livros disponíveis", description = "Retorna apenas os livros disponíveis para empréstimo")
    public ResponseEntity<List<LivroResponse>> listarLivrosDisponiveis() {
        List<LivroResponse> livros = livroService.listarLivrosDisponiveis();
        return ResponseEntity.ok(livros);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar livro", description = "Remove um livro do acervo")
    public ResponseEntity<Void> deletarLivro(@PathVariable Long id) {
        livroService.deletarLivro(id);
        return ResponseEntity.noContent().build();
    }
}