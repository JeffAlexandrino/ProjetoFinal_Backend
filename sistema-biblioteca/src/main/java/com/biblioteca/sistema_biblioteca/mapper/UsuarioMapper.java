package com.biblioteca.sistema_biblioteca.mapper;

import com.biblioteca.sistema_biblioteca.dto.request.UsuarioRequest;
import com.biblioteca.sistema_biblioteca.dto.response.UsuarioResponse;
import com.biblioteca.sistema_biblioteca.entity.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {
    
    public Usuario toEntity(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setNome(request.getNome());
        usuario.setEmail(request.getEmail());
        usuario.setTelefone(request.getTelefone());
        usuario.setEndereco(request.getEndereco());
        return usuario;
    }
    
    public UsuarioResponse toResponse(Usuario usuario) {
        String statusStr;
        try {
            java.lang.reflect.Method getStatusMethod = usuario.getClass().getMethod("getStatus");
            Object status = getStatusMethod.invoke(usuario);
            statusStr = status == null ? null : status.toString();
        } catch (Exception e) {
            statusStr = null;
        }
        return new UsuarioResponse(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getTelefone(),
            usuario.getEndereco(),
            usuario.getDataCadastro(),
            statusStr
        );
    }
}