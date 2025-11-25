package com.biblioteca.sistema_biblioteca.repository;

import com.biblioteca.sistema_biblioteca.entity.Emprestimo;
import com.biblioteca.sistema_biblioteca.entity.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByUsuarioId(Long usuarioId);
    List<Emprestimo> findByLivroId(Long livroId);
    List<Emprestimo> findByStatus(StatusEmprestimo status);
    
    @Query("SELECT e FROM Emprestimo e WHERE e.dataDevolucaoPrevista < :dataAtual AND e.status = 'ATIVO'")
    List<Emprestimo> findEmprestimosAtrasados(LocalDateTime dataAtual);
    
    @Query("SELECT COUNT(e) FROM Emprestimo e WHERE e.usuario.id = :usuarioId AND e.status = 'ATIVO'")
    int countEmprestimosAtivosPorUsuario(Long usuarioId);
}