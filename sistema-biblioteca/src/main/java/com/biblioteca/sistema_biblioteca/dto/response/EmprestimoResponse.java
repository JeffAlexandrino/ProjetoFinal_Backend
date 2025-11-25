package com.biblioteca.sistema_biblioteca.dto.response;

import java.time.LocalDateTime;

public class EmprestimoResponse {
    private Long id;
    private String usuarioNome;
    private String livroTitulo;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucaoPrevista;
    private LocalDateTime dataDevolucaoReal;
    private String status;
    private Double valorMulta;

    // Construtor
    public EmprestimoResponse(Long id, String usuarioNome, String livroTitulo,
                             LocalDateTime dataEmprestimo, LocalDateTime dataDevolucaoPrevista,
                             LocalDateTime dataDevolucaoReal, String status, Double valorMulta) {
        this.id = id;
        this.usuarioNome = usuarioNome;
        this.livroTitulo = livroTitulo;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoReal = dataDevolucaoReal;
        this.status = status;
        this.valorMulta = valorMulta;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsuarioNome() { return usuarioNome; }
    public String getLivroTitulo() { return livroTitulo; }
    public LocalDateTime getDataEmprestimo() { return dataEmprestimo; }
    public LocalDateTime getDataDevolucaoPrevista() { return dataDevolucaoPrevista; }
    public LocalDateTime getDataDevolucaoReal() { return dataDevolucaoReal; }
    public String getStatus() { return status; }
    public Double getValorMulta() { return valorMulta; }
}