package com.biblioteca.sistema_biblioteca.service;

import com.biblioteca.sistema_biblioteca.dto.request.LivroRequest;
import com.biblioteca.sistema_biblioteca.dto.response.LivroResponse;
import com.biblioteca.sistema_biblioteca.entity.Autor;
import com.biblioteca.sistema_biblioteca.entity.Categoria;
import com.biblioteca.sistema_biblioteca.entity.Livro;
import com.biblioteca.sistema_biblioteca.mapper.LivroMapper;
import com.biblioteca.sistema_biblioteca.repository.AutorRepository;
import com.biblioteca.sistema_biblioteca.repository.CategoriaRepository;
import com.biblioteca.sistema_biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {
    
    @Autowired
    private LivroRepository livroRepository;
    
    @Autowired
    private AutorRepository autorRepository;
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    
    @Autowired
    private LivroMapper livroMapper;
    
    public LivroResponse criarLivro(LivroRequest request) {
        if (request.getIsbn() != null && livroRepository.existsByIsbn(request.getIsbn())) {
            throw new RuntimeException("Já existe um livro com este ISBN");
        }
        
        Livro livro = livroMapper.toEntity(request);
        
        // Adicionar autores
        if (request.getAutoresIds() != null) {
            List<Autor> autores = autorRepository.findAllById(request.getAutoresIds());
            livro.setAutores(autores);
        }
        
        // Adicionar categorias
        if (request.getCategoriasIds() != null) {
            List<Categoria> categorias = categoriaRepository.findAllById(request.getCategoriasIds());
            livro.setCategorias(categorias);
        }
        
        Livro livroSalvo = livroRepository.save(livro);
        return livroMapper.toResponse(livroSalvo);
    }
    
    public List<LivroResponse> listarTodosLivros() {
        return livroRepository.findAll().stream()
            .map(livroMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public LivroResponse buscarLivroPorId(Long id) {
        Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        return livroMapper.toResponse(livro);
    }
    
    public List<LivroResponse> buscarLivrosPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo).stream()
            .map(livroMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public List<LivroResponse> listarLivrosDisponiveis() {
        return livroRepository.findLivrosDisponiveis().stream()
            .map(livroMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public void deletarLivro(Long id) {
        Livro livro = livroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        livroRepository.delete(livro);
    }
    
    // Método interno para uso de outras services
    public Livro buscarLivroEntityPorId(Long id) {
        return livroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }
    
    // Método interno para atualizar quantidade disponível
    public void atualizarQuantidadeDisponivel(Livro livro) {
        livroRepository.save(livro);
    }
}