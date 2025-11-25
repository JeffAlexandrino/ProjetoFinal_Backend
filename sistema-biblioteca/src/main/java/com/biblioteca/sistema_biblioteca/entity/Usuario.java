package com.biblioteca.sistema_biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Email(message = "Email deve ser válido")
    @Column(unique = true, nullable = false)
    private String email;

    private String telefone;

    private String endereco;

    @Column(name = "data_cadastro")
    private LocalDateTime dataCadastro;

    @Enumerated(EnumType.STRING)
    private StatusUsuario status;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // Construtores
    public Usuario() {
        this.dataCadastro = LocalDateTime.now();
        this.status = StatusUsuario.ATIVO;
    }

    public Usuario(String nome, String email, String telefone, String endereco) {
        this();
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public LocalDateTime getDataCadastro() { return dataCadastro; }
    public void setDataCadastro(LocalDateTime dataCadastro) { this.dataCadastro = dataCadastro; }

    public StatusUsuario getStatus() { return status; }
    public void setStatus(StatusUsuario status) { this.status = status; }

    public List<Emprestimo> getEmprestimos() { return emprestimos; }
    public void setEmprestimos(List<Emprestimo> emprestimos) { this.emprestimos = emprestimos; }
}

enum StatusUsuario {
    ATIVO, INATIVO, SUSPENSO
}