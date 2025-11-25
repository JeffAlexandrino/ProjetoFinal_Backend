package com.biblioteca.sistema_biblioteca.resource;

import com.biblioteca.sistema_biblioteca.dto.request.UsuarioRequest;
import com.biblioteca.sistema_biblioteca.dto.response.UsuarioResponse;
import com.biblioteca.sistema_biblioteca.service.UsuarioService;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuários", description = "Operações relacionadas aos usuários da biblioteca")
public class UsuarioResource {
    
    @Autowired
    private UsuarioService usuarioService;
    
    @PostMapping
    @Operation(summary = "Criar usuário", description = "Cadastra um novo usuário na biblioteca")
    public ResponseEntity<UsuarioResponse> criarUsuario(@Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse response = usuarioService.criarUsuario(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @GetMapping
    @Operation(summary = "Listar usuários", description = "Retorna todos os usuários cadastrados")
    public ResponseEntity<List<UsuarioResponse>> listarTodosUsuarios() {
        List<UsuarioResponse> usuarios = usuarioService.listarTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico pelo ID")
    public ResponseEntity<UsuarioResponse> buscarUsuarioPorId(@PathVariable Long id) {
        UsuarioResponse usuario = usuarioService.buscarUsuarioPorId(id);
        return ResponseEntity.ok(usuario);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente")
    public ResponseEntity<UsuarioResponse> atualizarUsuario(
            @PathVariable Long id, 
            @Valid @RequestBody UsuarioRequest request) {
        UsuarioResponse usuario = usuarioService.atualizarUsuario(id, request);
        return ResponseEntity.ok(usuario);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar usuário", description = "Remove um usuário do sistema")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}