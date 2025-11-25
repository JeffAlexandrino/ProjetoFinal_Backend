package com.biblioteca.sistema_biblioteca.resource;

import com.biblioteca.sistema_biblioteca.dto.request.EmprestimoRequest;
import com.biblioteca.sistema_biblioteca.dto.response.EmprestimoResponse;
import com.biblioteca.sistema_biblioteca.service.EmprestimoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
@Tag(name = "Empréstimos", description = "Operações relacionadas aos empréstimos de livros")
public class EmprestimoResource {
    
    @Autowired
    private EmprestimoService emprestimoService;
    
    @PostMapping
    @Operation(summary = "Realizar empréstimo", description = "Registra um novo empréstimo de livro")
    public ResponseEntity<EmprestimoResponse> realizarEmprestimo(@Valid @RequestBody EmprestimoRequest request) {
        EmprestimoResponse response = emprestimoService.realizarEmprestimo(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}/devolucao")
    @Operation(summary = "Registrar devolução", description = "Registra a devolução de um livro emprestado")
    public ResponseEntity<EmprestimoResponse> registrarDevolucao(@PathVariable Long id) {
        EmprestimoResponse response = emprestimoService.registrarDevolucao(id);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar empréstimos", description = "Retorna todos os empréstimos registrados")
    public ResponseEntity<List<EmprestimoResponse>> listarTodosEmprestimos() {
        List<EmprestimoResponse> emprestimos = emprestimoService.listarTodosEmprestimos();
        return ResponseEntity.ok(emprestimos);
    }
    
    @GetMapping("/usuario/{usuarioId}")
    @Operation(summary = "Listar empréstimos por usuário", description = "Retorna os empréstimos de um usuário específico")
    public ResponseEntity<List<EmprestimoResponse>> listarEmprestimosPorUsuario(@PathVariable Long usuarioId) {
        List<EmprestimoResponse> emprestimos = emprestimoService.listarEmprestimosPorUsuario(usuarioId);
        return ResponseEntity.ok(emprestimos);
    }
    
    @GetMapping("/ativos")
    @Operation(summary = "Listar empréstimos ativos", description = "Retorna apenas os empréstimos em andamento")
    public ResponseEntity<List<EmprestimoResponse>> listarEmprestimosAtivos() {
        List<EmprestimoResponse> emprestimos = emprestimoService.listarEmprestimosAtivos();
        return ResponseEntity.ok(emprestimos);
    }
    
    @GetMapping("/atrasados")
    @Operation(summary = "Listar empréstimos atrasados", description = "Retorna os empréstimos em atraso")
    public ResponseEntity<List<EmprestimoResponse>> listarEmprestimosAtrasados() {
        List<EmprestimoResponse> emprestimos = emprestimoService.listarEmprestimosAtrasados();
        return ResponseEntity.ok(emprestimos);
    }
}