package com.biblioteca.sistema_biblioteca.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class LivroRequest {
    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    private String isbn;
    private Integer anoPublicacao;
    private String editora;
    private String edicao;

    @NotNull(message = "Quantidade total é obrigatória")
    private Integer quantidadeTotal;

    private List<Long> autoresIds;
    private List<Long> categoriasIds;

    // Getters e Setters
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
    public void setQuantidadeTotal(Integer quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }
    public List<Long> getAutoresIds() { return autoresIds; }
    public void setAutoresIds(List<Long> autoresIds) { this.autoresIds = autoresIds; }
    public List<Long> getCategoriasIds() { return categoriasIds; }
    public void setCategoriasIds(List<Long> categoriasIds) { this.categoriasIds = categoriasIds; }
}