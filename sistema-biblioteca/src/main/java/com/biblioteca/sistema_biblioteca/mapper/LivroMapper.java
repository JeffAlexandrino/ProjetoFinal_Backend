package com.biblioteca.sistema_biblioteca.mapper;

import com.biblioteca.sistema_biblioteca.dto.request.LivroRequest;
import com.biblioteca.sistema_biblioteca.dto.response.LivroResponse;
import com.biblioteca.sistema_biblioteca.entity.Livro;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LivroMapper {
    
    public Livro toEntity(LivroRequest request) {
        Livro livro = new Livro();
        livro.setTitulo(request.getTitulo());
        livro.setIsbn(request.getIsbn());
        livro.setAnoPublicacao(request.getAnoPublicacao());
        livro.setEditora(request.getEditora());
        livro.setEdicao(request.getEdicao());
        livro.setQuantidadeTotal(request.getQuantidadeTotal());
        livro.setQuantidadeDisponivel(request.getQuantidadeTotal());
        return livro;
    }
    
    public LivroResponse toResponse(Livro livro) {
        return new LivroResponse(
            livro.getId(),
            livro.getTitulo(),
            livro.getIsbn(),
            livro.getAnoPublicacao(),
            livro.getEditora(),
            livro.getEdicao(),
            livro.getQuantidadeTotal(),
            livro.getQuantidadeDisponivel(),
            livro.getAutores().stream()
                .map(autor -> autor.getNome())
                .collect(Collectors.toList()),
            livro.getCategorias().stream()
                .map(categoria -> categoria.getNome())
                .collect(Collectors.toList())
        );
    }
}