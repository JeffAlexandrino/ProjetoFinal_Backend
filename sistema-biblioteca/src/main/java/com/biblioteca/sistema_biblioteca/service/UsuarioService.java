package com.biblioteca.sistema_biblioteca.service;

import com.biblioteca.sistema_biblioteca.dto.request.UsuarioRequest;
import com.biblioteca.sistema_biblioteca.dto.response.UsuarioResponse;
import com.biblioteca.sistema_biblioteca.entity.Usuario;
import com.biblioteca.sistema_biblioteca.mapper.UsuarioMapper;
import com.biblioteca.sistema_biblioteca.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioMapper usuarioMapper;
    
    public UsuarioResponse criarUsuario(UsuarioRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Já existe um usuário com este email");
        }
        
        Usuario usuario = usuarioMapper.toEntity(request);
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioSalvo);
    }
    
    public List<UsuarioResponse> listarTodosUsuarios() {
        return usuarioRepository.findAll().stream()
            .map(usuarioMapper::toResponse)
            .collect(Collectors.toList());
    }
    
    public UsuarioResponse buscarUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return usuarioMapper.toResponse(usuario);
    }
    
    public UsuarioResponse atualizarUsuario(Long id, UsuarioRequest request) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        usuario.setNome(request.getNome());
        usuario.setTelefone(request.getTelefone());
        usuario.setEndereco(request.getEndereco());
        
        Usuario usuarioAtualizado = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioAtualizado);
    }
    
    public void deletarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioRepository.delete(usuario);
    }
    
    // Método interno para uso de outras services
    public Usuario buscarUsuarioEntityPorId(Long id) {
        return usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
}