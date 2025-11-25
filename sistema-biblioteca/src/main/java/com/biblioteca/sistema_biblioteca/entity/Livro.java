package com.biblioteca.sistema_biblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Título é obrigatório")
    @Column(nullable = false)
    private String titulo;

    @Column(unique = true)
    private String isbn;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    private String editora;
    private String edicao;

    @Column(name = "quantidade_total")
    private Integer quantidadeTotal = 1;

    @Column(name = "quantidade_disponivel")
    private Integer quantidadeDisponivel = 1;

    @ManyToMany
    @JoinTable(
        name = "livro_autor",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private List<Autor> autores = new ArrayList<>();

    @ManyToMany
    @JoinTable(
        name = "livro_categoria",
        joinColumns = @JoinColumn(name = "livro_id"),
        inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "livro", cascade = CascadeType.ALL)
    private List<Emprestimo> emprestimos = new ArrayList<>();

    // Construtores
    public Livro() {}

    public Livro(String titulo, String isbn, Integer anoPublicacao, String editora, String edicao, Integer quantidadeTotal) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.edicao = edicao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeTotal;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getAnoPublicacao() { return anoPublicacao; }
    public void setAnoPublicacao(Integer anoPublicacao) { this.anoPublicacao = anoPublicacao; }

    public String getEditora() { return editora; }
    public void setEditora(String editora) { this.editora = editora; }

    public String getEdicao() { return edicao; }
    public void setEdicao(String edicao) { this.edicao = edicao; }

    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Integer quantidadeTotal) { 
        this.quantidadeTotal = quantidadeTotal; 
    }

    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { 
        this.quantidadeDisponivel = quantidadeDisponivel; 
    }

    public List<Autor> getAutores() { return autores; }
    public void setAutores(List<Autor> autores) { this.autores = autores; }

    public List<Categoria> getCategorias() { return categorias; }
    public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

    public List<Emprestimo> getEmprestimos() { return emprestimos; }
    public void setEmprestimos(List<Emprestimo> emprestimos) { this.emprestimos = emprestimos; }
}