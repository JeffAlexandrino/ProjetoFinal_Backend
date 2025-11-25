package com.biblioteca.sistema_biblioteca.repository;

import com.biblioteca.sistema_biblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);
    List<Livro> findByTituloContainingIgnoreCase(String titulo);
    
    @Query("SELECT l FROM Livro l WHERE l.quantidadeDisponivel > 0")
    List<Livro> findLivrosDisponiveis();
    
    boolean existsByIsbn(String isbn);
}