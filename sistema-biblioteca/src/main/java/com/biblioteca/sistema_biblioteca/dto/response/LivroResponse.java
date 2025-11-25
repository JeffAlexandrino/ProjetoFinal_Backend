package com.biblioteca.sistema_biblioteca.dto.response;

import java.util.List;
//import java.util.stream.Collectors;

public class LivroResponse {
    private Long id;
    private String titulo;
    private String isbn;
    private Integer anoPublicacao;
    private String editora;
    private String edicao;
    private Integer quantidadeTotal;
    private Integer quantidadeDisponivel;
    private List<String> autores;
    private List<String> categorias;

    // Construtor
    public LivroResponse(Long id, String titulo, String isbn, Integer anoPublicacao,
                        String editora, String edicao, Integer quantidadeTotal,
                        Integer quantidadeDisponivel, List<String> autores, List<String> categorias) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.anoPublicacao = anoPublicacao;
        this.editora = editora;
        this.edicao = edicao;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
        this.autores = autores;
        this.categorias = categorias;
    }

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIsbn() { return isbn; }
    public Integer getAnoPublicacao() { return anoPublicacao; }
    public String getEditora() { return editora; }
    public String getEdicao() { return edicao; }
    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public List<String> getAutores() { return autores; }
    public List<String> getCategorias() { return categorias; }
}