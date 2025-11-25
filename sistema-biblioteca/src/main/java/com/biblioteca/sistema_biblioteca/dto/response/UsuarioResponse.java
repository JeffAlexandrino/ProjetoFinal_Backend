package com.biblioteca.sistema_biblioteca.dto.response;

import java.time.LocalDateTime;

public class UsuarioResponse {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String endereco;
    private LocalDateTime dataCadastro;
    private String status;

    // Construtor
    public UsuarioResponse(Long id, String nome, String email, String telefone, 
                          String endereco, LocalDateTime dataCadastro, String status) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
        this.dataCadastro = dataCadastro;
        this.status = status;
    }

    // Getters
    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public String getEndereco() { return endereco; }
    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public String getStatus() { return status; }
}