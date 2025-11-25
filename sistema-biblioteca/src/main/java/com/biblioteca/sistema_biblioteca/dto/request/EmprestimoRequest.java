package com.biblioteca.sistema_biblioteca.dto.request;

import jakarta.validation.constraints.NotNull;

public class EmprestimoRequest {
    @NotNull(message = "ID do usuário é obrigatório")
    private Long usuarioId;

    @NotNull(message = "ID do livro é obrigatório")
    private Long livroId;

    // Getters e Setters
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
    public Long getLivroId() { return livroId; }
    public void setLivroId(Long livroId) { this.livroId = livroId; }
}